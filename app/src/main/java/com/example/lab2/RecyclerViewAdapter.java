package com.example.lab2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {


    private List<Item> items;
    private LayoutInflater inflater;

    public RecyclerViewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        items = new ArrayList<>();
    }

    public void addAll(List<Item> items) {
        int startIndex = this.items.size();
        this.items.addAll(items);
        notifyItemRangeChanged(startIndex, items.size());
    }

    public Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = items.get(position);
        holder.tvTitle.setText(item.getName());
        Glide.with(inflater.getContext())
                .load(item.getImage())
                .centerCrop()
                .crossFade()
                .into(holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageView ivPhoto;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            ivPhoto = (ImageView) itemView.findViewById(R.id.ivPhoto);
        }
    }
}
