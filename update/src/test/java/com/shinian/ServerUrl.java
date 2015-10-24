package com.shinian;


public class ServerUrl {
	public static void main(String[] args) {
		String url = "http://127.0.0.1/update/getServerUrl";
		String param = "{\"data\":{\"channelId\":\"3\",\"serverNo\":\"1\"},\"ts\":1}";
		String ret = Util.doPost(url,param);
		System.out.println("ret:" + ret);
		
	}
}
