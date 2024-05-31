package com.markheath.lostandfoundapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.libraries.places.api.model.Place;     // THIS ONE!!!!

import com.google.android.gms.common.api.Status;
//import com.google.android.gms.location.places.Place;          // NOT THIS ONE!!! AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
//import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class NewAdvertActivity extends AppCompatActivity {

    RadioGroup postTypeRadioGroup;
    RadioButton lostRadioButton, foundRadioButton, selectedRadioButton;
    Button saveButton, getCurrentLocationButton;
    TextInputEditText nameTextInputEditText, phoneTextInputEditText, descriptionTextInputEditText,
            dateTextInputEditText, locationTextInputEditText;
    String postType, userName, userPhoneNumber, itemDescription, itemLocationFound;
    LocalDate itemDateFound;
    LocationManager locationManager;
    LocationListener locationListener;
    double latitude, longitude;
    Location itemLocationLocation;

    // from SIT305 example
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_advert_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {

                latitude = location.getLatitude();
                longitude = location.getLongitude();
                String lat_string = String.valueOf(latitude);
                String lng_string = String.valueOf(longitude);
                String loc_text = lat_string + ", " + lng_string;

                locationTextInputEditText.setText(loc_text);
            }
        };

        postTypeRadioGroup = findViewById(R.id.postTypeRadioGroup);
        lostRadioButton    = findViewById(R.id.lostRadioButton);
        foundRadioButton   = findViewById(R.id.foundRadioButton);

        nameTextInputEditText        = findViewById(R.id.nameTextInputEditText);
        phoneTextInputEditText       = findViewById(R.id.phoneTextInputEditText);
        descriptionTextInputEditText = findViewById(R.id.descriptionTextInputEditText);
        dateTextInputEditText        = findViewById(R.id.dateTextInputEditText);
        locationTextInputEditText    = findViewById(R.id.locationTextInputEditText);

        getCurrentLocationButton = findViewById(R.id.getCurrentLocationButton);
        saveButton = findViewById(R.id.saveButton);

        postTypeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            selectedRadioButton = findViewById(checkedId);
            if (selectedRadioButton != null) {

                if (selectedRadioButton.getId() == R.id.lostRadioButton) {
                    postType = "Lost";      // this could ultimately be simplified to have ItemEntity store a bool called isFound instead of a string, but oh well
                } else if (selectedRadioButton.getId() == R.id.foundRadioButton) {
                    postType = "Found";
                }
            }
        });

        getCurrentLocationButton.setOnClickListener( v -> {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        });

        saveButton.setOnClickListener(v -> {

            int hasValid = 0;
            int requiredValid = 2;

            userName          = nameTextInputEditText.getText().toString();
            userPhoneNumber   = phoneTextInputEditText.getText().toString();
            itemDescription   = descriptionTextInputEditText.getText().toString();

            //itemLocationFound = locationTextInputEditText.getText().toString();

            try {
                itemDateFound = LocalDate.parse(dateTextInputEditText.getText().toString());
                hasValid++;
            }
            catch (Exception ex) {
                Toast.makeText(NewAdvertActivity.this, "Invalid date format.", Toast.LENGTH_SHORT).show();
            }

            if (selectedRadioButton != null) {
                hasValid++;
            }

            // require a valid radio button selection and a valid date found
            if (hasValid >= requiredValid) {

                // don't save unless the stuff seems to be valid
                ItemDatabase itemDatabase = ItemDatabaseClient.getInstance(NewAdvertActivity.this);

                ItemEntity newItem = new ItemEntity();
                newItem.setPostType(postType);
                newItem.setUserName(userName);
                newItem.setUserPhoneNumber(userPhoneNumber);
                newItem.setItemDescription(itemDescription);
                newItem.setItemDateFound(itemDateFound);
                //newItem.setItemLocationFound(itemLocationFound);
                newItem.setItemLocationLatitude(latitude);
                newItem.setItemLocationLongitude(longitude);

                itemDatabase.itemDao().insertItem(newItem);

                // it's not explicitly said what to do after saving, so I've decided to go to the list
                Intent intent = new Intent(NewAdvertActivity.this, AllItemsActivity.class);
                startActivity(intent);
            } else if (selectedRadioButton == null) {
                Toast.makeText(NewAdvertActivity.this, "Must select a post type! (lost or found)", Toast.LENGTH_SHORT).show();
            } else {
                // (probably invalid date format still)
            }
        });

        // initialize Places SDK (??) (this isn't the "new" one) https://developers.google.com/maps/documentation/places/android-sdk/config#places-sdk-for-android
        //String apiKey = "${MAPS_API_KEY}";// I don't understand enough about how Android handles string interpolation to understand why this doesn't work
        String apiKey = "AIza[...]";
        Places.initialize(getApplicationContext(), apiKey);
        PlacesClient placesClient = Places.createClient(this);

        // autocomplete fragment for 9.1P [updated to AutocompleteSupportFragment, since other one is deprecated)
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setCountries("AU");

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {

//                // scuffed debugging using toasts instead of real logging
//                if (place == null)
//                {
//                    Toast.makeText(getApplicationContext(), "place is null", Toast.LENGTH_SHORT).show();
//                }
//
//                if (place.getLatLng() == null)
//                {
//                    Toast.makeText(getApplicationContext(), "coords are null", Toast.LENGTH_SHORT).show();
//                }

                // a bit scuffed because when I first made this I didn't realise the LatLng data type existed
                latitude = place.getLatLng().latitude;
                longitude = place.getLatLng().longitude;

                String loc_text = latitude + ", " + longitude;
                locationTextInputEditText.setText(loc_text);
            }

            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error. [skipped for sake of time]
                //Log.i(TAG, "An error occurred: " + status);
            }
        });

        // (a few remnants from trying to do it properly using in-code logic instead of the fragment)
//        locationTextInputEditText.setOnClickListener(v -> {
//            autocompleteFragment.getView().setVisibility(View.VISIBLE);
//        });
//
//        startAutocomplete = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                result -> {
//                    if (result.getResultCode() == RESULT_OK) {
//                        Intent intent = result.getData();
//                        if (intent != null) {
//                            Place place = Autocomplete.getPlaceFromIntent(intent);
//                            // Use the selected place information in your app
//                        }
//                    }
//                }
//        );
//
//        // instead of using a fragment (logic tied to the fragment UI element), make it code-based
//        // Set the fields to specify which types of place data to
//        // return after the user has made a selection.
//        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);
//
//        // Start the autocomplete intent.
//        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
//                .build(this);
//        startAutocomplete.launch(intent);

    }
}