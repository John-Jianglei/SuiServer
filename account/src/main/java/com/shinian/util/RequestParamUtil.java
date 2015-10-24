package com.shinian.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestParamUtil {
	public static String getRequestContents(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		BufferedInputStream bufferedInputStream = null;
		StringBuffer returnStr = new StringBuffer();
		try {
			byte[] bytes = new byte[1024];
				
			bufferedInputStream = new BufferedInputStream(request.getInputStream());
			int iRead;
			while ((iRead = bufferedInputStream.read(bytes)) != -1) {
				returnStr.append(new String(bytes, 0, iRead, "UTF-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedInputStream != null) {
					bufferedInputStream.close();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return returnStr.toString();
	}
	
	
}