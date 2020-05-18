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

import com.rizky92.sportsdbapp.R;
import com.rizky92.sportsdbapp.databinding.DescriptionFragmentBinding;
import com.rizky92.sportsdbapp.ui.activity.detail.DetailViewModel;

import static android.view.View.GONE;

public class DescriptionFragment extends Fragment {

    DescriptionFragmentBinding binding;
    DetailViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.description_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showState(1);
        viewModel = new ViewModelProvider(getActivity()).get(DetailViewModel.class);
        viewModel.getLeagueDetail().observe(getViewLifecycleOwner(), leagues -> {
            if (leagues != null && leagues.size() > 0) {
                showState(0);
                if (leagues.get(0).getStrFacebook() == null || leagues.get(0).getStrFacebook().isEmpty()) {
                    binding.ivFacebook.setVisibility(GONE);
                    binding.tvFacebook.setVisibility(GONE);
                } else {
                    binding.tvFacebook.setText(leagues.get(0).getStrFacebook());
                }

                if (leagues.get(0).getStrTwitter() == null || leagues.get(0).getStrTwitter().isEmpty()) {
                    binding.ivTwitter.setVisibility(GONE);
                    binding.tvTwitter.setVisibility(GONE);
                } else {
                    binding.tvTwitter.setText(leagues.get(0).getStrTwitter());
                }

                if (leagues.get(0).getStrYoutube() == null || leagues.get(0).getStrYoutube().isEmpty()) {
                    binding.ivYoutube.setVisibility(GONE);
                    binding.tvYoutube.setVisibility(GONE);
                } else {
                    binding.tvYoutube.setText(leagues.get(0).getStrYoutube());
                }

                if (leagues.get(0).getStrWebsite() == null || leagues.get(0).getStrWebsite().isEmpty()) {
                    binding.ivWeb.setVisibility(GONE);
                    binding.tvWeb.setVisibility(GONE);
                } else {
                    binding.tvWeb.setText(leagues.get(0).getStrWebsite());
                }

                if (leagues.get(0).getStrDescriptionEN() == null || leagues.get(0).getStrDescriptionEN().isEmpty()) {
                    binding.tvDesc.setVisibility(GONE);
                } else {
                    binding.tvDesc.setText(leagues.get(0).getStrDescriptionEN());
                }
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

                binding.tvDesc.setVisibility(GONE);
                binding.tvWeb.setVisibility(GONE);
                binding.tvYoutube.setVisibility(GONE);
                binding.tvTwitter.setVisibility(GONE);
                binding.tvFacebook.setVisibility(GONE);
                binding.ivWeb.setVisibility(GONE);
                binding.ivYoutube.setVisibility(GONE);
                binding.ivTwitter.setVisibility(GONE);
                binding.ivFacebook.setVisibility(GONE);
                break;

            default:
                break;
        }
    }
}
