package com.mystitch.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mystitch.model.ProductItem;

public interface ProductItemDao {

	List<ProductItem> retrieveProductItemList(String productCode) throws DataAccessException;
	
	ProductItem getProductItem(long itemId) throws DataAccessException;
}
