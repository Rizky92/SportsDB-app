package com.rizky92.sportsdbapp.api.response.event;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class EventResponse{

	@SerializedName("events")
	private List<Events> events;

	public void setEvents(List<Events> events){
		this.events = events;
	}

	public List<Events> getEvents(){
		return events;
	}

	@Override
 	public String toString(){
		return 
			"EventResponse{" + 
			"events = '" + events + '\'' + 
			"}";
		}
}