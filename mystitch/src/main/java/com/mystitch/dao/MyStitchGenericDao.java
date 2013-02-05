package com.mystitch.dao;

import java.io.Serializable;
import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

public abstract class MyStitchGenericDao<M extends Serializable> {
	
	private static Logger logger = Logger.getLogger(MyStitchGenericDao.class);
		
	private static final long serialVersionUID = -1035187014434957063L;
	
	private EntityManagerFactory entityManagerFactory;
	
	private EntityManager entityManager;
	
	private Class<M> modelClass;
	
	public void config(final Class<M> model, EntityManagerFactory emf, EntityManager em ){
		modelClass = model;
		entityManagerFactory = emf;
		entityManager = em;
	}
	
	@Transactional
	public M findById(final long id) throws DataAccessException{
		
		M modelBean = entityManager.find(modelClass, id);
		//entityManager.refresh(modelBean);
		return  modelBean;
		//return entityManager.createQuery("from " + modelClass.getAnnotation(Table.class).name(), modelClass).getSingleResult();
	
	}
	
	@SuppressWarnings("unchecked")
	public List<M> queryByField(final String field, final String value)throws DataAccessException{
		
		javax.persistence.Query query = entityManager.createQuery("from " + modelClass.getSimpleName() 
				+ " m where m." + field +" 	like '%" + value + "%' " );
		//.setFirstResult(5).setMaxResults(100).getResultList();
		//query.setFirstResult(50);
		query.setMaxResults(100);
		
		return query.getResultList();
		
		
	}
	
	
	
	
	public List<M> findAll() throws DataAccessException{
		return entityManager.createQuery("from " + modelClass.getSimpleName(), modelClass).getResultList();
		
	}
	
	public void save(final M modelBean ) throws PersistenceException{   
		//entityManager = entityManagerFactory.createEntityManager();	
		
		logger.debug("starting persistent trasaction...");
		entityManager.getTransaction().begin();
		entityManager.persist(modelBean);
		entityManager.getTransaction().commit();
		
		logger.debug("committed persistent trasaction...");
		
    }
	   
    public void update( final M modelBean )throws DataAccessException{
    	
    	//entityManager = entityManagerFactory.createEntityManager();	
    	entityManager.getTransaction().begin();    	
    	entityManager.merge(modelBean);
		entityManager.getTransaction().commit();    
       
    }
	   
   public void deleteById( final long id) throws DataAccessException{
	   //entityManager = entityManagerFactory.createEntityManager();	
	   final M modelBean = findById(id);
	   
	   entityManager.getTransaction().begin();
	   entityManager.remove(modelBean);
	   entityManager.getTransaction().commit();  
	  
   }
	 
	

}
