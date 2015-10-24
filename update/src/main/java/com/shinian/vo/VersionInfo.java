package com.shinian.vo;

import java.io.Serializable;
import java.util.List;

public class VersionInfo extends BaseObject implements Serializable{
private static final long serialVersionUID = 1L;
	
	private String url;
	private int has;
	
	private String accountUrl;//account server url prefix
	private String staticUrl; //static nginx server url prefix
	
	private List<ServerListVo> serverList;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getHas() {
		return has;
	}
	public void setHas(int has) {
		this.has = has;
	}
	public String getAccountUrl() {
		return accountUrl;
	}
	public void setAccountUrl(String accountUrl) {
		this.accountUrl = accountUrl;
	}	
	public String getStaticUrl() {
		return staticUrl;
	}
	public void setStaticUrl(String staticUrl) {
		this.staticUrl = staticUrl;
	}
	public List<ServerListVo> getServerList() {
		return serverList;
	}
	public void setServerList(List<ServerListVo> serverList) {
		this.serverList = serverList;
	}
}
