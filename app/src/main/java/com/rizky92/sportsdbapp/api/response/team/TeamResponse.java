package com.rizky92.sportsdbapp.api.response.team;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TeamResponse{

	@SerializedName("teams")
	private List<Teams> teams;

	public void setTeams(List<Teams> teams){
		this.teams = teams;
	}

	public List<Teams> getTeams(){
		return teams;
	}

	@Override
 	public String toString(){
		return 
			"TeamResponse{" + 
			"teams = '" + teams + '\'' + 
			"}";
		}
}