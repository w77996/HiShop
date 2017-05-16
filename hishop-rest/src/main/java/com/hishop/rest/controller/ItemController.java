package com.hishop.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hishop.common.util.HishopResult;
import com.hishop.rest.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public HishopResult getItemBaseInfo(@PathVariable long itemId){
		HishopResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public HishopResult getItemDescInfo(@PathVariable long itemId){
		HishopResult result = itemService.getItemDesc(itemId);
		return result;
	}
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public HishopResult getItemDescParam(@PathVariable long itemId){
		HishopResult result = itemService.getItemParam(itemId);
		return result;
	}
}
