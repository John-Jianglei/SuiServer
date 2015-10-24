package com.shinian;

public class UpdatePwd {

	public static void main(String[] args) {
		String url = "http://127.0.0.1:8080/account/updatePwd";
//		String param = "{'ts':'1025451','username':'mjf@sina.com','password':'111111'}";
		String param = "{'ts':'1025451','email':'yinhuawei@163.com','pwd':'123456','newPwd':'234567','affirmPwd':'234567'}";
		String ret = Util.doPost(url,param);
		System.out.println("ret:" + ret);
	}
}
