package com.gcexe.monitor.persistence.entity;

import java.util.Date;

public class SysPic {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_pic.id
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_pic.pic_name
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	private String picName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_pic.create_time
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_pic.update_time
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	private Date updateTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_pic.creator
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	private String creator;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_pic.updator
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	private String updator;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_pic.monitor_rate_day
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	private Integer monitorRateDay;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_pic.monitor_rate_week
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	private Integer monitorRateWeek;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_pic.monitor_rate_month
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	private Integer monitorRateMonth;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_pic.monitor_rate_year
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	private Integer monitorRateYear;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_pic.open_task
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	private Integer openTask;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_pic.pic_url
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	private String picUrl;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_pic.id
	 * @return  the value of sys_pic.id
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_pic.id
	 * @param id  the value for sys_pic.id
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_pic.pic_name
	 * @return  the value of sys_pic.pic_name
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public String getPicName() {
		return picName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_pic.pic_name
	 * @param picName  the value for sys_pic.pic_name
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public void setPicName(String picName) {
		this.picName = picName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_pic.create_time
	 * @return  the value of sys_pic.create_time
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_pic.create_time
	 * @param createTime  the value for sys_pic.create_time
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_pic.update_time
	 * @return  the value of sys_pic.update_time
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_pic.update_time
	 * @param updateTime  the value for sys_pic.update_time
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_pic.creator
	 * @return  the value of sys_pic.creator
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_pic.creator
	 * @param creator  the value for sys_pic.creator
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_pic.updator
	 * @return  the value of sys_pic.updator
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public String getUpdator() {
		return updator;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_pic.updator
	 * @param updator  the value for sys_pic.updator
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public void setUpdator(String updator) {
		this.updator = updator;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_pic.monitor_rate_day
	 * @return  the value of sys_pic.monitor_rate_day
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public Integer getMonitorRateDay() {
		return monitorRateDay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_pic.monitor_rate_day
	 * @param monitorRateDay  the value for sys_pic.monitor_rate_day
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public void setMonitorRateDay(Integer monitorRateDay) {
		this.monitorRateDay = monitorRateDay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_pic.monitor_rate_week
	 * @return  the value of sys_pic.monitor_rate_week
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public Integer getMonitorRateWeek() {
		return monitorRateWeek;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_pic.monitor_rate_week
	 * @param monitorRateWeek  the value for sys_pic.monitor_rate_week
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public void setMonitorRateWeek(Integer monitorRateWeek) {
		this.monitorRateWeek = monitorRateWeek;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_pic.monitor_rate_month
	 * @return  the value of sys_pic.monitor_rate_month
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public Integer getMonitorRateMonth() {
		return monitorRateMonth;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_pic.monitor_rate_month
	 * @param monitorRateMonth  the value for sys_pic.monitor_rate_month
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public void setMonitorRateMonth(Integer monitorRateMonth) {
		this.monitorRateMonth = monitorRateMonth;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_pic.monitor_rate_year
	 * @return  the value of sys_pic.monitor_rate_year
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public Integer getMonitorRateYear() {
		return monitorRateYear;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_pic.monitor_rate_year
	 * @param monitorRateYear  the value for sys_pic.monitor_rate_year
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public void setMonitorRateYear(Integer monitorRateYear) {
		this.monitorRateYear = monitorRateYear;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_pic.open_task
	 * @return  the value of sys_pic.open_task
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public Integer getOpenTask() {
		return openTask;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_pic.open_task
	 * @param openTask  the value for sys_pic.open_task
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public void setOpenTask(Integer openTask) {
		this.openTask = openTask;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_pic.pic_url
	 * @return  the value of sys_pic.pic_url
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_pic.pic_url
	 * @param picUrl  the value for sys_pic.pic_url
	 * @mbggenerated  Wed Apr 25 19:24:06 CST 2018
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}