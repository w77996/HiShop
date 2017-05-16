package com.hishop.protal.service;

import com.hishop.pojo.TbItem;
import com.hishop.protal.pojo.ItemInfo;

public interface ItemService {
	ItemInfo getItemById(long itemId);
	String getItemDescById(Long itemId);
	String getItemParam(Long itemId);
}
