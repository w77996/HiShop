package com.hishop.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hishop.common.util.ExceptionUtil;
import com.hishop.common.util.HishopResult;
import com.hishop.pojo.TbContent;
import com.hishop.rest.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/list/{contentCategoryId}")
	@ResponseBody
	public HishopResult getContentList(@PathVariable Long contentCategoryId) {
		try {
			List<TbContent> list = contentService.getContentList(contentCategoryId);
			return HishopResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return HishopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
