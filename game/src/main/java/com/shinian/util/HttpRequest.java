package com.shinian.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class HttpRequest {
	private static Logger log = Logger.getLogger(HttpRequest.class);
	
	public static String doGet(String url) {
		String resp = "";

		HttpClient client = null;
		GetMethod method = null;
		try {
			client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(20000);
				
			method = new GetMethod(url);
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				log.info("doGet - statusCode=" + statusCode + ",url=" + url);
			}

			resp = method.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (method != null)
				method.releaseConnection();
		}
		log.info("doGet success: url=" + url + ",resp=" + resp);
		return resp;
	}
	
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