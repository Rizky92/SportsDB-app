package com.rizky92.sportsdbapp.ui.fragment.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rizky92.sportsdbapp.R;
import com.rizky92.sportsdbapp.api.client.ApiClient;
import com.rizky92.sportsdbapp.api.client.ApiService;
import com.rizky92.sportsdbapp.api.response.event.Events;
import com.rizky92.sportsdbapp.api.response.team.TeamResponse;
import com.rizky92.sportsdbapp.databinding.ItemsDetailBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchListAdapter extends RecyclerView.Adapter<MatchListAdapter.ViewHolder> {

    List<Events> list;
    Activity activity;

    public MatchListAdapter(Activity activity, List<Events> events) {
        this.activity = activity;
        this.list = events;
    }

    public void addItems(List<Events> items) {
        this.list.clear();
        this.list.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsDetailBinding binding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.items_detail, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Events item = list.get(position);

        holder.binding.tvHome.setText(item.getStrHomeTeam());
        holder.binding.tvHomeScore.setText(item.getIntHomeScore());
        holder.binding.tvAway.setText(item.getStrAwayTeam());
        holder.binding.tvAwayScore.setText(item.getIntAwayScore());

        holder.binding.tvSchedule.setText("Scheduled: " + item.getDateEvent());

        getTeamLogo(item.getIdHomeTeam(), holder.binding.ivLogoHome);
        getTeamLogo(item.getIdAwayTeam(), holder.binding.ivLogoAway);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemsDetailBinding binding;

        ViewHolder(@NonNull ItemsDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private void getTeamLogo(String teamId, ImageView imageView) {
        ApiService service = ApiClient.getApiClient().create(ApiService.class);
        Call<TeamResponse> api = service.getTeamLogo(teamId);
        api.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful() && response.body().getTeams() != null) {
                    if (!activity.isFinishing()) {
                        Glide.with(activity)
                                .load(response.body().getTeams().get(0).getStrTeamBadge())
                                .thumbnail(0.4f)
                                .into(imageView);
                    }
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                Log.d("MatchListAdapter", t.getMessage());
            }
        });
    }
}
