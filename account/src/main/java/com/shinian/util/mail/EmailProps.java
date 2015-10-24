package com.shinian.util.mail;

import java.util.ArrayList;
import java.util.List;

public class EmailProps{
	private String emailHost = "";
	private boolean useDefaultSmtpPort = true;
	private int customPort = 25;
	private String username = "";
	private String password = "";
	private boolean TLS = false;
	private String fromAddress = "";
	private String fromDisplayName = "";
	
	private String subject = "";
	private String content = "";
	
	private List<String> recipients = new ArrayList<String>(); //接受人列表
	private List<String> carbonCopy = new ArrayList<String>(); //ccList	
	
	public String getEmailHost() {
		return emailHost;
	}
	
	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}
	
	public boolean isUseDefaultSmtpPort() {
		return useDefaultSmtpPort;
	}
	
	public void setUseDefaultSmtpPort(boolean useDefaultSmtpPort) {
		this.useDefaultSmtpPort = useDefaultSmtpPort;
	}
	
	public int getCustomPort() {
		return customPort;
	}
	
	public void setCustomPort(int customPort) {
		this.customPort = customPort;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isTLS() {
		return TLS;
	}
	
	public void setTLS(boolean TLS) {
		this.TLS = TLS;
	}
	
	public String getFromAddress() {
		return fromAddress;
	}
	
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	
	public String getFromDisplayName() {
		return fromDisplayName;
	}
	
	public void setFromDisplayName(String fromDisplayName) {
		this.fromDisplayName = fromDisplayName;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public List<String> getRecipients() {
		return recipients;
	}
	
	public void setRecipients(List<String> recipients) {
		this.recipients.addAll(recipients);
	}
	
	public List<String> getCarbonCopy() {
		return carbonCopy;
	}
	
	public void setCarbonCopy(List<String> carbonCopy) {
		this.carbonCopy.addAll(carbonCopy);
	}
}


