package com.zhang.userserver.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by 廖师兄
 * 2018-03-04 21:41
 */
@Data
@Entity
public class UserInfo {

	@Id
	private String id;

	private String username;

	private String password;

	private String openid;

	private Integer role;
}
