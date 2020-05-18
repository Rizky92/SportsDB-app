package com.rizky92.sportsdbapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rizky92.sportsdbapp.R;
import com.rizky92.sportsdbapp.api.response.event.Events;
import com.rizky92.sportsdbapp.databinding.MatchFragmentBinding;
import com.rizky92.sportsdbapp.ui.activity.detail.DetailViewModel;
import com.rizky92.sportsdbapp.ui.fragment.adapter.MatchListAdapter;

import java.util.ArrayList;
import java.util.List;

public class NextMatchFragment extends Fragment {

    DetailViewModel viewModel;
    MatchFragmentBinding binding;
    List<Events> events = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.match_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showState(1);

        binding.rvMatches.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvMatches.setHasFixedSize(true);
        MatchListAdapter adapter = new MatchListAdapter(getActivity(), events);
        binding.rvMatches.setAdapter(adapter);

        viewModel = new ViewModelProvider(getActivity()).get(DetailViewModel.class);
        viewModel.getNextMatchEvents().observe(getViewLifecycleOwner(), events -> {
            if (events != null || events.size() > 0) {
                adapter.addItems(events);
                showState(0);
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
