package com.gcexe.monitor.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.gcexe.monitor.utils.ResultCodeVo;

public interface PicService extends BaseService {

	public ResultCodeVo uploadImage(MultipartFile file,HttpServletRequest request);
}
