package com.rizky92.sportsdbapp.ui.activity.main;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.rizky92.sportsdbapp.R;
import com.rizky92.sportsdbapp.api.response.country.Countries;
import com.rizky92.sportsdbapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    List<Countries> list = new ArrayList<>();
    MainAdapter adapter;
    MainViewModel viewModel;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        showState(1);

        binding.rvLeagues.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rvLeagues.setHasFixedSize(true);
        adapter = new MainAdapter(this, list);
        binding.rvLeagues.setAdapter(adapter);
        binding.swipeToRefresh.setOnRefreshListener(this);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        loadLeagues();
    }

    @Override
    public void onRefresh() {
        loadLeagues();
        binding.swipeToRefresh.setRefreshing(false);
    }

    private void loadLeagues() {
        viewModel.getAllLeagues().observe(this, items -> {
            if (items != null) {
                showState(0);
                adapter.addItems(items);
            } else {
                showState(2);
            }
        });
    }

    private void showState(int state) {
        View loading = binding.contentLoading.getRoot();
        View empty = binding.contentEmpty.getRoot();
        switch (state) {
            case 0:
                loading.setVisibility(View.GONE);
                empty.setVisibility(View.GONE);
                break;

            case 1:
                loading.setVisibility(View.VISIBLE);
                empty.setVisibility(View.GONE);
                break;

            case 2:
                loading.setVisibility(View.GONE);
                empty.setVisibility(View.VISIBLE);
                break;

            default:
                break;
        }
    }
}
