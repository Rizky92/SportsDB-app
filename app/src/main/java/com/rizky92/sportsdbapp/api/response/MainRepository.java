package com.rizky92.sportsdbapp.api.response;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.rizky92.sportsdbapp.api.client.ApiClient;
import com.rizky92.sportsdbapp.api.client.ApiService;
import com.rizky92.sportsdbapp.api.response.country.Countries;
import com.rizky92.sportsdbapp.api.response.country.CountryResponse;
import com.rizky92.sportsdbapp.api.response.event.EventResponse;
import com.rizky92.sportsdbapp.api.response.event.Events;
import com.rizky92.sportsdbapp.api.response.league.LeagueResponse;
import com.rizky92.sportsdbapp.api.response.league.Leagues;
import com.rizky92.sportsdbapp.api.response.team.TeamResponse;
import com.rizky92.sportsdbapp.api.response.team.Teams;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private static final String TAG = MainRepository.class.getSimpleName();

    public MainRepository() {
    }

    public MutableLiveData<List<Countries>> getAllLeagues() {
        MutableLiveData<List<Countries>> liveData = new MutableLiveData<>();
        ApiService service = ApiClient.getApiClient().create(ApiService.class);
        Call<CountryResponse> api = service.getAllLeagues("Soccer");
        api.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, retrofit2.Response<CountryResponse> response) {
                Log.d(TAG, response.toString());
                Log.d(TAG, "HTTP code: " + String.valueOf(response.code()));
                if (response.isSuccessful() && response.body().getCountries() != null) {
                    liveData.setValue(response.body().getCountries());
                } else {
                    liveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
        return liveData;
    }

    public MutableLiveData<List<Events>> getNextMatchEvents(@Nullable String leagueId) {
        MutableLiveData<List<Events>> liveData = new MutableLiveData<>();
        ApiService service = ApiClient.getApiClient().create(ApiService.class);
        Call<EventResponse> api = service.getNextMatchEvent(leagueId);
        api.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                Log.d(TAG, response.toString());
                Log.d(TAG, "HTTP code: " + String.valueOf(response.code()));
                if (response.isSuccessful() && response.body().getEvents() != null) {
                    liveData.setValue(response.body().getEvents());
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
        return liveData;
    }

    public MutableLiveData<List<Events>> getPreviousMatchEvents(@Nullable String leagueId) {
        MutableLiveData<List<Events>> liveData = new MutableLiveData<>();
        ApiService service = ApiClient.getApiClient().create(ApiService.class);
        Call<EventResponse> api = service.getPreviousMatchEvent(leagueId);
        api.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                Log.d(TAG, response.toString());
                Log.d(TAG, "HTTP code: " + String.valueOf(response.code()));
                if (response.isSuccessful() && response.body().getEvents() != null) {
                    liveData.setValue(response.body().getEvents());
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
        return liveData;
    }

    public MutableLiveData<List<Teams>> getAllTeamsInLeague(@Nullable String leagueId) {
        MutableLiveData<List<Teams>> liveData = new MutableLiveData<>();
        ApiService service = ApiClient.getApiClient().create(ApiService.class);
        Call<TeamResponse> api = service.getAllTeams(leagueId);
        api.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                Log.d(TAG, response.toString());
                Log.d(TAG, "HTTP code: " + String.valueOf(response.code()));
                if (response.isSuccessful() && response.body().getTeams() != null) {
                    liveData.setValue(response.body().getTeams());
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
        return liveData;
    }

    public MutableLiveData<List<Leagues>> getLeagueDetail(@Nullable String leagueId) {
        MutableLiveData<List<Leagues>> liveData = new MutableLiveData<>();
        ApiService service = ApiClient.getApiClient().create(ApiService.class);
        Call<LeagueResponse> api = service.getLeagueDetail(leagueId);
        api.enqueue(new Callback<LeagueResponse>() {
            @Override
            public void onResponse(Call<LeagueResponse> call, Response<LeagueResponse> response) {
                Log.d(TAG, response.toString());
                Log.d(TAG, "HTTP code: " + String.valueOf(response.code()));
                if (response.isSuccessful() && response.body().getLeagues() != null) {
                    liveData.setValue(response.body().getLeagues());
                }
            }

            @Override
            public void onFailure(Call<LeagueResponse> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
        return liveData;
    }
}
