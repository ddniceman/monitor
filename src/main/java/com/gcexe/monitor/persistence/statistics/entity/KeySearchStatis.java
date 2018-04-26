package com.gcexe.monitor.persistence.statistics.entity;

import java.util.Date;

public class KeySearchStatis {
	private int id;
	private String keyName;
	private int keyCnt;
	private int monitorRate;
	private int openTask;
	private Date uTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public int getKeyCnt() {
		return keyCnt;
	}
	public void setKeyCnt(int keyCnt) {
		this.keyCnt = keyCnt;
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
	public Date getuTime() {
		return uTime;
	}
	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}
	
}
