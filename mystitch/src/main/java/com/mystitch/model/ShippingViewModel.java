package com.mystitch.model;

import java.util.List;

import org.zkoss.bind.annotation.Init;

public class ShippingViewModel {
	
	
    private String stitchMethod;
	
	public List<String> getMethod() {
        return ShippingModel.getMethod();
    }

	@Init
	public void init(){
		setStitchMethod("Select Shipping Method");
	}
	
	public String getStitchMethod() {
		return stitchMethod;
	}

	public void setStitchMethod(String stitchMethod) {
		this.stitchMethod = stitchMethod;
	}

	
}
