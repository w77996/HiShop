package com.hishop.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hishop.common.util.HishopResult;
import com.hishop.rest.service.RedisService;

@Controller
@RequestMapping("/cache/sync")
public class RedisController {
	@Autowired
	private RedisService redisService;
	@RequestMapping("/content/{contentCid}")
	@ResponseBody
	public HishopResult contentCacheSync(@PathVariable long contentCid){
		HishopResult result = redisService.syncContent(contentCid);
		return result;
	}
}
