package com.security.mvc.user.service;
import java.util.List;

import com.security.mvc.user.entity.User;

/**@Description: Useræœ�åŠ¡æŽ¥å�£
 *  @version Revision: V1.0 
 *  @author GuoJun mailto: guojun828@126.com
 */

public interface UserService {
	public String add(User user);

	public String login(User user);

	public int insertRow(User user);

	public List<User> getList();

	public int deleteRow(int id);

	public User getRowById(int id);

	public int updateRow(User user);
}
