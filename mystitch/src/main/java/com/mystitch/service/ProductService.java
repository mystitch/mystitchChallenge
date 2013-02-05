package com.mystitch.service;

import java.util.List;

import com.mystitch.exception.ProductServiceException;
import com.mystitch.model.ProductItem;
import com.mystitch.model.ProductOrder;

public interface ProductService {

	
	
	List<ProductItem> getProductItemList(String productCode, String productCategory) throws ProductServiceException;
	
	void processProductOrder(ProductOrder order) throws ProductServiceException;
	
	double calculateOrderPrice(ProductOrder order) throws ProductServiceException;
	
	void cachedProductOrder(ProductOrder order)  throws ProductServiceException;
	
	ProductItem getProductItem(long itemId)  throws ProductServiceException;
	
	
}
