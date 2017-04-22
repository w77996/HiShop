package com.hishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hishop.common.util.HishopResult;
import com.hishop.service.ItemParamService;
import com.hishop.service.ItemService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public HishopResult getItemParamByCid(@PathVariable Long itemCatId){
		HishopResult hishopResult = itemParamService.getItemParamByCid(itemCatId);
		return hishopResult;
		
	}
	
	
	
}
