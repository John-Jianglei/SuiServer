//武将进阶测试

package com.shinian;

public class IF1007 {
	public static void main(String[] args) {
		String url = "http://127.0.0.1:8080/game/game?acid=1007&ver=1.0.0";
		//url = "http://120.27.49.196:80/game?acid=1005&ver=1.6.0";		
		String param = "{\"data\":{\"id\":\"3\",\"uid\":\"1-1\",\"comsumedid\":\"15\",\"token\":\"d2d5765a2dc10f3d9591a4f7e9a0dde7\"},\"ts\":1}";
		String ret = HttpUtil.doPost(url,param);
		System.out.println("ret:" + ret);
	}
}
