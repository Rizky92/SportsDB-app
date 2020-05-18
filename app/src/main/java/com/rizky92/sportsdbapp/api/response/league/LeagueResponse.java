package com.rizky92.sportsdbapp.api.response.league;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeagueResponse {

    @SerializedName("leagues")
    private List<Leagues> leagues;

    public void setLeagues(List<Leagues> leagues) {
        this.leagues = leagues;
    }

    public List<Leagues> getLeagues() {
        return leagues;
    }

    @Override
    public String toString() {
        return
                "CountryResponse{" +
                        "leagues = '" + leagues + '\'' +
                        "}";
    }
}