package com.gcexe.monitor.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gcexe.monitor.service.PicService;
import com.gcexe.monitor.utils.ResultCodeVo;
@Controller
@RequestMapping("/pic")
public class PicController {
	
	@Autowired
	private PicService picService;
	/**
	 * 添加
	 * @param json
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public ResultCodeVo  insert(@RequestBody String json)
	{
		return picService.insert(json);
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
		return picService.selectbyid(json);
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
		return picService.update(json);
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
		return picService.delete(json);
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
		return picService.search(json);
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
		return picService.searchdata(json);
	}
	
	/**
	 * 图片上传
	 * @param multipartfiles
	 * @return
	 */
	@RequestMapping("/uploatpic")
	@ResponseBody
	public ResultCodeVo  uploatpic(@RequestParam("file")MultipartFile  file,HttpServletRequest request)
	{
		return picService.uploadImage(file,request);
	}
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
