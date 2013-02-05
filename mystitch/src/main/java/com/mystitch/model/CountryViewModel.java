package com.mystitch.model;

import java.util.List;

import org.zkoss.bind.annotation.Init;

public class CountryViewModel {

	private String stitchCountry;
	
	public List<String> getCountry() {
        return CountryListModel.getCountry();
    }

	@Init
	public void init(){
		setStitchCountry("Australia");
	}

	public String getStitchCountry() {
		return stitchCountry;
	}

	public void setStitchCountry(String stitchCountry) {
		this.stitchCountry = stitchCountry;
	}
	
	
	
	
	
}
