/**
 * 工具类
 */
package com.shinian.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


public class Util {

	private static final String METHOD_POST = "POST";  
	private static final String DEFAULT_CHARSET = "utf-8";  
     
    public static String doPost(String url, String params) throws Exception {  
    	String ctype = "application/x-www-form-urlencoded";
         byte[] content = {};  
         if(params != null){   
             content = params.getBytes(DEFAULT_CHARSET);  
        }  
          
         return doPost(url, ctype, content, 30000, 30000);  
     }  
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            System.out.println(" urlNameString "+ urlNameString );
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

     public static String doPost(String url, String ctype, byte[] content,int connectTimeout,int readTimeout) throws Exception {  
         HttpsURLConnection conn = null;  
         OutputStream out = null;  
         String rsp = null;  
         try {  
             try{  
                 SSLContext ctx = SSLContext.getInstance("TLS");  
                 ctx.init(new KeyManager[0], new TrustManager[] {new DefaultTrustManager()}, new SecureRandom());  
                 SSLContext.setDefault(ctx);  
   
                 conn = getConnection(new URL(url), METHOD_POST, ctype);   
                conn.setHostnameVerifier(new HostnameVerifier() {
					
                     @Override  
                     public boolean verify(String hostname, SSLSession session) {  
                         return true;  
                     }  
                 });  
                 conn.setConnectTimeout(connectTimeout);  
                conn.setReadTimeout(readTimeout);  
             }catch(Exception e){  
                 throw e;  
             }  
             try{  
                 out = conn.getOutputStream();  
                 out.write(content);  
                 rsp = getResponseAsString(conn);  
             }catch(IOException e){  
                
                 throw e;  
            }  
               
         }finally {  
             if (out != null) {  
                 out.close();  
            }  
             if (conn != null) {  
                 conn.disconnect();  
             }  
         }  
           
         return rsp;  
     }  
   
     private static class DefaultTrustManager implements X509TrustManager {  
   
         @Override  
         public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}  
   
         @Override  
         public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}  
   
         @Override  
         public X509Certificate[] getAcceptedIssuers() {  
             return null;  
         }  
   
     }  
       
    private static HttpsURLConnection getConnection(URL url, String method, String ctype)  
             throws IOException {  
         HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();  
        conn.setRequestMethod(method);  
         conn.setDoInput(true);  
         conn.setDoOutput(true);  
         conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");  
         conn.setRequestProperty("User-Agent", "stargate");  
         conn.setRequestProperty("Content-Type", ctype);  
         return conn;  
     }  
   
     protected static String getResponseAsString(HttpURLConnection conn) throws IOException {  
         String charset = getResponseCharset(conn.getContentType());  
         InputStream es =  conn.getErrorStream();  
         if (es == null) {  
             return getStreamAsString(conn.getInputStream(), charset);  
         } else {  
             return getStreamAsString(es, charset);  
//             if (StringUtils.isEmpty(msg)) {  
//                 throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());  
//             } else {  
//                 throw new IOException(msg);  
//             }
         }  
     }  
   
     private static String getStreamAsString(InputStream stream, String charset) throws IOException {  
         try {  
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));  
             StringWriter writer = new StringWriter();  
   
             char[] chars = new char[256];  
             int count = 0;  
             while ((count = reader.read(chars)) > 0) {  
                 writer.write(chars, 0, count);  
             }  
   
             return writer.toString();  
         } finally {  
             if (stream != null) {  
                 stream.close();  
             }  
         }  
     }  
   
     private static String getResponseCharset(String ctype) {  
         String charset = DEFAULT_CHARSET;  
   
         if (!StringUtils.isEmpty(ctype)) {  
             String[] params = ctype.split(";");  
            for (String param : params) {  
                 param = param.trim();  
                 if (param.startsWith("charset")) {  
                     String[] pair = param.split("=", 2);  
                    if (pair.length == 2) {  
                         if (!StringUtils.isEmpty(pair[1])) {  
                             charset = pair[1].trim();  
                         }  
                     }  
                     break;  
                 }  
             }  
         }  
   
        return charset;  
     }
 

}
