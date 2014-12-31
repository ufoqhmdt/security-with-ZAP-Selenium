package com.security.mvc.util;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;

import com.security.mvc.exceptions.BusinessException;


public abstract class HibernateEntityManagerImpl<T> implements
		HibernateEntityManager<T> {

	protected HibernateTemplate template;

	public HibernateEntityManagerImpl() {
	}

	public HibernateEntityManagerImpl(HibernateTemplate template) {
		this.template = template;
	}

	@Resource
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public T findById(Class<T> c, long id) throws BusinessException {
		// template.setCacheQueries(true);
		return template.load(c, id);
	}

	public T loadEntity(final Class<T> c, final Serializable id)
			throws BusinessException {
		template.setCacheQueries(true);
		return (T) template.load(c, id);
	}

	public T loadEntityById(final Serializable id) throws BusinessException {

		return (T) loadEntity(getEntityType(), id);
	}

	public void save(Object entity) throws BusinessException {

		template.save(entity);
	}

	public void update(Object entity) throws BusinessException {
		// template.setFlushMode(HibernateTemplate.FLUSH_EAGER);
		template.update(entity);
	}

	public void saveOrUpdate(Object entity) throws BusinessException {
		// template.setFlushMode(HibernateTemplate.FLUSH_EAGER);
		template.saveOrUpdate(entity);
	}

	public void remove(Object entity) throws BusinessException {
		template.delete(entity);
	}

	public List<?> executeHql(String queryString) throws BusinessException {
		// template.setCacheQueries(true);
		return template.find(queryString);
	}

	public List<?> executeSQL(String sqlString) throws BusinessException {
		return template.getSessionFactory().getCurrentSession()
				.createSQLQuery(sqlString).list();
	}

	public void executeUpdate(String sqlString) throws BusinessException {
		template.getSessionFactory().getCurrentSession()
				.createSQLQuery(sqlString).executeUpdate();
	}

	public void deleteObjectById(Serializable id) throws BusinessException {
		template.getSessionFactory().getCurrentSession()
				.delete(loadEntityById(id));
	}

	/**
	 * 下面三个方法暂时留下作后续扩�?
	 */

	public List<T> loadAll(Class<T> c) throws BusinessException {
		return template.loadAll(c);
	}

}
