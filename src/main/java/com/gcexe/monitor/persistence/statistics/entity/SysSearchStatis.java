package com.gcexe.monitor.persistence.statistics.entity;

import java.util.Date;

public class SysSearchStatis {
	private int id;
	private String picName;
	private int picCnt;
	private int monitorRate;
	private int openTask;
	private Date uTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public int getPicCnt() {
		return picCnt;
	}
	public void setPicCnt(int picCnt) {
		this.picCnt = picCnt;
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
