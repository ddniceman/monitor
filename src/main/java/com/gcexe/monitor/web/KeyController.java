package com.gcexe.monitor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcexe.monitor.service.KeyService;
import com.gcexe.monitor.utils.ResultCodeVo;
@Controller
@RequestMapping("/key")
public class KeyController {
	
	@Autowired
	private KeyService keyService;
	/**
	 * 添加
	 * @param json
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public ResultCodeVo  insert(@RequestBody String json)
	{
		return keyService.insert(json);
	}
	/**
	 * 主键查询
	 * @param json
	 * @return
	 */
	@RequestMapping("/selectbyid")
	@ResponseBody
	public ResultCodeVo  selectbyid(@RequestBody String json)
	{
		return keyService.selectbyid(json);
	}
	/**
	 * 修改
	 * @param json
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ResultCodeVo  update(@RequestBody String json)
	{
		return keyService.update(json);
	}
	/**
	 * 删除
	 * @param json
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ResultCodeVo  delete(@RequestBody String json)
	{
		return keyService.delete(json);
	}
	/**
	 * 查询
	 * @param json
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public ResultCodeVo  search(@RequestBody String json)
	{
		return keyService.search(json);
	}
	/**
	 * 查询data
	 * @param json
	 * @return
	 */
	@RequestMapping("/searchdata")
	@ResponseBody
	public ResultCodeVo  searchdata(@RequestBody String json)
	{
		return keyService.searchdata(json);
	}
	//===============================================================
	/**
	 * 关键字排名TOP10
	 * @param json
	 * @return
	 */
	@RequestMapping("/keyorder")
	@ResponseBody
	public ResultCodeVo  keyorder(@RequestBody String json)
	{
		return keyService.keyorder(json);
	}
	/**
	 * 传播站点排名TOP10
	 * @param json
	 * @return
	 */
	@RequestMapping("/siteorder")
	@ResponseBody
	public ResultCodeVo  siteorder(@RequestBody String json)
	{
		return keyService.siteorder(json);
	}
	/**
	 * 全球地图趋势分布
	 * @param json
	 * @return
	 */
	@RequestMapping("/map")
	@ResponseBody
	public ResultCodeVo  map(@RequestBody String json)
	{
		return keyService.map(json);
	}
	/**
	 * 根据关键字获取URL
	 * @param json
	 * @return
	 */
	@RequestMapping("/urllist")
	@ResponseBody
	public ResultCodeVo  urllist(@RequestBody String json)
	{
		return keyService.selectByKey(json);
	}
	/**
	 * 根据URL获取关键字
	 * @param json
	 * @return
	 */
	@RequestMapping("/keylist")
	@ResponseBody
	public ResultCodeVo  keylist(@RequestBody String json)
	{
		return keyService.selectByUrl(json);
	}
	
}
