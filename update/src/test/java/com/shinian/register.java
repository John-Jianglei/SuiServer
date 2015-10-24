package com.shinian;



public class register {
	public static void main(String[] args) {
		String url = "http://127.0.0.1:8080/account/register";
//		String param = "{'ts':'154120','email':'mjf@sina.com','password':'111111','os':'111111'}";
		String param = "{\"data\":{\"email\":\"y7@sina.com\",\"password\":\"111111\"},\"ts\":1}";
		String ret = Util.doPost(url,param);
		System.out.println("ret:" + ret);
	}
}
