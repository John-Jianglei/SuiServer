package com.shinian;

public class IF9006 {
	public static void main(String[] args) {
		String url = "http://127.0.0.1:8080/game/game?acid=9006&ver=1.0.0";
		String param = "{\"data\":{\"comId\":\"1\",\"uid\":\"1-5\",\"amount\":\"6\",\"token\":\"d2d5765a2dc10f3d9591a4f7e9a0dde7\"},\"ts\":1}";
		String ret = HttpUtil.doPost(url,param);
		System.out.println("ret:" + ret);
	}
}
