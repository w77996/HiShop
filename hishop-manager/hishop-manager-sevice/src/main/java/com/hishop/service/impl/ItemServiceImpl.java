package com.hishop.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hishop.mapper.TbItemMapper;
import com.hishop.pojo.TbItem;
import com.hishop.pojo.TbItemExample;
import com.hishop.pojo.TbItemExample.Criteria;
import com.hishop.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper tbitemMapper;
	@Override
	public TbItem getItemById(long id) {
		// TODO Auto-generated method stub
		TbItem tbItem = tbitemMapper.selectByPrimaryKey(id);
		TbItemExample tbItemExample = new TbItemExample();
		Criteria criteria = tbItemExample.createCriteria();
		criteria.andIdEqualTo(id);
		List<TbItem> list = tbitemMapper.selectByExample(tbItemExample);
		if(list != null && list.size() > 0){
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}

}
