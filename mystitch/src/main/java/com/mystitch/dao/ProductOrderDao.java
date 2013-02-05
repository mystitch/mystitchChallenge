package com.mystitch.dao;

import org.springframework.dao.DataAccessException;

import com.mystitch.model.ProductOrder;

public interface ProductOrderDao {

	
	int saveProductOrder(ProductOrder order) throws DataAccessException;
	
}
