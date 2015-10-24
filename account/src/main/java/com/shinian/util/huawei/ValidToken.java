package com.shinian.util.huawei;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;

import com.alibaba.fastjson.JSON;


public class ValidToken extends HttpServlet
{
	
    
    
    public ValidToken()
    {
        super();
    }
    
    // 验证accessToken地址
    public static final String VALID_TOKEN_ADDR = "https://api.vmall.com/rest.php";
    
    /**
     * accessToken是敏感数据，不推荐使用GET方式
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException
    {
    }
    

    
    // 仅接受POST请求
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException
    {
        String retCode = "500";
        request.setCharacterEncoding("UTF-8");
        String accessToken = request.getParameter("accessToken");
        if (null != accessToken && !"".equals(accessToken))
        {
            // https请求
            String postBody = "nsp_svc=OpenUP.User.getInfo&nsp_ts=";
            postBody += String.valueOf(System.currentTimeMillis() / 1000);
            
            postBody += "&access_token=" + java.net.URLEncoder.encode(accessToken, "utf-8");
            
            HttpRequester sendHttps = new HttpRequester(); // 发送请求
            HttpResponse receiveHttps = null; // 收到的响应
            
            receiveHttps = sendHttps.post(VALID_TOKEN_ADDR, new HashMap<String, String>(), postBody);
            
            String retMsg = receiveHttps.getContent();
            UserInfo userInfo = JSON.parseObject(retMsg, UserInfo.class);
            
            if (null != userInfo && null != userInfo.getUserID() && !"".equals(userInfo.getUserID()))
            {
                retCode = "200";
            }
        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        out.print(retCode);
        out.close();
    }
    
    public Map<String, Object> getValue(HttpServletRequest request)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        
        List<String> longcodeArray = new ArrayList<String>();
        List<String> messageArray = new ArrayList<String>();
        try
        {
            
            String keyandValue = "";
            String key = "";
            String value = "";
            Iterator<String> it = request.getParameterMap().keySet().iterator();
            while (it.hasNext())
            {
                key = it.next();
                value = ((Object[])(request.getParameterMap().get(key)))[0].toString();
                // value = value.getBytes(value,"UTF-8");
                
                keyandValue = key + "=" + value;
                map.put(key, value);
                System.out.println(key + "= " + value);
            }
            
        }
        catch (Exception e)
        {
            return null;
        }
        
        return map;
    }
    
}
