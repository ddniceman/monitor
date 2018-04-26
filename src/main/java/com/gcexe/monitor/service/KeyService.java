package com.gcexe.monitor.service;

import com.gcexe.monitor.utils.ResultCodeVo;

public interface KeyService extends BaseService {
	/**
	 * 关键字排名TOP10
	 * 
	 * @param json
	 * @return
	 */
	public ResultCodeVo keyorder(String json);

	/**
	 * 传播站点排名TOP10
	 * 
	 * @param json
	 * @return
	 */
	public ResultCodeVo siteorder(String json);

	/**
	 * 全球地图趋势分布
	 * 
	 * @param json
	 * @return
	 */
	public ResultCodeVo map(String json);
	/**
	 * 根据关键获取url
	 * @return
	 */
	public ResultCodeVo selectByKey(String json);
	
	/**
	 * 根据url获取关键字
	 * @return
	 */
	public ResultCodeVo selectByUrl(String json);
	
	
	
	
	
	
	
	
	
	
	
	
	

}
