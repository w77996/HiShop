package com.hishop.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hishop.common.util.HishopResult;
import com.hishop.search.service.ItemService;

@Controller
@RequestMapping("/manager")
public class ItemController {
	@Autowired
	private ItemService itemService;
	@RequestMapping("/importall")
	@ResponseBody
	public HishopResult importAllItems(){
		HishopResult hishopResult =null;
		try {
			hishopResult = itemService.importAllItems();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hishopResult;
	}
}
