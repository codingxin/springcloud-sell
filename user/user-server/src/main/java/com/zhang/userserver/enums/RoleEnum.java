package com.zhang.userserver.enums;

import lombok.Getter;

/**
 * Created by 廖师兄
 * 2018-03-04 23:23
 */
@Getter
public enum RoleEnum {
	BUYER(1, "买家"),
	SELLER(2, "卖家"),
	;

	private Integer code;

	private String message;

	RoleEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
