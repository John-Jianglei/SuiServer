package com.shinian;


public class login {
	public static void main(String[] args) {
		String url = "http://127.0.0.1:8080/account/login";
		String param = "{\"data\":{\"email\":\"yinhuawei@163.com\",\"password\":\"123456\"},\"ts\":1}";
		String ret = Util.doPost(url,param);
		System.out.println("ret:" + ret);
		
	}
}
