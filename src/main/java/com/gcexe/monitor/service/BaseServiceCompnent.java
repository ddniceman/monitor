package com.gcexe.monitor.service;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import java.util.List;

import java.util.Properties;

import org.python.core.PyFunction;
import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import com.gcexe.monitor.persistence.dao.IPDataMapper;
import com.gcexe.monitor.persistence.entity.IPData;
import com.gcexe.monitor.persistence.entity.KeySearch;
import com.gcexe.monitor.persistence.entity.PicSearch;
import com.gcexe.monitor.persistence.entity.SysIP;

import net.sf.json.JSONArray;

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

	protected int insertIPData(String[] ips, SysIP sysIp, IPDataMapper ipDataMapper) {
		try {
			List<String> ipList = Arrays.asList(ips);

			// 使用python获取 指定ip列表的数据
			Properties props = new Properties();
			props.put("python.home", "path to the Lib folder");
			props.put("python.console.encoding", "UTF-8");
			props.put("python.security.respectJavaAccessibility", "false");
			props.put("python.import.site", "false");
			Properties preprops = System.getProperties();
			PythonInterpreter.initialize(preprops, props, new String[0]);
			PythonInterpreter interpreter = new PythonInterpreter();
			InputStream in = null;
			in = BaseServiceCompnent.class.getClassLoader().getResourceAsStream("ip_reverse_query2.py");
			interpreter.execfile(in);
			PyFunction func = (PyFunction) interpreter.get("ip_revers_query_list", PyFunction.class);
			PyObject pyobj = func.__call__(new PyList(ipList));
			interpreter.close();
			in.close();
			JSONArray jsonArray = JSONArray.fromObject(pyobj.toString());
			// 保存到数据库
			for (int i = 0; i < jsonArray.size(); i++) {
				String ip_ret = jsonArray.getJSONObject(i).getString("ip_ret");
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
						ipData.setVersion(1);// 必填
						// 执行添加
						ipDataMapper.insertSelective(ipData);
					} else {
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

							if (compnay.equals("未备案")) {
								ipData.setRecordCom(null);
							} else {
								ipData.setRecordCom(compnay);
							}
							if (id.equals("未备案")) {
								ipData.setRecordNo(null);
							} else {
								ipData.setRecordNo(id);
							}
							java.text.SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							Date date1 = null;
							try {
								date1 = formatter.parse(date);
							} catch (ParseException e) {
								date1 = formatter.parse("00-00-00");
								e.printStackTrace();
							}
							ipData.setRecordTime(date1);

							ipData.setCreator(sysIp.getCreator());
							ipData.setUpdator(sysIp.getUpdator());
							ipData.setCreateTime(sysIp.getCreateTime());
							ipData.setUpdateTime(sysIp.getUpdateTime());
							ipData.setIsRecord(sysIp.getGnRecord());

							ipData.setVersion(1);// 必填
							// 执行添加
							ipDataMapper.insertSelective(ipData);

						}
					}
				}
			}
		} catch (

		Exception e) {
			return 0;
		}
		return 1;
	}
}
