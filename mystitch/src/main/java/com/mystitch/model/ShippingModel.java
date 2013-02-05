package com.mystitch.model;

import java.util.ArrayList;
import java.util.List;

public class ShippingModel {
	
private static List<String> method = new ArrayList<String>();
	
	
	static{		
		method.add("Select Shipping Method");
		method.add("Free Express Courier(no signature) $0.00");
		method.add("Free eParcel(no signature) $0.00");
		
		};
	
	public static final List<String> getMethod(){
		return method;
	}

}
