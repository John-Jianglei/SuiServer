//武将分解
package com.shinian;

public class IF1019 {

	public static void main(String[] args) {
		String url = "http://127.0.0.1:8080/game/game?acid=1019&ver=1.0.0";
		String param = "{\"data\":{\"uid\":\"1-1\",\"npcList\":[{\"id\":\"9\"},{\"id\":\"8\"}],\"token\":\"d2d5765a2dc10f3d9591a4f7e9a0dde7\"},\"ts\":1}";
		
		String ret = HttpUtil.doPost(url,param);
		System.out.println("ret:" + ret);
	}
}
