package com.mystitch.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.mystitch.model.ProductItem;

public class ProductItemDaoImpl extends MyStitchGenericDao<ProductItem> implements ProductItemDao {

	private static Logger logger = Logger.getLogger(ProductItemDaoImpl.class);
	
	@PersistenceContext(unitName = "myStitchPersistentUnit")
	private EntityManager entityManager;

	public ProductItemDaoImpl(EntityManagerFactory entityManagerFactory) {
		super();		
		entityManager = entityManagerFactory.createEntityManager();
		config(ProductItem.class, entityManagerFactory, entityManager);
	}
	
	@Override
	public List<ProductItem> retrieveProductItemList(String productCode)
			throws DataAccessException {
		    
		    logger.debug("querying by product_code:" + productCode);
		    
			return super.queryByField("productCode", productCode);
			
	
	}

	@Override
	public ProductItem getProductItem(long itemId) throws DataAccessException {
		
		return super.findById(itemId);
		
	}
	
	

}
