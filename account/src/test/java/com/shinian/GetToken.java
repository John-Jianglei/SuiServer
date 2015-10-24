package com.shinian;

public class GetToken {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "http://127.0.0.1:8080/account/checkToken?uid=0&token=0";
		String param = "{\"data\":{\"uid\":\"12\",\"token\":\"95908d38d6be69659fa83ec77bad945f\"},\"ts\":1}";
		String ret = Util.doPost(url,param);
		System.out.println("ret:" + ret);

	}

}
