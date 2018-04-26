package com.gcexe.monitor.persistence.statistics.entity;

import java.util.Date;

public class IPSearchStatis {

	private int id;
	private String ipName;
	private int ipCnt;
	private int monitorRate;
	private int openTask;
	private Date uTime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getuTime() {
		return uTime;
	}
	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}
	public String getIpName() {
		return ipName;
	}
	public void setIpName(String ipName) {
		this.ipName = ipName;
	}
	public int getIpCnt() {
		return ipCnt;
	}
	public void setIpCnt(int ipCnt) {
		this.ipCnt = ipCnt;
	}
	public int getMonitorRate() {
		return monitorRate;
	}
	public void setMonitorRate(int monitorRate) {
		this.monitorRate = monitorRate;
	}
	public int getOpenTask() {
		return openTask;
	}
	public void setOpenTask(int openTask) {
		this.openTask = openTask;
	}
	
}
