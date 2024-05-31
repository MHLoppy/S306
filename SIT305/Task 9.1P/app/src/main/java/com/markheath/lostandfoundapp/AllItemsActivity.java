package com.markheath.lostandfoundapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AllItemsActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_REMOVE_ITEM = 1;
    RecyclerView itemsListRecView;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_items_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Context appContext = getApplicationContext();
        ItemDatabase itemDatabase = ItemDatabaseClient.getInstance(appContext);

        List<ItemEntity> allItems = itemDatabase.itemDao().getAllItems();

        itemsListRecView = findViewById(R.id.allItemsRecView);
        itemAdapter = new ItemAdapter(AllItemsActivity.this, this, allItems);
        itemsListRecView.setLayoutManager(new LinearLayoutManager(this));
        itemsListRecView.setAdapter(itemAdapter);
    }

    // surprisingly, the 9AM brute-force solution did not work
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        // inelegant and low-performance, but adequate for the nearly-9AM submission
//        itemAdapter.notifyDataSetChanged();
//    }

    // this one should work if I finished the implementation, but I'm exhausted so let's just cheat
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
////        if (requestCode == REQUEST_CODE_REMOVE_ITEM && resultCode == Activity.RESULT_OK) {
////            int removedPosition = data.getIntExtra("removedId", -1);
////            if (removedPosition != -1) {
////                // Update the RecyclerView to reflect the removed item
////                itemAdapter.notifyItemRemoved(removedPosition);
////            }
////        }
//        // refresh the task layout
//        if (requestCode == 1){
//            recreate();
//        }
//    }
}