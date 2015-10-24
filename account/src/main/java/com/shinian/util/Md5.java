package com.shinian.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Md5 {

 

	public static   String getToken(String msg, boolean timeChange) {
		try {

			long current = System.currentTimeMillis();
			int max=999999;
	        int min=100000;
	        Random random = new Random();
	        int s = random.nextInt(max)%(max-min+1) + min;

			MessageDigest md = MessageDigest.getInstance("MD5");
			String values = current+""+s+""+msg;
			md.update(values.getBytes());
			return toHex(md.digest());
//			return (new sun.misc.BASE64Encoder()).encode( toHex(md.digest()).getBytes() ); 

		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	private static String toHex(byte buffer[]) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 15, 16));
		}

		return sb.toString();
	}
}
