package com.gcexe.monitor.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcexe.monitor.persistence.dao.PicSearchMapper;
import com.gcexe.monitor.persistence.dao.SysPicMapper;

import com.gcexe.monitor.persistence.entity.SysPic;
import com.gcexe.monitor.utils.ITools;
import com.gcexe.monitor.utils.ResultCodeVo;

import net.sf.json.JSONObject;

@Service
public class PicServiceImpl extends BaseServiceCompnent implements PicService {

	@Autowired
	private SysPicMapper sysPicMapper;
	@Autowired
	private PicSearchMapper picSearchMapp;
	@Autowired
	private ITools itools;
	@Override
	public ResultCodeVo insert(String json) {
		// 封装json
		JSONObject JObj = JSONObject.fromObject(json);
		// 封装对象
		SysPic sysPic = new SysPic();
		sysPic.setPicName(JObj.getString("name"));
		sysPic.setPicUrl(JObj.getString("imgUrl"));
		sysPic.setMonitorRateDay(JObj.getInt("day_monitor"));
		sysPic.setMonitorRateMonth(JObj.getInt("week_monitor"));
		sysPic.setMonitorRateWeek(JObj.getInt("month_monitor"));
		sysPic.setMonitorRateYear(JObj.getInt("year_monitor"));
		sysPic.setOpenTask(JObj.getInt("plan_task"));
		sysPic.setCreator(JObj.getString("user"));
		sysPic.setUpdator(JObj.getString("user"));
		sysPic.setCreateTime(new Date());
		sysPic.setUpdateTime(new Date());
		// 执行添加
		int cnt = sysPicMapper.insertSelective(sysPic);
		if (cnt > 0) {
//			// 获取监控数据
//			PicSearch picSearch = this.getPicData();
//			// 持久化保存
//			int datacnt = picSearchMapp.insertSelective(picSearch);
//			if (datacnt > 0) {
				return new ResultCodeVo(true, 0, "success", null);
//			}
		}
		return new ResultCodeVo(false, -1, "failed", null);
	}

	public ResultCodeVo selectbyid(String json) {
		// 封装json 获取id
		JSONObject JObj = JSONObject.fromObject(json);
		Integer id = JObj.getInt("id");
		SysPic sysPic = sysPicMapper.selectByPrimaryKey(id);
		return new ResultCodeVo(true, 0, "success", sysPic);
	}

	public ResultCodeVo update(String json) {
		// 封装json
		JSONObject JObj = JSONObject.fromObject(json);
		// 封装对象
		SysPic sysPic = new SysPic();
		sysPic.setId(JObj.getInt("id"));
		sysPic.setPicName(JObj.getString("name"));
		sysPic.setPicUrl(JObj.getString("imgUrl"));
		sysPic.setMonitorRateDay(JObj.getInt("day_monitor"));
		sysPic.setMonitorRateMonth(JObj.getInt("week_monitor"));
		sysPic.setMonitorRateWeek(JObj.getInt("month_monitor"));
		sysPic.setMonitorRateYear(JObj.getInt("year_monitor"));
		sysPic.setOpenTask(JObj.getInt("plan_task"));
		sysPic.setCreator(JObj.getString("user"));
		sysPic.setUpdator(JObj.getString("user"));
		sysPic.setCreateTime(new Date());
		sysPic.setUpdateTime(new Date());
		int cnt = sysPicMapper.updateByPrimaryKeySelective(sysPic);
		if (cnt > 0) {
			return new ResultCodeVo(true, 0, "success", null);
		}
		return new ResultCodeVo(false, -1, "failed", null);
	}

	public ResultCodeVo delete(String json) {
		// 封装json 获取id
		JSONObject JObj = JSONObject.fromObject(json);
		Integer id = JObj.getInt("id");
		int cnt = sysPicMapper.deleteByPrimaryKey(id);
		if (cnt > 0) {
			return new ResultCodeVo(true, 0, "success", null);
		}
		return new ResultCodeVo(false, -1, "failed", null);
	}
	@Override
	public ResultCodeVo search(String json) {
		// 封装json
		JSONObject JObj = JSONObject.fromObject(json);
		int pagenum = JObj.getInt("pagenum");
		int limit = JObj.getInt("limit");
		int startnum = itools.getStartNum(pagenum, limit);
		// 设定参数主要是分页
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagenum", startnum);
		map.put("limit", limit);
		// 总条数
		int count = sysPicMapper.getRowCount();
		// 总页数
		int total = itools.getAllPage(count, limit);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("count", count);
		resultMap.put("total", total);
		resultMap.put("data", sysPicMapper.search(map));
		return new ResultCodeVo(true, 0, "success", resultMap);
	}

	@Override
	public ResultCodeVo searchdata(String json) {
		// 封装json
		JSONObject JObj = JSONObject.fromObject(json);
		int pagenum = JObj.getInt("pagenum");
		int limit = JObj.getInt("limit");
		int startnum = itools.getStartNum(pagenum, limit);
		// 设定参数主要是分页
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagenum", startnum);
		map.put("limit", limit);
		// 总条数
		int count = picSearchMapp.getRowCount();
		// 总页数
		int total = itools.getAllPage(count, limit);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("count", count);
		resultMap.put("total", total);
		resultMap.put("data", picSearchMapp.search(map));
		return new ResultCodeVo(true, 0, "success", resultMap);
	}

}
