package com.vinspier.utils;

import java.util.UUID;

public class UUIDUtils {
	/**
	 * 随机生成id
	 * @return
	 */
	public static String getId(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	/**
	 * 随机生成激活码
	 * @return
	 */
	public static String getCode(){
		return getId();
	}

	public static String resetPictureName(){
		return getId().substring(0,20);
	}

}
