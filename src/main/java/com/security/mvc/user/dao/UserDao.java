/**
 * 
 */
package com.security.mvc.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.security.mvc.user.entity.User;

/**@Description: 用户Dao
 *  @author UFO
 */

public interface UserDao {
	public String addUser(User user) ;
	
	public String removeUserByID(Integer userID);
	
	public String updateUser(User user);
	
	public List<User> findAllUser();
	
	public User findUserByID(Integer userID);
	
	public User findUserByUserName(String userName);
}
