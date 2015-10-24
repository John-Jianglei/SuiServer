
package com.shinian.util;

import java.util.Map;


public abstract class CommonUtil
{
	
    public static boolean rsaDoCheck(Map<String, Object> params, String sign, String publicKey)
    {
        //获取待签名字符串
        String content = RSA.getSignData(params);
        System.out.println(" content"+ content.length() );
        for (int i = 0; i <  content.length(); i++)
        {
        	System.out.println(" i "+ i + " "+ content.charAt(i));
		}
//    	String content = "amount=0.01&extReserved=1&notifyTime=1418028001689&orderId=A201412081609165774C8ACE&payType=4&productName=钻石&requestId=1-10062-777322-20141208160911&result=0&userName=900086000020830944";
//    	sign = "kJTD3cyQmIU0dW9DrdgeoBuriK/w7c30yGoCyOeOqcc2dv246H1IWmMlbVF5BaDr6PR3DXn0ty1dHopV8thJAA==";
        System.out.println("rsaDoCheck  content "+content);
        System.out.println("rsaDoCheck  sign "+sign);
        System.out.println("rsaDoCheck  publicKey "+publicKey);
        //验签
        return RSA.doCheck(content, sign, publicKey);
    }
    
}
