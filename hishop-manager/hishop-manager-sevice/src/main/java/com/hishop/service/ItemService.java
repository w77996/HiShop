package com.hishop.service;

import com.hishop.common.pojo.EUDataGridResult;
import com.hishop.common.util.HishopResult;
import com.hishop.pojo.TbItem;

public interface ItemService {
	
	TbItem getItemById(long id);
	EUDataGridResult getItemList(int page,int rows);
	HishopResult creatItem(TbItem item,String desc) throws Exception;

}
