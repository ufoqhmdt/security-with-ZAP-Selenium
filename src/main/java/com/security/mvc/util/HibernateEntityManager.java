package com.security.mvc.util;

import java.io.Serializable;
import java.util.List;

import com.security.mvc.exceptions.BusinessException;


public interface HibernateEntityManager<T> {

	public T findById(Class<T> c, long idValue) throws BusinessException;

	public T loadEntity(final Class<T> c, final Serializable id) throws BusinessException ;

	public T getEntity(Class<T> c, Serializable id) throws BusinessException;

	public T loadEntityById(final Serializable id) throws BusinessException;
	
	public void update(Object entity) throws BusinessException;
	
	public List<T> loadAll(Class<T> c) throws BusinessException;

	public void save(T entity) throws BusinessException;

	public void saveOrUpdate(T entity) throws BusinessException;

	public void deleteObjectById(Serializable id) throws BusinessException;

	public void remove(T entity) throws BusinessException;

	public List<?> executeHql(String queryString) throws BusinessException;

	public List<?> executeSQL(String sqlString) throws BusinessException;
	
	public void executeUpdate(String sqlString) throws BusinessException;
	
	public abstract Class<T> getEntityType() throws BusinessException;

}
