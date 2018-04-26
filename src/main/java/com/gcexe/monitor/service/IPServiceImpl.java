package com.gcexe.monitor.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcexe.monitor.persistence.dao.IPDataMapper;
import com.gcexe.monitor.persistence.dao.SysIPMapper;
import com.gcexe.monitor.persistence.entity.SysIP;
import com.gcexe.monitor.persistence.statistics.entity.IPCntStatis;
import com.gcexe.monitor.utils.ITools;
import com.gcexe.monitor.utils.ResultCodeVo;

import net.sf.json.JSONObject;

@Service("ipServiceImpl")
public class IPServiceImpl extends BaseServiceCompnent implements IPService {

	@Autowired
	private SysIPMapper sysIPMapper;
	@Autowired
	private ITools itools;
	@Autowired
	private IPDataMapper ipDataMapper;

	public ResultCodeVo insert(String json) {
		// 封装json
		JSONObject JObj = JSONObject.fromObject(json);
		// 封装对象
		SysIP sysIP = new SysIP();
		sysIP.setIpName(JObj.getString("name"));
		sysIP.setIpCom(JObj.getString("company"));
		sysIP.setIpList(JObj.getString("ip"));
		sysIP.setIcpRecord(JObj.getInt("icp_record"));
		sysIP.setGnRecord(JObj.getInt("gn_record"));
		sysIP.setMonitorRateDay(JObj.getInt("day_monitor"));
		sysIP.setMonitorRateMonth(JObj.getInt("week_monitor"));
		sysIP.setMonitorRateWeek(JObj.getInt("month_monitor"));
		sysIP.setMonitorRateYear(JObj.getInt("year_monitor"));
		sysIP.setOpenTask(JObj.getInt("plan_task"));
		sysIP.setCreator(JObj.getString("user"));
		sysIP.setUpdator(JObj.getString("user"));
		sysIP.setCreateTime(new Date());
		sysIP.setUpdateTime(new Date());
		// 执行添加
		int cnt = sysIPMapper.insertSelective(sysIP);

		if (cnt > 0) {
			String[] iplist = itools.formatStringSplit(sysIP.getIpList(), ",");
			// 获取监控数据

			this.insertIPData(iplist, sysIP, ipDataMapper);

			return new ResultCodeVo(true, 0, "success", null);
		} else {
			return new ResultCodeVo(false, -1, "failed", null);
		}

	}

	public ResultCodeVo selectbyid(String json) {
		// 封装json 获取id
		JSONObject JObj = JSONObject.fromObject(json);
		Integer id = JObj.getInt("id");
		SysIP sysIp = sysIPMapper.selectByPrimaryKey(id);
		return new ResultCodeVo(true, 0, "success", sysIp);
	}

	public ResultCodeVo update(String json) {
		// 封装json
		JSONObject JObj = JSONObject.fromObject(json);
		// 封装对象
		SysIP sysIP = new SysIP();
		sysIP.setId(JObj.getInt("id"));
		sysIP.setIpName(JObj.getString("name"));
		sysIP.setIpCom(JObj.getString("company"));
		sysIP.setIpList(JObj.getString("ip"));
		sysIP.setIcpRecord(JObj.getInt("icp_record"));
		sysIP.setGnRecord(JObj.getInt("gn_record"));
		sysIP.setMonitorRateDay(JObj.getInt("day_monitor"));
		sysIP.setMonitorRateMonth(JObj.getInt("week_monitor"));
		sysIP.setMonitorRateWeek(JObj.getInt("month_monitor"));
		sysIP.setMonitorRateYear(JObj.getInt("year_monitor"));
		sysIP.setOpenTask(JObj.getInt("plan_task"));
		sysIP.setCreator(JObj.getString("user"));
		sysIP.setUpdator(JObj.getString("user"));
		sysIP.setCreateTime(new Date());
		sysIP.setUpdateTime(new Date());
		int cnt = sysIPMapper.updateByPrimaryKeySelective(sysIP);
		if (cnt > 0) {
			return new ResultCodeVo(true, 0, "success", null);
		} else {
			return new ResultCodeVo(false, -1, "failed", null);
		}
	}

	public ResultCodeVo delete(String json) {
		// 封装json
		JSONObject JObj = JSONObject.fromObject(json);
		// 封装json 获取id
		Integer id = JObj.getInt("id");
		int cnt = sysIPMapper.deleteByPrimaryKey(id);
		if (cnt > 0) {
			return new ResultCodeVo(true, 0, "success", null);
		}
		return new ResultCodeVo(false, -1, "failed", null);
	}

	// ==============================================
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
		int count = sysIPMapper.getRowCount();
		// 总页数
		int total = itools.getAllPage(count, limit);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("count", count);
		resultMap.put("total", total);
		resultMap.put("data", sysIPMapper.search(map));
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
		int count = ipDataMapper.getRowCount();
		// 总页数
		int total = itools.getAllPage(count, limit);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("count", count);
		resultMap.put("total", total);
		resultMap.put("data", ipDataMapper.search(map));
		return new ResultCodeVo(true, 0, "success", resultMap);
	}

	public ResultCodeVo domainCnt(String json) {
		return new ResultCodeVo(true, 0, "success", this.sysIPMapper.domainCnt());
	}

	public ResultCodeVo illegalcnt(String json) {
		return new ResultCodeVo(true, 0, "success", sysIPMapper.illegalcnt());
	}

	public ResultCodeVo situation(String json) {
		// 封装json
		JSONObject JObj = JSONObject.fromObject(json);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("param", JObj.getInt("param"));

		List<IPCntStatis> situationList = sysIPMapper.situation(map);
		List<IPCntStatis> unsituationList = sysIPMapper.unsituation(map);
		int total = sysIPMapper.situationTotal();
		int untotal = sysIPMapper.unsituationTotal();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("untotal", untotal);
		result.put("situationList", situationList);
		result.put("unsituationList", unsituationList);
		return new ResultCodeVo(true, 0, "success", result);
	}

	public ResultCodeVo avgsituation(String json) {
		// 封装json
		JSONObject JObj = JSONObject.fromObject(json);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("param", JObj.getInt("param"));
		return new ResultCodeVo(true, 0, "success", sysIPMapper.avgsituation(map));
	}

}
