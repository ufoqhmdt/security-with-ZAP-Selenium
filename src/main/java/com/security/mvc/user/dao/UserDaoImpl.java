package com.security.mvc.user.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.security.mvc.common.Constants;
import com.security.mvc.exceptions.BusinessException;
import com.security.mvc.user.entity.User;
import com.security.mvc.util.HibernateEntityManagerImpl;

/**
 * @Description: TODO
 * @author UFO
 */
@Repository
public class UserDaoImpl extends HibernateEntityManagerImpl<User> implements
		UserDao {

	@Override
	public String addUser(User user) {
		try {
			super.save(user);
			return Constants.ReturnCode.OPER_SUCCESS;
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		return Constants.ReturnCode.OPER_FAIL;
	}

	@Override
	public String removeUserByID(Integer userID) {
		return null;
	}

	@Override
	public String updateUser(User user) {
		return null;
	}

	@Override
	public List<User> findAllUser() {
		return null;
	}

	@Override
	public User findUserByID(Integer userID) {

		return null;
	}

	@Override
	public User findUserByUserName(String userName) {
		User user = null;
		try {
			List<User> userList = (List<User>) super
					.executeHql("from User where userName = '" + userName + "'");
			// List<User> userList = super.loadAll(User.class);
			if (userList.size() != 0) {
				user = (User) userList.get(0);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		// try {
		// user = super.getEntity(User.class, 1l);
		// } catch (BusinessException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return user;
	}

	@Override
	public User getEntity(Class<User> c, Serializable id)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(User entity) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(User entity) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(User entity) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<User> getEntityType() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> loadAll(Class<User> c) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
