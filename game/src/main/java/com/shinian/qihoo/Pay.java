/**
 * 
 */ 

package com.shinian.qihoo;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Pay {

	private static final String VERIFY_URL = "http://msdk.mobilem.360.cn/pay/order_verify.json";
	private static final String VERIFIED = "verified";
	private String _appKey;
	private String _appSecret;
	private PayAppInterface _payApp;
	
	private String _errorMsg = "";

	public Pay(PayAppInterface payApp) {
		this._payApp = payApp;
		this._appKey = payApp.getAppKey();
		this._appSecret = payApp.getAppSecret();
	}

	/**
	 * 处理从360过来的支付订单通知请求
	 * @param params
	 * @return 
	 */
	public String processRequest(HashMap<String, String> params) {

		System.out.println(" 1   processRequest: "+JSON.toJSONString(params) );
		if (!this._isValidRequest(params)) {
			if(!this._errorMsg.isEmpty())
			{
				return this._errorMsg;
			}
			return "invalid request ";
		}

		System.out.println(" 2   processRequest: " );
		
		if (!this._verifyOrder(params)) {
			if(!this._errorMsg.isEmpty())
			{
				return this._errorMsg;
			}
			return "verify failed";
		}

		System.out.println(" 3   processRequest: " );
		
		if (this._payApp.isValidOrder(params)) {
			this._payApp.processOrder(params);
		}
		System.out.println(" 4   processRequest: ");
		return "ok";
	}

	/**
	 * 向360服务器发起请求验证订单是否有效
	 * @param params
	 * @return Boolean 是否有效
	 */
	private Boolean _verifyOrder(HashMap<String, String> params) {
		String url = VERIFY_URL;
		HashMap<String, String> requestParams = new HashMap();

		String field;
		Iterator<String> iterator = params.keySet().iterator();
		while (iterator.hasNext()) {
			field = iterator.next();
			if (field.equals("sign") || field.equals("sign_return")) 
            {
				continue;
			}
			requestParams.put(field, params.get(field));
		}
        requestParams.put("sign", Util.getSign(requestParams, this._appSecret));

		String ret;
		try {
			ret = Util.requestUrl(url, requestParams);
		} catch (IOException e) {
			this._errorMsg = e.toString();
			return false;
		} catch (Exception e1) {
			this._errorMsg = e1.toString();
			return false;
		}

//		JSONParser jsonParser = new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE);
//		JSONObject obj;
		HashMap<String, Object> obj;
		try {
//			obj = (JSONObject) jsonParser.parse(ret);
			obj = JSONObject.parseObject(ret, HashMap.class);
			
			Boolean verified =  obj.get("ret").equals(VERIFIED);
			if(!verified)
			{
				this._errorMsg = obj.get("ret").toString();
			}
			return verified;
		} catch (Exception e) {
			this._errorMsg = e.toString();
			return false;
		}
	}

	/**
	 * 检查request完整性
	 * @param params
	 * @return Boolean
	 */
	private Boolean _isValidRequest(HashMap params) {

		String arrFields[] = {"app_key", "product_id", "app_uid",
			"order_id", "sign_type", "gateway_flag",
			"sign", "sign_return","amount"
		};
		ArrayList fields = new ArrayList(Arrays.asList(arrFields));

		String key;
		String value;
		Iterator iterator = fields.iterator();
		while (iterator.hasNext()) {
			key = (String) iterator.next();
			value = (String) params.get(key);
			if (value == null || value.equals("")) {
				return false;
			}
		}
		
		if(!params.get("app_key").equals(this._appKey)){
			this._errorMsg = "not my order";
			return false;
		}

		String sign = Util.getSign(params, this._appSecret);
		String paramSign = (String) params.get("sign");
		return sign.equals(paramSign);
	}
}
