package com.gcexe.monitor.web;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcexe.monitor.service.IPService;
import com.gcexe.monitor.utils.ResultCodeVo;

@Controller
@RequestMapping("ip")
public class IPController {

	@Resource( name="ipServiceImpl")
	private IPService ipService;
	
	/**
	 * 添加
	 * @param json
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public ResultCodeVo  insert(@RequestBody String json)
	{
		return ipService.insert(json);
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
		return ipService.selectbyid(json);
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
		return ipService.update(json);
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
		return ipService.delete(json);
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
		return ipService.search(json);
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
		return ipService.searchdata(json);
	}
	//===============================================================
	/**
	 * IP(域名数)TOP10
	 * @param json
	 * @return
	 */
	@RequestMapping("/domaincnt")
	@ResponseBody
	public ResultCodeVo  domainCnt(@RequestBody String json)
	{
		return ipService.domainCnt(json);
	}
	/**
	 * 非法域名IPTOP10
	 * @param json
	 * @return
	 */
	@RequestMapping("/illegalcnt")
	@ResponseBody
	public ResultCodeVo  illegalcnt(@RequestBody String json)
	{
		return ipService.illegalcnt(json);
	}
	/**
	 * IP域名总数/非法域名总数态势
	 * @param json
	 * @return
	 */
	@RequestMapping("/situation")
	@ResponseBody
	public ResultCodeVo  situation(@RequestBody String json)
	{
		return ipService.situation(json);
	}
	/**
	 * IP平均挂域名态势
	 * @param json
	 * @return
	 */
	@RequestMapping("/avgsituation")
	@ResponseBody
	public ResultCodeVo  avgsituation(@RequestBody String json)
	{
		return ipService.avgsituation(json);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
