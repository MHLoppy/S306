package com.markheath.lostandfoundapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<ItemEntity> itemList;
    private Context context;
    Activity activity;

    public ItemAdapter(Activity activity, Context context, List<ItemEntity> itemList) {
        this.itemList = itemList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_row, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemEntity currentItem = itemList.get(position);
        holder.bind(currentItem);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RemoveItemActivity.class);
            intent.putExtra("Id", currentItem.getId());
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView postTypeTextView, itemDescriptionTextView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            postTypeTextView = itemView.findViewById(R.id.postTypeTextView);
            itemDescriptionTextView = itemView.findViewById(R.id.itemDescriptionTextView);
        }

        public void bind(ItemEntity item) {
            postTypeTextView.setText(item.getPostType());
            itemDescriptionTextView.setText(item.getItemDescription());
        }
    }
}

