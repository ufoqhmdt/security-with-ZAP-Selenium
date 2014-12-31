package com.security.mvc.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**@Description: User实体
 *  @author UFO
 */
@Entity
@Table(name = "user")
public class User implements Serializable{
	private Integer userID;		//用户ID
	private String userName;	//用户名
	private String password;	//登陆密码
	private String fullName;	//姓名
	private String email;	//Email
	private String mobile;	//手机号
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name = "userID", unique = true, nullable = false)
	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	
	@Column(name = "username", length = 255)
    public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "password", length = 255)
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "fullName", length = 255)
    public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Column(name = "email", length = 255)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "mobile", length = 255)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
