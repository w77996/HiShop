package com.hishop.service.impl;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hishop.common.pojo.EUDataGridResult;
import com.hishop.common.util.HishopResult;
import com.hishop.common.util.IDUtils;
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
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		// TODO Auto-generated method stub
		TbItemExample tbItemExample = new TbItemExample();
		PageHelper.startPage(page, rows);
		
		List<TbItem> list = tbitemMapper.selectByExample(tbItemExample);
		System.out.println(list.size());
		EUDataGridResult euDataGridResult = new EUDataGridResult();
		euDataGridResult.setRows(list);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		euDataGridResult.setTotal(pageInfo.getTotal());
		return euDataGridResult;
	}
	@Override
	public HishopResult creatItem(TbItem item) {
		// TODO Auto-generated method stub
		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte) 1);
		item.setUpdated(new Date());
		item.setCreated(new Date());
		tbitemMapper.insert(item);
		return HishopResult.ok();
	}

}
