package com.gcexe.monitor.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcexe.monitor.persistence.dao.KeySearchMapper;
import com.gcexe.monitor.persistence.dao.SysKeyMapper;

import com.gcexe.monitor.persistence.entity.SysKey;
import com.gcexe.monitor.utils.ITools;
import com.gcexe.monitor.utils.ResultCodeVo;

@Service
public class KeyServiceImpl extends BaseServiceCompnent implements KeyService {

	@Autowired
	private SysKeyMapper sysKeyMapper;
	@Autowired
	private KeySearchMapper keySearchMapper;
	@Autowired
	private ITools itools;

	public ResultCodeVo insert(String json) {
		// 封装json
		JSONObject JObj = JSONObject.fromObject(json);
		// 封装对象
		SysKey sysKey = new SysKey();
		sysKey.setKeyName(JObj.getString("name"));
		sysKey.setKeyList(JObj.getString("keywords"));
		sysKey.setCreateTime(new Date());
		sysKey.setUpdateTime(new Date());
		sysKey.setCreator(JObj.getString("user"));
		sysKey.setUpdator(JObj.getString("user"));
		sysKey.setMonitorRateDay(JObj.getInt("day_monitor"));
		sysKey.setMonitorRateWeek(JObj.getInt("month_monitor"));
		sysKey.setMonitorRateMonth(JObj.getInt("week_monitor"));
		sysKey.setMonitorRateYear(JObj.getInt("year_monitor"));
		sysKey.setOpenTask(JObj.getInt("plan_task"));
		// 执行添加
		int cnt = sysKeyMapper.insertSelective(sysKey);
		if (cnt > 0) {
//			// 获取监控数据
//			KeySearch keySearch = this.getKeyData();
//			// 持久化保存
//			int datacnt = keySearchMapper.insertSelective(keySearch);
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
		SysKey sysKey = sysKeyMapper.selectByPrimaryKey(id);
		return new ResultCodeVo(true, 0, "success", sysKey);
	}

	public ResultCodeVo update(String json) {
		// 封装json
		JSONObject JObj = JSONObject.fromObject(json);
		// 封装对象
		SysKey sysKey = new SysKey();
		sysKey.setId(JObj.getInt("id"));
		sysKey.setKeyName(JObj.getString("name"));
		sysKey.setKeyList(JObj.getString("keywords"));
		sysKey.setMonitorRateDay(JObj.getInt("day_monitor"));
		sysKey.setMonitorRateMonth(JObj.getInt("week_monitor"));
		sysKey.setMonitorRateWeek(JObj.getInt("month_monitor"));
		sysKey.setMonitorRateYear(JObj.getInt("year_monitor"));
		sysKey.setOpenTask(JObj.getInt("plan_task"));
		sysKey.setCreator(JObj.getString("user"));
		sysKey.setUpdator(JObj.getString("user"));
		sysKey.setCreateTime(new Date());
		sysKey.setUpdateTime(new Date());
		int cnt = sysKeyMapper.updateByPrimaryKeySelective(sysKey);
		if (cnt > 0) {
			return new ResultCodeVo(true, 0, "success", null);
		}
		return new ResultCodeVo(false, -1, "failed", null);
	}

	public ResultCodeVo delete(String json) {
		// 封装json 获取id
		JSONObject JObj = JSONObject.fromObject(json);
		// 封装json 获取id
		Integer id = JObj.getInt("id");
		int cnt = sysKeyMapper.deleteByPrimaryKey(id);
		if (cnt > 0) {
			return new ResultCodeVo(true, 0, "success", null);
		}
		return new ResultCodeVo(false, -1, "failed", null);
	}

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
		int count = sysKeyMapper.getRowCount();
		// 总页数
		int total = itools.getAllPage(count, limit);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("count", count);
		resultMap.put("total", total);
		resultMap.put("data", sysKeyMapper.search(map));
		return new ResultCodeVo(true, 0, "success", resultMap);
	}

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
		int count = keySearchMapper.getRowCount();
		// 总页数
		int total = itools.getAllPage(count, limit);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("count", count);
		resultMap.put("total", total);
		resultMap.put("data", keySearchMapper.search(map));
		return new ResultCodeVo(true, 0, "success", resultMap);
	}

	public ResultCodeVo keyorder(String json) {
		return new ResultCodeVo(true, 0, "success", this.sysKeyMapper.keyorder());
	}

	public ResultCodeVo siteorder(String json) {
		return new ResultCodeVo(true, 0, "success", this.sysKeyMapper.siteorder());
	}

	public ResultCodeVo map(String json) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyCount", sysKeyMapper.keyCount());
		map.put("urlCount", sysKeyMapper.urlCount());

		return new ResultCodeVo(true, 0, "success", map);
	}

	public ResultCodeVo selectByKey(String json) {
		// 封装json
		JSONObject JObj = JSONObject.fromObject(json);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyCon", JObj.getString("keyCon"));
		return new ResultCodeVo(true, 0, "success", sysKeyMapper.selectByKey(map));
	}

	public ResultCodeVo selectByUrl(String json) {
		// 封装json
		JSONObject JObj = JSONObject.fromObject(json);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", JObj.getString("url"));
		return new ResultCodeVo(true, 0, "success", sysKeyMapper.selectByUrl(map));
	}

}
