package com.mystitch.model;

import java.util.ArrayList;
import java.util.List;

public class CountryListModel {
	
	private static List<String> country = new ArrayList<String>();
	
	
	static{		
		country.add("Australia");
		country.add("United Kingdom");
		country.add("United States");
		country.add("Canada");
		country.add("France");
		};
	
	public static final List<String> getCountry(){
		return country;
	}
	
}

