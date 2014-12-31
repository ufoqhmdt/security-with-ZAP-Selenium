/**
 * 
 */
package com.security.mvc.user.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.mvc.common.Constants;
import com.security.mvc.user.dao.UserDao;
import com.security.mvc.user.entity.User;

/**
 * @Description: User服务实现类
 * @version Revision: V1.0
 * @author GuoJun mailto: guojun828@126.com
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	SessionFactory sessionFactory;

	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public String add(User user) {
		return userDao.addUser(user);
	}

	@Override
	public String login(User user) {
		User loginUser = userDao.findUserByUserName(user.getUserName());
		if (loginUser != null
				&& loginUser.getPassword().equals(user.getPassword())) {
			return Constants.ReturnCode.LOGIN_SUCCESS;
		}
		return Constants.ReturnCode.LOGIN_FAIL;
	}

	@Override
	@Transactional
	public int insertRow(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(user);
		tx.commit();
		Serializable id = session.getIdentifier(user);
		session.close();
		return (Integer) id;
	}

	@Override
	public List<User> getList() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<User> userList = session.createQuery("from user").list();
		session.close();
		return userList;
	}

	@Override
	public User getRowById(int id) {
		Session session = sessionFactory.openSession();
		User user = (User) session.load(User.class, id);
		return user;
	}

	@Override
	public int updateRow(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(user);
		tx.commit();
		Serializable id = session.getIdentifier(user);
		session.close();
		return (Integer) id;
	}

	@Override
	public int deleteRow(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User user = (User) session.load(User.class, id);
		session.delete(user);
		tx.commit();
		Serializable ids = session.getIdentifier(user);
		session.close();
		return (Integer) ids;
	}

}
