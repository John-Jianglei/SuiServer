package com.shinian.util;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ResponseParamUtil {

private static Logger log = Logger.getLogger("game");
	public static void writeJsonMessage(HttpServletResponse response,String jsonStr){		
		try {
			jsonStr = filterSpecialCharacters(jsonStr);
			
			int length = jsonStr.getBytes("utf8").length;
			response.setContentType("text/plain;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentLength(length);
			response.getWriter().write(jsonStr);
			log.info("response:" + jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//过滤调一些特殊的字符：小米手机昵称里面有太阳符号的会导致死机
	private static String filterSpecialCharacters(String str){
		String regEx = "(☀)";
		return Pattern.compile(regEx).matcher(str).replaceAll("");
	}
}