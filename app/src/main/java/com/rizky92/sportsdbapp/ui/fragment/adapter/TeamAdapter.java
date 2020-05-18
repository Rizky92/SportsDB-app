package com.rizky92.sportsdbapp.ui.fragment.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rizky92.sportsdbapp.R;
import com.rizky92.sportsdbapp.api.response.team.Teams;
import com.rizky92.sportsdbapp.databinding.ItemsGridBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private List<Teams> list;
    private Activity activity;

    public TeamAdapter(Activity activity, List<Teams> items) {
        this.activity = activity;
        this.list = items;
    }

    public void addItems(List<Teams> items) {
        this.list.clear();
        this.list.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsGridBinding binding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.items_grid, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Teams item = list.get(position);
        holder.binding.tvLeague.setText(item.getStrTeam());
        Glide.with(activity)
                .load(item.getStrTeamBadge())
                .thumbnail(0.4f)
                .into(holder.binding.ivLeague);
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
