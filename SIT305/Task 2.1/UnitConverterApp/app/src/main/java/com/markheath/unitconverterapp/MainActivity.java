package com.markheath.unitconverterapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import java.util.Locale;

enum UnitType {
    LENGTH,
    WEIGHT,
    TEMPERATURE
}

public class MainActivity extends AppCompatActivity {

    Button buttonChangeUnitsToLength;
    Button buttonChangeUnitsToWeight;
    Button buttonChangeUnitsToTemperature;
    TextView textViewConvert;
    TextView textViewTo;
    TextView textViewResult;
    EditText editTextNumberDecimal;
    Spinner spinnerConvertFrom;
    Spinner spinnerConvertTo;
    Button buttonConvert;
    ImageView imageAd;
    List<String> unitsLength;
    List<String> unitsWeight;
    List<String> unitsTemperature;
    Double input;
    Double output;
    UnitType currentUnit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize stuff
        buttonChangeUnitsToLength      = findViewById(R.id.buttonChangeUnitsToLength);
        buttonChangeUnitsToWeight      = findViewById(R.id.buttonChangeUnitsToWeight);
        buttonChangeUnitsToTemperature = findViewById(R.id.buttonChangeUnitsToTemperature);

        textViewConvert = findViewById(R.id.textViewConvert);
        textViewTo      = findViewById(R.id.textViewTo);
        textViewResult  = findViewById(R.id.textViewResult);

        editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal);

        spinnerConvertFrom = findViewById(R.id.spinnerConvertFrom);
        spinnerConvertTo   = findViewById(R.id.spinnerConvertTo);

        buttonConvert = findViewById(R.id.buttonConvert);

        imageAd = findViewById(R.id.imageAd);

        input = 1.0;    // placeholder values
        output = 1.0;
        currentUnit = UnitType.LENGTH;

        // Set default appearance of the buttons
        SetButtonActive(buttonChangeUnitsToLength);                     // since length is the default selection
        SetButtonInactive(buttonChangeUnitsToWeight);
        SetButtonInactive(buttonChangeUnitsToTemperature);
        //String fadedWhite = "#8CFFFFFF";                                // a bit scuffed, but colors.xml isn't cooperating
        //buttonConvert.setTextColor(Color.parseColor(fadedWhite));

        // [universally] Change input box background color programmatically (not exposed in XML properties?)
        // https://stackoverflow.com/a/25922062/16367940
        //editTextNumberDecimal.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);//something else seems to be changing some stuff here and it's not worth debugging

        // Make arraylists to use with the dropdowns (spinners)
        unitsLength = List.of("cm", "m", "km", "in", "ft", "yd", "mi");
        unitsWeight = List.of("mg", "g", "kg", "lbs", "oz", "sh. tn");
        unitsTemperature = List.of("F", "C", "K");

        // Make a mega-list of all available units (how does Java not have a simple way to do this????????)
        //List<String> unitsAll = Stream.of(unitsLength.stream(), unitsWeight.stream(), unitsTemperature.stream())
        //        .flatMap(Function.identity())
        //        .collect(Collectors.toList());

        // Create an adapter for each set of units
        ArrayAdapter<String> adapterLengths      = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unitsLength);
        ArrayAdapter<String> adapterWeights      = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unitsWeight);
        ArrayAdapter<String> adapterTemperatures = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unitsTemperature);
        //ArrayAdapter<String> adapterAll          = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unitsAll);

        // Set the defaults for both spinners
        adapterLengths.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerConvertFrom.setAdapter(adapterLengths);
        spinnerConvertTo.setAdapter((adapterLengths));

        // Monitor for spinner selection
        spinnerConvertFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // not currently used?
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // not currently used?
            }
        });

        // Length button (this seems like a slightly scuffed way of doing this)
        buttonChangeUnitsToLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUnit = UnitType.LENGTH;

                SetButtonActive(buttonChangeUnitsToLength);
                SetButtonInactive(buttonChangeUnitsToWeight);
                SetButtonInactive(buttonChangeUnitsToTemperature);

                spinnerConvertFrom.setAdapter(adapterLengths);
                spinnerConvertTo.setAdapter((adapterLengths));
            }
        });

        // Weight button (this seems like a slightly scuffed way of doing this)
        buttonChangeUnitsToWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUnit = UnitType.WEIGHT;

                SetButtonInactive(buttonChangeUnitsToLength);
                SetButtonActive(buttonChangeUnitsToWeight);
                SetButtonInactive(buttonChangeUnitsToTemperature);

                spinnerConvertFrom.setAdapter(adapterWeights);
                spinnerConvertTo.setAdapter((adapterWeights));
            }
        });

        // Temperature button (this seems like a slightly scuffed way of doing this)
        buttonChangeUnitsToTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUnit = UnitType.TEMPERATURE;

                SetButtonInactive(buttonChangeUnitsToLength);
                SetButtonInactive(buttonChangeUnitsToWeight);
                SetButtonActive(buttonChangeUnitsToTemperature);

                spinnerConvertFrom.setAdapter(adapterTemperatures);
                spinnerConvertTo.setAdapter((adapterTemperatures));
            }
        });

        // Convert button press
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    input = Double.parseDouble(editTextNumberDecimal.getText().toString());
                    String inputUnit = spinnerConvertFrom.getSelectedItem().toString();
                    String outputUnit = spinnerConvertTo.getSelectedItem().toString();
                    output = ConvertWithCorrectUnit(input, inputUnit, outputUnit);
                    //String formattedOutput = String.format(Locale.ENGLISH, "%,.9f", output);
                    String formattedOutput = String.format(Locale.ENGLISH, "%.10f", output).replaceAll("\\.?0*$", "");

                    String result = input + " " + inputUnit + " = " + formattedOutput + " " + outputUnit;
                    textViewResult.setText(result);
                } catch (Exception e) {
                    String message = getResources().getString(R.string.errorEmptyInput);
                    Toast.makeText(MainActivity.this, message,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // The phrase "earning a living" implies that living is a privilege to be earned and not an innate right in modern society
        String adUrl = getResources().getString(R.string.urlAd);
        imageAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(adUrl));
                startActivity(browserIntent);
            }
        });
    }

    // I'm used to C# syntax, sue me
    private void SetButtonActive(Button button)
    {
        button.setTextColor(Color.WHITE);
        button.setTypeface(null, Typeface.BOLD);
    }

    private void SetButtonInactive(Button button)
    {
        String fadedWhite = "#8CFFFFFF";        // a bit scuffed, but colors.xml isn't cooperating
        button.setTextColor(Color.parseColor(fadedWhite));
        button.setTypeface(null, Typeface.NORMAL);
    }

    private double ConvertWithCorrectUnit(double input, String fromUnit, String toUnit)
    {
        double result;

        if (currentUnit == UnitType.LENGTH) {
            result = ConvertLength(input, fromUnit, toUnit);
        }
        else if (currentUnit == UnitType.WEIGHT) {
            result = ConvertWeight(input, fromUnit, toUnit);
        }
        else if (currentUnit == UnitType.TEMPERATURE) {
            result = ConvertTemperature(input, fromUnit, toUnit);
        }
        else {
            String message = String.valueOf(R.string.errorInvalidUnit);
            Toast.makeText(MainActivity.this, message,
                    Toast.LENGTH_SHORT).show();
            result = 0;
        }

//        if (result == 0) {
//            String message = String.valueOf(R.string.errorEmptyInput);
//            Toast.makeText(MainActivity.this, message,
//                    Toast.LENGTH_SHORT).show();
//        }

        return result;
    }

    private double ConvertLength(double input, String fromUnit, String toUnit)
    {
        // Avoid conversion of equal units
        if (fromUnit.equals(toUnit)) {
            return input;
        }

        double inputInMeters;
        double result;

        // First, convert to "base" unit of meters
        switch (fromUnit) {
            case "cm":
                inputInMeters = input * 0.01;
                break;
            case "km":
                inputInMeters = input * 1000;
                break;
            case "in":
                inputInMeters = input * 0.0254;
                break;
            case "ft":
                inputInMeters = input * 0.3048;
                break;
            case "yd":
                inputInMeters = input * 0.9144;
                break;
            case "mi":
                inputInMeters = input * 1609.34;
                break;
            default:        // already in meters (hopefully)
                inputInMeters = input;
                break;
        }

        // Then, convert from "base" unit to specified unit
        switch (toUnit) {
            case "cm":
                result = inputInMeters / 0.01;
                break;
            case "km":
                result = inputInMeters / 1000;
                break;
            case "in":
                result = inputInMeters / 0.0254;
                break;
            case "ft":
                result = inputInMeters / 0.3048;
                break;
            case "yd":
                result = inputInMeters / 0.9144;
                break;
            case "mi":
                result = inputInMeters / 1609.34;
                break;
            default:        // already in meters (hopefully)
                result = inputInMeters;
                break;
        }

        return result;
    }

    private double ConvertWeight(double input, String fromUnit, String toUnit)
    {
        // Avoid conversion of equal units
        if (fromUnit.equals(toUnit)) {
            return input;
        }

        double inputInGrams;
        double result;

        // First, convert to "base" unit of meters
        switch (fromUnit) {
            case "mg":
                inputInGrams = input * 0.1;
                break;
            case "kg":
                inputInGrams = input * 0.001;
                break;
            case "lbs":
                inputInGrams = input * 453.592;
                break;
            case "oz":
                inputInGrams = input * 28.3495;
                break;
            case "sh. tn":
                inputInGrams = input * 907185;
                break;
            default:        // already in grams (hopefully)
                inputInGrams = input;
                break;
        }

        // Then, convert from "base" unit to specified unit
        switch (toUnit) {
            case "mg":
                result = inputInGrams / 10;
                break;
            case "kg":
                result = inputInGrams / 0.001;
                break;
            case "lbs":
                result = inputInGrams / 453.592;
                break;
            case "oz":
                result = inputInGrams / 28.3495;
                break;
            case "sh. tn":
                result = inputInGrams / 907185;
                break;
            default:        // already in grams (hopefully)
                result = inputInGrams;
                break;
        }

        return result;
    }

    private double ConvertTemperature(double input, String fromUnit, String toUnit)
    {
        // Avoid conversion of equal units
        if (fromUnit.equals(toUnit)) {
            return input;
        }

        double inputInCelsius;
        double result;

        // First, convert to "base" unit of Kelvin
        switch (fromUnit) {
            case "F":
                inputInCelsius = (input - 32) / 1.8;
                break;
            case "K":
                inputInCelsius = input - 273.15;
                break;
            default:        // already in Celsius (hopefully)
                inputInCelsius = input;
                break;
        }

        // Then, convert from "base" unit to specified unit
        switch (toUnit) {
            case "F":
                result = (inputInCelsius * 1.8) + 32;
                break;
            case "K":
                result = inputInCelsius + 273.15;
                break;
            default:        // already in Celsius (hopefully)
                result = inputInCelsius;
                break;
        }

        return result;
    }
}