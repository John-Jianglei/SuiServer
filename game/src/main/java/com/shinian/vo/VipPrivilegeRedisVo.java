package com.shinian.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VipPrivilegeRedisVo extends BaseObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int vipLevel;
	private String buyStrengthDesc;		
	private int buyStrength;			//购买体力次数
	private String maxStrengthDesc;
	private int maxStrength;			//体力上限
	private String resetPassDesc;		
	private int resetPassNum;			//充值关卡次数
	private String zhaoCaiDesc;
	private int zhaoCaiNum;				//招财次数 
	private String libaoDesc;
	private int libao;					//应该是礼包id，目前还没想好
	private String saoDangDesc;
	private int saoDang;				//是否具有扫荡功能；1有，0没有
	private String chongZhiDesc;
	private int chongZhi;				//累积充值，也可以写成累计充值
	private String shenJiangLuDesc;
	private int shenJiangLu;			//神将录次数	
	
	public int getVip() {
		return vipLevel;
	}

	public void setVip(int vipLevel) {
		this.vipLevel = vipLevel;
	}

	public String getBuyStrengthDesc() {
		return buyStrengthDesc;
	}

	public void setBuyStrengthDesc(String buyStrengthDesc) {
		this.buyStrengthDesc = buyStrengthDesc;
	}
	
	public int getBuyStrength() {
		return buyStrength;
	}

	public void setBuyStrength(int buyStrength) {
		this.buyStrength = buyStrength;
	}

	public String getMaxStrengthDesc() {
		return maxStrengthDesc;
	}

	public void setMaxStrengthDesc(String maxStrengthDesc) {
		this.maxStrengthDesc = maxStrengthDesc;
	}

	public int getMaxStrength() {
		return maxStrength;
	}

	public void setMaxStrength(int maxStrength) {
		this.maxStrength = maxStrength;
	}

	public String getResetPassDesc() {
		return resetPassDesc;
	}

	public void setResetPassDesc(String resetPassDesc) {
		this.resetPassDesc = resetPassDesc;
	}

	public int getResetPassNum() {
		return resetPassNum;
	}

	public void setResetPassNum(int resetPassNum) {
		this.resetPassNum = resetPassNum;
	}

	public String getZhaoCaiDesc() {
		return zhaoCaiDesc;
	}


	public void setZhaoCaiDesc(String zhaoCaiDesc) {
		this.zhaoCaiDesc = zhaoCaiDesc;
	}

	public int getZhaoCaiNum() {
		return zhaoCaiNum;
	}

	public void setZhaoCaiNum(int zhaoCaiNum) {
		this.zhaoCaiNum = zhaoCaiNum;
	}

	public String getLibaoDesc() {
		return libaoDesc;
	}

	public void setLibaoDesc(String libaoDesc) {
		this.libaoDesc = libaoDesc;
	}

	public int getLibao() {
		return libao;
	}

	public void setLibao(int libao) {
		this.libao = libao;
	}


	public String getSaoDangDesc() {
		return saoDangDesc;
	}

	public void setSaoDangDesc(String saoDangDesc) {
		this.saoDangDesc = saoDangDesc;
	}

	public int getSaoDang() {
		return saoDang;
	}


	public void setSaoDang(int saoDang) {
		this.saoDang = saoDang;
	}

	public String getChongZhiDesc() {
		return chongZhiDesc;
	}

	public void setChongZhiDesc(String chongZhiDesc) {
		this.chongZhiDesc = chongZhiDesc;
	}

	public int getChongZhi() {
		return chongZhi;
	}

	public void setChongZhi(int chongZhi) {
		this.chongZhi = chongZhi;
	}

	public String getShenJiangLuDesc() {
		return shenJiangLuDesc;
	}

	public void setShenJiangLuDesc(String shenJiangLuDesc) {
		this.shenJiangLuDesc = shenJiangLuDesc;
	}

	public int getShenJiangLu() {
		return shenJiangLu;
	}

	public void setShenJiangLu(int shenJiangLu) {
		this.shenJiangLu = shenJiangLu;
	}
	
	public void fromList(List<String> list) {
		int idx = 0;
		
		this.vipLevel = Integer.parseInt(list.get(idx++));  
		this.buyStrengthDesc = list.get(idx++); 
		this.buyStrength = Integer.parseInt(list.get(idx++));
		this.maxStrengthDesc = list.get(idx++);
		this.maxStrength = Integer.parseInt(list.get(idx++));
		this.resetPassDesc = list.get(idx++);
		this.resetPassNum = Integer.parseInt(list.get(idx++));
		this.zhaoCaiDesc = list.get(idx++);
		this.zhaoCaiNum = Integer.parseInt(list.get(idx++));
		this.libaoDesc = list.get(idx++);
		this.libao = Integer.parseInt(list.get(idx++));
		this.saoDangDesc = list.get(idx++);
		this.saoDang = Integer.parseInt(list.get(idx++));
		this.chongZhiDesc = list.get(idx++);
		this.chongZhi = Integer.parseInt(list.get(idx++));
		this.shenJiangLuDesc = list.get(idx++);
		this.shenJiangLu = Integer.parseInt(list.get(idx++));		

	}
	
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
				
		map.put("vipLevel", 		        this.vipLevel + "");
		map.put("buyStrengthDesc",          this.buyStrengthDesc + "");
		map.put("buyStrength", 		        this.buyStrength + "");
		map.put("maxStrengthDesc", 		    this.maxStrengthDesc + "");
		map.put("maxStrength", 		        this.maxStrength + "");
		map.put("resetPassDesc", 		    this.resetPassDesc + "");
		map.put("resetPassNum", 		    this.resetPassNum + "");
		map.put("zhaoCaiDesc", 		        this.zhaoCaiDesc + "");
		map.put("zhaoCaiNum", 		        this.zhaoCaiNum + "");
		map.put("libaoDesc", 		        this.libaoDesc + "");
		map.put("libao", 		            this.libao + "");
		map.put("saoDangDesc", 		        this.saoDangDesc + "");
		map.put("saoDang", 		            this.saoDang + "");
		map.put("chongZhiDesc", 		    this.chongZhiDesc + "");
		map.put("chongZhi", 		        this.chongZhi + "");
		map.put("shenJiangLuDesc", 		    this.shenJiangLuDesc + "");
		map.put("shenJiangLu", 		        this.shenJiangLu + "");		

		return map;
	}
	
	public String[] getFieldNames() {
		return new String[] {"vipLevel", "buyStrengthDesc", "buyStrength", "maxStrengthDesc", "maxStrength", "resetPassDesc", 
				"resetPassNum", "zhaoCaiDesc", "zhaoCaiNum", "libaoDesc", "libao", "saoDangDesc", "saoDang", 
				"chongZhiDesc", "chongZhi", "shenJiangLuDesc", "shenJiangLu"};
	}
	
}
