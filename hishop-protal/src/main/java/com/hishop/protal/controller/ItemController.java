package com.hishop.protal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hishop.pojo.TbItem;
import com.hishop.protal.pojo.ItemInfo;
import com.hishop.protal.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable Long itemId,Model model){
		ItemInfo item = itemService.getItemById(itemId);
		model.addAttribute("item",item);
		return "item";
	}
	@RequestMapping(value="/item/desc/{itemId}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable Long itemId){
		String result = itemService.getItemDescById(itemId);
		
		return result;
	}
	@RequestMapping(value="/item/param/{itemId}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId){
		String result = itemService.getItemParam(itemId);
		
		return result;
	}
}
