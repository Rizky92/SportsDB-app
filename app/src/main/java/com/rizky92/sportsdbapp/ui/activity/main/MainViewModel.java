package com.rizky92.sportsdbapp.ui.activity.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rizky92.sportsdbapp.api.response.MainRepository;
import com.rizky92.sportsdbapp.api.response.country.Countries;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    MainRepository mainRepository;

    public MainViewModel(Application application) {
        super(application);
        mainRepository = new MainRepository();
    }

    public LiveData<List<Countries>> getAllLeagues() {
        return mainRepository.getAllLeagues();
    }
}
