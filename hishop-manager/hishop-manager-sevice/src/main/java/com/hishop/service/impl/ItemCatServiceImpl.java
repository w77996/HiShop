package com.hishop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hishop.common.pojo.EUTreeNode;
import com.hishop.mapper.TbItemCatMapper;
import com.hishop.pojo.TbItemCat;
import com.hishop.pojo.TbItemCatExample;
import com.hishop.pojo.TbItemCatExample.Criteria;
import com.hishop.service.ItemCatService;

@Service 
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public List<EUTreeNode> getCatList(long parentId) {
		// TODO Auto-generated method stub
		TbItemCatExample tbItemCatExample = new TbItemCatExample();
		Criteria criteria =tbItemCatExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		List<TbItemCat> list = itemCatMapper.selectByExample(tbItemCatExample);
		List<EUTreeNode> resultList = new ArrayList<>();
		for(TbItemCat tbItemCat : list){
			EUTreeNode node =new EUTreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
	}

}
