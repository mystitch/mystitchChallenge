package com.mystitch.service;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mystitch.dao.ProductItemDao;
import com.mystitch.exception.ProductServiceException;
import com.mystitch.model.ProductItem;
import com.mystitch.model.ProductOrder;
@Scope("prototype")
@Service
public class ProductServiceImpl implements ProductService {

	ProductItemDao productItemDao;
	
	@Override
	public List<ProductItem> getProductItemList(String productCode,
			String productCategory) throws ProductServiceException {
			
		return productItemDao.retrieveProductItemList(productCode);
    
		
	}

	@Override
	public void processProductOrder(ProductOrder order)
			throws ProductServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public double calculateOrderPrice(ProductOrder order)
			throws ProductServiceException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void cachedProductOrder(ProductOrder order)
			throws ProductServiceException {
		// TODO Auto-generated method stub
		
	}

	public void setProductItemDao(ProductItemDao productItemDao) {
		this.productItemDao = productItemDao;
	}

	@Override
	public ProductItem getProductItem(long itemId)
			throws ProductServiceException {
		
		return productItemDao.getProductItem(itemId);
		
	}

}
