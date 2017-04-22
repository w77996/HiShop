package com.hishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hishop.common.pojo.EUDataGridResult;
import com.hishop.common.util.HishopResult;
import com.hishop.pojo.TbItem;
import com.hishop.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemid}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemid){
		TbItem tbItem = itemService.getItemById(itemid);
		return tbItem;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="30")Integer row){
		EUDataGridResult euDataGridResult = itemService.getItemList(page, row);
		return euDataGridResult;
	}
	
	@RequestMapping(value="/item/save",method= RequestMethod.POST)
	@ResponseBody
	private HishopResult creatItem(TbItem item,String desc)throws Exception{
		HishopResult hishopResult = itemService.creatItem(item,desc) ;
		return hishopResult;
	}
}
