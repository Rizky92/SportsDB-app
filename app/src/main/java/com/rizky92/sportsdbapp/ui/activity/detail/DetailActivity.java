package com.rizky92.sportsdbapp.ui.activity.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.rizky92.sportsdbapp.R;
import com.rizky92.sportsdbapp.databinding.ActivityDetailBinding;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    public static final String LEAGUE_ID = "league_id";
    DetailViewModel viewModel;
    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String leagueId = getIntent().getStringExtra(LEAGUE_ID);

        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        viewModel.setLeagueId(leagueId);
        viewModel.getLeagueDetail().observe(this, leagues -> {
            if (leagues != null && leagues.size() > 0) {
                binding.tvLeagueName.setText(leagues.get(0).getStrLeague());
                Glide.with(this)
                        .load(leagues.get(0).getStrBadge())
                        .thumbnail(0.4f)
                        .into(binding.ivLeagueLogo);
            }
        });

        TabPagerAdapter tab = new TabPagerAdapter(getSupportFragmentManager(), this);
        binding.tabPager.setAdapter(tab);
        binding.tabDetail.setupWithViewPager(binding.tabPager);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
