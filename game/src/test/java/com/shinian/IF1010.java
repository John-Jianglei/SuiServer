package com.shinian;

public class IF1010 {
	public static void main(String[] args) {
		String url = "http://127.0.0.1:8080/game/game?acid=1010&ver=1.0.0";
		String param = "{\"data\":{\"offUid\":\"1-11\",\"defUid\":\"1-12\",\"token\":\"d2d5765a2dc10f3d9591a4f7e9a0dde7\"},\"ts\":1}";
		String ret = HttpUtil.doPost(url,param);
		System.out.println("ret:" + ret);

	}
}
