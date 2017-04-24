package com.hishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hishop.service.ItemParamItemService;

@Controller
public class ItemParaItemContraller {
	@Autowired
	private ItemParamItemService itemParamItemService;
	
	@RequestMapping("/showitem/{itemId}")
	private String  showItemParam(@PathVariable Long itemId,Model model){
		String data = itemParamItemService.getItemParamByItemId(itemId);
		model.addAttribute("itemParam",data);
		return "item";
	}

}
