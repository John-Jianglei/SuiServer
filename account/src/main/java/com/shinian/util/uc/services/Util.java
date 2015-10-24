package com.shinian.util.uc.services;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 工具类。
 */
public class Util {
	
	private static  Log Logger = LogFactory.getLog(Util.class);
	/** 
     * MD5 加密 
     */  
    public static String getMD5Str(String str) {  
        MessageDigest messageDigest = null;  
  
        try {  
            messageDigest = MessageDigest.getInstance("MD5");  
            messageDigest.reset();  
            messageDigest.update(str.getBytes("UTF-8"));  
        } catch (NoSuchAlgorithmException e) {  
            Logger.error("NoSuchAlgorithmException caught!");
            System.exit(-1);  
        } catch (UnsupportedEncodingException e) {  
            Logger.error(e.toString());
        }  
  
        byte[] byteArray = messageDigest.digest();  
  
        StringBuffer md5StrBuff = new StringBuffer();  
  
        for (int i = 0; i < byteArray.length; i++) {              
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
            else  
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
        }  
  
        return md5StrBuff.toString();  
    }
    
    
    
    /**
	 * 将Map组装成待签名数据。 待签名的数据必须按照一定的顺序排列
	 * 
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getSignData(Map params) {
		StringBuffer content = new StringBuffer();

		// 按照key做排序
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		int index = 0;
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key) == null ? "" : params.get(key).toString();
			if (value != null) {
				content.append( key + "=" + value);
			} else {
				content.append(key + "=");
			}
		}

		return content.toString();
	}
	
	
}
