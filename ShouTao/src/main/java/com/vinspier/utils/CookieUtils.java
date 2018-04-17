package com.vinspier.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {
	/**
	 * 通过名称在cookie数组获取指定的cookie
	 * @param cookieName cookie名称
	 * @param cookies  cookie数组
	 * @return
	 */
	public static Cookie getCookie(Cookie[] cookies,String cookieName) {
		if(cookieName == null){
			return null;
		}
		if(cookies!=null){
			for (Cookie c : cookies) {
				//通过名称获取
				if(cookieName.equals(c.getName())){
					//返回
					return c;
				}
			}
		}
		return null;
	}
}
