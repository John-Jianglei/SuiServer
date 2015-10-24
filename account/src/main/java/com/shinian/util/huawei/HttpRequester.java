/*
 * 文 件 名: HttpRequester.java
 * 版 权: Huawei Technologies Co., Ltd. Copyright YYYY-YYYY, All rights reserved
 * 描 述: <描述>
 * 修 改 人: w00171845
 * 修改时间: 2012-8-7
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.shinian.util.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 处理Http请求相关的类
 * 
 * @author w00171845
 * @version [版本号, 2012-8-7]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class HttpRequester
{
    
    
    public final static int HTTP_OK = 200;
    
    public final static int HTTP_TIMEOUT = 408;
    
    private final static String METHOD_POST = "POST";
    
    private final static String METHOD_GET = "GET";
    
    private String defaultContentEncoding = "UTF-8";
    
    private static SSLSocketFactory ssf;
    
    /**
     * 忽视证书HostName
     */
    private static HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier()
    {
        @Override
        public boolean verify(String s, SSLSession sslsession)
        {
            return true;
        }
    };
    
    /**
     * 忽视证书 Certification
     */
    private static TrustManager ignoreCertificationTrustManger = new X509TrustManager()
    {
        private X509Certificate[] certificates;
        
        @Override
        public void checkClientTrusted(X509Certificate[] certificates, String authType)
            throws CertificateException
        {
            if (this.certificates == null)
            {
                this.certificates = certificates;
            }
            
        }
        
        @Override
        public void checkServerTrusted(X509Certificate[] ax509certificate, String s)
            throws CertificateException
        {
            if (this.certificates == null)
            {
                this.certificates = ax509certificate;
            }
        }
        
        @Override
        public X509Certificate[] getAcceptedIssuers()
        {
            // TODO Auto-generated method stub
            return null;
        }
    };
    
    static
    {
        /*
         * use ignore host name verifier
         */
        HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
        
        // Prepare SSL Context
        try
        {
            TrustManager[] tm = {ignoreCertificationTrustManger};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            ssf = sslContext.getSocketFactory();
        }
        catch (Exception e)
        {
        }
    }
    
    public HttpRequester()
    {
    }
    
    /**
     * 发送GET请求
     * 
     * @param urlString 目标地址url
     * @param properties head属性
     * @param parameters get携带的参数值
     * @return
     * @throws IOException [参数说明]
     * 
     * @return HttpResponse 得到的response响应对象
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public HttpResponse get(String urlString, Map<String, String> properties, String parameters)
        throws IOException
    {
        StringBuilder sb = new StringBuilder();
        sb.append(urlString);
        if (parameters != null)
        {
            if (urlString.indexOf('?') > 0)
            {
                sb.append('&');
                sb.append(parameters);
                // urlString += '&' + parameters;
            }
            else
            {
                sb.append('?');
                sb.append(parameters);
                // urlString += '?' + parameters;
            }
        }
        
        urlString = sb.toString();
       
        
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        
        if (urlString.indexOf("https://") >= 0)
        {
            ((HttpsURLConnection)urlConnection).setSSLSocketFactory(ssf);
        }
        
        urlConnection.setRequestMethod(METHOD_GET);
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);
        
        if (properties != null)
        {
            // CHECKSTYLE:OFF 该问题为非问题 -h00193325
            for (Entry<String, String> value : properties.entrySet())
            // CHECKSTYLE:ON
            {
                urlConnection.addRequestProperty(value.getKey(), value.getValue());
            }
        }
        
        return this.makeContent(urlConnection);
    }
    
    /**
     * 发送POST请求
     * 
     * @param urlString
     * @param properties
     * @param parameters
     * @return
     * @throws IOException [参数说明]
     * 
     * @return HttpResponse [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public HttpResponse post(String urlString, Map<String, String> properties, String parameters)
        throws IOException
    {
        
        OutputStream out = null;
        HttpURLConnection urlConnection = null;
        try
        {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection)url.openConnection();
            
            if (urlString.indexOf("https://") >= 0)
            {
                ((HttpsURLConnection)urlConnection).setSSLSocketFactory(ssf);
            }
            
            urlConnection.setRequestMethod(METHOD_POST);
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            
            if (properties != null)
            {
                // CHECKSTYLE:OFF 该问题为非问题 -h00193325
                for (Entry<String, String> value : properties.entrySet())
                // CHECKSTYLE:ON
                {
                    urlConnection.addRequestProperty(value.getKey(), value.getValue());
                }
            }
            
            out = urlConnection.getOutputStream();
            if (parameters != null)
            {
                out.write(parameters.getBytes(defaultContentEncoding));
            }
        }
        catch (Exception e)
        {
        }
        finally
        {
            try
            {
                if (null != out)
                {
                    out.flush();
                    out.close();
                }
            }
            catch (Exception e2)
            {
            }
            
            try
            {
                if (null != urlConnection)
                {
                    urlConnection.disconnect();
                }
            }
            catch (Exception e2)
            {
            }
        }
        if (null == urlConnection)
        {
            return null;
        }
        
        return this.makeContent(urlConnection);
    }
    
    /**
     * 得到响应对象
     */
    private HttpResponse makeContent(HttpURLConnection urlConnection)
    {
        HttpResponse httpResponser = new HttpResponse();
        InputStream in = null;
        BufferedReader bufferedReader = null;
        
        try
        {
            in = urlConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(in));
            StringBuilder temp = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null)
            {
                temp.append(line).append('\n');
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            
            String encoding = urlConnection.getContentEncoding();
            if (encoding == null)
            {
                encoding = this.defaultContentEncoding;
            }
            
            URL url = urlConnection.getURL();
            
            httpResponser.defaultPort = url.getDefaultPort();
            httpResponser.file = url.getFile();
            httpResponser.host = url.getHost();
            httpResponser.path = url.getPath();
            httpResponser.port = url.getPort();
            httpResponser.protocol = url.getProtocol();
            httpResponser.query = url.getQuery();
            httpResponser.ref = url.getRef();
            httpResponser.userInfo = url.getUserInfo();
            
            httpResponser.content = new String(temp.toString().getBytes(), encoding);
            httpResponser.contentEncoding = encoding;
            httpResponser.code = urlConnection.getResponseCode();
            httpResponser.message = urlConnection.getResponseMessage();
            httpResponser.contentType = urlConnection.getContentType();
            httpResponser.method = urlConnection.getRequestMethod();
            httpResponser.connectTimeout = urlConnection.getConnectTimeout();
            httpResponser.readTimeout = urlConnection.getReadTimeout();
        }
        catch (Exception e)
        {
        }
        finally
        {
            closeBufferReader(bufferedReader);
            try
            {
                if (null != in)
                {
                    in.close();
                }
            }
            catch (Exception e2)
            {
            }
        }
        
        return httpResponser;
    }
    
    private void closeBufferReader(BufferedReader bufferedReader)
    {
        try
        {
            if (null != bufferedReader)
            {
                bufferedReader.close();
            }
        }
        catch (Exception e2)
        {
        }
    }
    
    /**
     * 默认的响应字符集
     */
    public String getDefaultContentEncoding()
    {
        return this.defaultContentEncoding;
    }
    
    /**
     * 设置默认的响应字符集
     */
    public void setDefaultContentEncoding(String defaultContentEncoding)
    {
        this.defaultContentEncoding = defaultContentEncoding;
    }
    
}
