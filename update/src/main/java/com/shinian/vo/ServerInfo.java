package com.shinian.vo;

import java.io.Serializable;
import java.util.List;

public class ServerInfo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;

	private String accountUrl;//account server url prefix
	private String staticUrl; //static nginx server url prefix
	
	private List<ServerListVo> serverList;
	
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
