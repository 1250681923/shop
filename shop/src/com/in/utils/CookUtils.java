package com.in.utils;

import javax.servlet.http.Cookie;

public class CookUtils {
	/**
	 * ͨ��������cookie�����ȡָ����cookie
	 * @param name cookie����
	 * @param cookies  cookie����
	 * @return
	 */
	public static Cookie getCookieByName(String name, Cookie[] cookies) {
		if(cookies!=null){
			for (Cookie c : cookies) {
				//ͨ�����ƻ�ȡ
				if(name.equals(c.getName())){
					//����
					return c;
				}
			}
		}
		return null;
	}
}
