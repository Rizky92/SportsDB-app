package com.rizky92.sportsdbapp.api.client;

import com.rizky92.sportsdbapp.api.response.country.CountryResponse;
import com.rizky92.sportsdbapp.api.response.event.EventResponse;
import com.rizky92.sportsdbapp.api.response.league.LeagueResponse;
import com.rizky92.sportsdbapp.api.response.team.TeamResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    // get all soccer league
    @GET("search_all_leagues.php")
    Call<CountryResponse> getAllLeagues(
            @Query("s") String sport
    );

    // get league detail
    @GET("lookupleague.php")
    Call<LeagueResponse> getLeagueDetail(
            @Query("id") String leagueId
    );

    // get all teams by league
    @GET("lookup_all_teams.php")
    Call<TeamResponse> getAllTeams(
            @Query("id") String leagueId
    );

    // get league next match
    @GET("eventsnextleague.php")
    Call<EventResponse> getNextMatchEvent(
            @Query("id") String leagueId
    );

    // get league previous match
    @GET("eventspastleague.php")
    Call<EventResponse> getPreviousMatchEvent(
            @Query("id") String leagueId
    );

    // get team logo
    @GET("lookupteam.php")
    Call<TeamResponse> getTeamLogo(
            @Query("id") String teamId
    );
}
