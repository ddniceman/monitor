package com.gcexe.monitor.service;

import com.gcexe.monitor.utils.ResultCodeVo;

public interface BaseService {
	/**
	 * 添加
	 * 
	 * @param json
	 * @return
	 */
	public ResultCodeVo insert(String json);

	/**
	 * 主键查询
	 * 
	 * @param json
	 * @return
	 */
	public ResultCodeVo selectbyid(String json);

	/**
	 * 修改
	 * 
	 * @param json
	 * @return
	 */
	public ResultCodeVo update(String json);

	/**
	 * 删除
	 * 
	 * @param json
	 * @return
	 */
	public ResultCodeVo delete(String json);

	/**
	 * 查询
	 * 
	 * @param json
	 * @return
	 */
	public ResultCodeVo search(String json);

	/**
	 * 查询data
	 * 
	 * @param json
	 * @return
	 */
	public ResultCodeVo searchdata(String json);

}
