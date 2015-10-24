package com.shinian;



public class IF1001 {
	public static void main(String[] args) {
		String url = "http://127.0.0.1/game/game?acid=1001&ver=1.0.0";
		String param = "{\"data\":{\"uid\":\"1-1\",\"token\":\"d2d5765a2dc10f3d9591a4f7e9a0dde7\",\"type\":0,\"desc\":\"fkdsfld\"},\"ts\":1}";
		String ret = HttpUtil.doPost(url,param);
		System.out.println("ret:" + ret);
		
		 
	}
}
