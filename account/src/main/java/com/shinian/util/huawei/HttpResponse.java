/*
 * 文 件 名: HttpResponse.java
 * 版 权: Huawei Technologies Co., Ltd. Copyright YYYY-YYYY, All rights reserved
 * 描 述: <描述>
 * 修 改 人: w00171845
 * 修改时间: 2012-8-7
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.shinian.util.huawei;

/**
 * Http 响应对象 Response
 * 
 * @author w00171845
 * @version [版本号, 2012-8-7]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class HttpResponse
{
    public final static int OK = 200;
    
    protected int defaultPort;
    
    protected String file;
    
    protected String host;
    
    protected String path;
    
    protected int port;
    
    protected String protocol;
    
    protected String query;
    
    protected String ref;
    
    protected String userInfo;
    
    protected String contentEncoding;
    
    protected String content;
    
    protected String contentType;
    
    protected int code;
    
    protected String message;
    
    protected String method;
    
    protected int connectTimeout;
    
    protected int readTimeout;
    
    public String getContent()
    {
        return content;
    }
    
    public String getContentType()
    {
        return contentType;
    }
    
    public int getCode()
    {
        return code;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public String getContentEncoding()
    {
        return contentEncoding;
    }
    
    public String getMethod()
    {
        return method;
    }
    
    public int getConnectTimeout()
    {
        return connectTimeout;
    }
    
    public int getReadTimeout()
    {
        return readTimeout;
    }
    
    public int getDefaultPort()
    {
        return defaultPort;
    }
    
    public String getFile()
    {
        return file;
    }
    
    public String getHost()
    {
        return host;
    }
    
    public String getPath()
    {
        return path;
    }
    
    public int getPort()
    {
        return port;
    }
    
    public String getProtocol()
    {
        return protocol;
    }
    
    public String getQuery()
    {
        return query;
    }
    
    public String getRef()
    {
        return ref;
    }
    
    public String getUserInfo()
    {
        return userInfo;
    }
}
