package com.rizky92.sportsdbapp.api.response.country;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryResponse {

    @SerializedName("countrys")
    private List<Countries> countries;

    public void setCountries(List<Countries> countries) {
        this.countries = countries;
    }

    public List<Countries> getCountries() {
        return countries;
    }

    @Override
    public String toString() {
        return
                "CountryResponse{" +
                        "countrys = '" + countries + '\'' +
                        "}";
    }
}