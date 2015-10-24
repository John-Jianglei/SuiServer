package com.shinian;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

 
public class HttpUtil {
	
//	public final static String accServiceUrl = "http://127.0.0.1:8080/account/";
	
	public static String doPost(String url, String str) {
		String resp = "";

		HttpClient client = null;
		PostMethod method = null;

		try { 
			client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(20000);
			method = new PostMethod(url);
			method.setRequestEntity(new StringRequestEntity(str, "application/x-www-form-urlencoded", "UTF-8"));

			method.setRequestHeader("Accept", "*/*");
			method.setRequestHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; MOBIM)");
			method.setRequestHeader("Content-Length", String.valueOf(str.getBytes().length));
			method.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");

			client.executeMethod(method);

			int httpStatusCode = method.getStatusCode();
			resp = method.getResponseBodyAsString();

			if (httpStatusCode != HttpStatus.SC_OK) {
				System.out.println("post failed:" + httpStatusCode + "," + url);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (method != null)
				method.releaseConnection();
		}
		System.out.println("post success:" + url + "," + str + "," + resp);
		
		return resp;
	}
}
