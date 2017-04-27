package com.hishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hishop.common.util.HishopResult;
import com.hishop.pojo.TbContent;
import com.hishop.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/save")
	@ResponseBody
	public HishopResult insertContent(TbContent tbContent){
		HishopResult hishopResult = contentService.insertContent(tbContent);
		return hishopResult;
	}
}
