package com.gcexe.monitor.service;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import net.sf.json.JSONArray;

import org.python.core.PyFunction;
import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import com.gcexe.monitor.persistence.dao.IPDataMapper;
import com.gcexe.monitor.persistence.entity.IPData;
import com.gcexe.monitor.persistence.entity.KeySearch;
import com.gcexe.monitor.persistence.entity.PicSearch;
import com.gcexe.monitor.persistence.entity.SysIP;

public class BaseServiceCompnent {

	// 获取IPData数据
	protected IPData getIPData() {
		return null;
	}

	// 获取图片Data数据
	protected PicSearch getPicData() {
		return null;
	}

	// 获取关键字Data数据
	protected KeySearch getKeyData() {
		return null;
	}
	protected int insertIPData(String[] ips, SysIP sysIp,
			IPDataMapper ipDataMapper) {
		List<String> ipList = Arrays.asList(ips);
		int ipId = 0;
		// 使用python获取 指定ip列表的数据
		Properties props = new Properties();
		props.put("python.home", "path to the Lib folder");
		props.put("python.console.encoding", "UTF-8");
		props.put("python.security.respectJavaAccessibility", "false");
		props.put("python.import.site", "false");
		Properties preprops = System.getProperties();
		PythonInterpreter.initialize(preprops, props, new String[0]);
		PythonInterpreter interpreter = new PythonInterpreter();
//		String in = this.getClass().getResource("ip_reverse_query2.py").getPath();
		InputStream in = null;
        in = BaseServiceCompnent.class.getClassLoader().getResourceAsStream("ip_reverse_query2.py");
		System.out.println(in);
		interpreter.execfile(in);
		PyFunction func = (PyFunction) interpreter.get("ip_revers_query_list",
				PyFunction.class);
		// list.add("121.43.160.204/32");
		// list.add("121.41.15.16/28");
		// list.add("121.41.15.112/28");
		// list.add("121.43.160.198");
		PyObject pyobj = func.__call__(new PyList(ipList));
		System.out.println("anwser = " + pyobj.toString());
		JSONArray jsonArray = JSONArray.fromObject(pyobj.toString());
		for (int i = 0; i < jsonArray.size(); i++) {
			String ip_ret = jsonArray.getJSONObject(i).getString("ip_ret");
			// System.out.println(ip_ret);
			JSONArray json = JSONArray.fromObject(ip_ret.toString());
			for (int j = 0; j < json.size(); j++) {
				String ip = json.getJSONObject(j).getString("ip");
				String record = json.getJSONObject(j).getString("record");
				JSONArray json2 = JSONArray.fromObject(record).getJSONArray(0);
				if (json2.size() <= 0) {
					IPData ipData = new IPData();
					ipData.setIpId(sysIp.getId());// 必填
					ipData.setIpCom(sysIp.getIpCom());
					ipData.setIp(ip);
					List<IPData> selectByipId = ipDataMapper.selectByipId(sysIp.getId());
					if (selectByipId != null) {
						// 判断ipData表是否已经有 ipId 存在的数据
						// 如果存在 version +1
						ipId += 1;
						ipData.setVersion(ipId);// 必填
						ipDataMapper.insertSelective(ipData);
					}else {
						ipData.setVersion(ipId);// 必填
						// 执行添加
						ipDataMapper.insertSelective(ipData);
					}
				}else{
				for (int k = 0; k < json2.size(); k++) {
					String date = json2.getJSONObject(k).getString("date");
					String compnay = json2.getJSONObject(k).getString("compnay");
					String name = json2.getJSONObject(k).getString("name");
					String id = json2.getJSONObject(k).getString("id");
					IPData ipData = new IPData();
					ipData.setIpId(sysIp.getId());// 必填
					ipData.setIpCom(sysIp.getIpCom());
					ipData.setIp(ip);
					ipData.setIpDomain(name);
					ipData.setRecordNo(id);
					ipData.setRecordCom(compnay);
					java.text.SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date date1 = null;
					try {
						date1 = formatter.parse(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						date1 = new Date(0, 0, 0, 0, 0, 0);
						e.printStackTrace();
					}
					ipData.setRecordTime(date1);
					
					ipData.setCreator(sysIp.getCreator());
					ipData.setUpdator(sysIp.getUpdator());
					ipData.setCreateTime(sysIp.getCreateTime());
					ipData.setUpdateTime(sysIp.getUpdateTime());
					ipData.setIsRecord(sysIp.getGnRecord());
//					Map<String, Object> map = new HashMap<>();
//					map.put("ipId", sysIp.getId());
					List<IPData> selectByipId = ipDataMapper.selectByipId(sysIp.getId());
					
					if (selectByipId != null) {
						// 判断ipData表是否已经有 ipId 存在的数据
						// 如果存在 version +1
						
						ipId += 1;
						ipData.setVersion(ipId);// 必填
						ipDataMapper.insertSelective(ipData);
					}else {
						ipData.setVersion(ipId);// 必填
						// 执行添加
						ipDataMapper.insertSelective(ipData);
					}
				}
			}
//				ipDataMapper.insertSelective(ipData);
			}
		}
		return 1;
	}
}
