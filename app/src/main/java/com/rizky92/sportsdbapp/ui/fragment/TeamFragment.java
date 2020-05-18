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
import androidx.recyclerview.widget.GridLayoutManager;

import com.rizky92.sportsdbapp.R;
import com.rizky92.sportsdbapp.api.response.team.Teams;
import com.rizky92.sportsdbapp.databinding.TeamFragmentBinding;
import com.rizky92.sportsdbapp.ui.activity.detail.DetailViewModel;
import com.rizky92.sportsdbapp.ui.fragment.adapter.TeamAdapter;

import java.util.ArrayList;
import java.util.List;

public class TeamFragment extends Fragment {

    DetailViewModel viewModel;
    TeamFragmentBinding binding;
    List<Teams> list = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.team_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showState(1);

        binding.rvTeams.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        binding.rvTeams.setHasFixedSize(true);
        TeamAdapter adapter = new TeamAdapter(getActivity(), list);
        binding.rvTeams.setAdapter(adapter);

        viewModel = new ViewModelProvider(requireActivity()).get(DetailViewModel.class);
        viewModel.getAllTeamsInLeague().observe(getViewLifecycleOwner(), teams -> {
            if (teams != null || teams.size() > 0) {
                adapter.addItems(teams);
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
