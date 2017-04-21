package com.hishop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hishop.common.util.JsonUtils;
import com.hishop.service.PictureService;

@Controller
public class PictureController {
	
	@Autowired
	private PictureService pictureService;
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String pictureUpload(MultipartFile uploadFile){
		Map result = pictureService.uploadPicture(uploadFile);
		String json = JsonUtils.objectToJson(result);
		return json;
	}
	
}
