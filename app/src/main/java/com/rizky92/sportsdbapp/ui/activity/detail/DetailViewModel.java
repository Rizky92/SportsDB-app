package com.rizky92.sportsdbapp.ui.activity.detail;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rizky92.sportsdbapp.api.response.MainRepository;
import com.rizky92.sportsdbapp.api.response.event.Events;
import com.rizky92.sportsdbapp.api.response.league.Leagues;
import com.rizky92.sportsdbapp.api.response.team.Teams;

import java.util.List;

public class DetailViewModel extends AndroidViewModel {

    MainRepository mainRepository;
    String leagueId;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        mainRepository = new MainRepository();
    }

    public LiveData<List<Events>> getNextMatchEvents() {
        return mainRepository.getNextMatchEvents(leagueId);
    }

    public LiveData<List<Events>> getPreviousMatchEvents() {
        return mainRepository.getPreviousMatchEvents(leagueId);
    }

    public LiveData<List<Teams>> getAllTeamsInLeague() {
        return mainRepository.getAllTeamsInLeague(leagueId);
    }

    public LiveData<List<Leagues>> getLeagueDetail() {
        return mainRepository.getLeagueDetail(leagueId);
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
        Log.d("LeagueId", leagueId);
    }
}
