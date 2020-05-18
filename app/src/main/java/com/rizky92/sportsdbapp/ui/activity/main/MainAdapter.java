package com.rizky92.sportsdbapp.ui.activity.main;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rizky92.sportsdbapp.R;
import com.rizky92.sportsdbapp.api.response.country.Countries;
import com.rizky92.sportsdbapp.databinding.ItemsGridBinding;
import com.rizky92.sportsdbapp.ui.activity.detail.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    Activity activity;
    List<Countries> list;

    public void addItems(List<Countries> items) {
        this.list.clear();
        this.list.addAll(items);
        notifyDataSetChanged();
    }

    public MainAdapter(Activity activity, List<Countries> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsGridBinding binding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.items_grid, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Countries item = list.get(position);
        holder.binding.tvLeague.setText(item.getStrLeague());
        Glide.with(activity)
                .load(item.getStrBadge())
                .thumbnail(0.4f)
                .into(holder.binding.ivLeague);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, DetailActivity.class);
            intent.putExtra(DetailActivity.LEAGUE_ID, item.getIdLeague());
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemsGridBinding binding;

        ViewHolder(@NonNull ItemsGridBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
