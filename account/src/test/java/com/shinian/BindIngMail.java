package com.shinian;

public class BindIngMail {

	public static void main(String[] args) {
		String url = "http://127.0.0.1:8080/account/bindingMail";
//		String param = "{'ts':'1025451','username':'mjf@sina.com','password':'111111'}";
		String param = "{'ts':'1025451','email':'yinhuawei@163.com','bindingmail':'wyinhuawei@163.com'}";
		String ret = Util.doPost(url,param);
		System.out.println("ret:" + ret);
	}
}
