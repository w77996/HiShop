package com.hishop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hishop.common.util.HishopResult;
import com.hishop.mapper.TbItemParamMapper;
import com.hishop.pojo.TbItemParam;
import com.hishop.pojo.TbItemParamExample;
import com.hishop.service.ItemParamService;
@Service
public class ItemParamServiceImpl implements ItemParamService {
	
	@Autowired
	private TbItemParamMapper tbitemParamMapper;
	@Override
	public HishopResult getItemParamByCid(long cid) {
		// TODO Auto-generated method stub
		TbItemParamExample tbItemParamExample = new TbItemParamExample();
		
		com.hishop.pojo.TbItemParamExample.Criteria criteria = tbItemParamExample.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = tbitemParamMapper.selectByExample(tbItemParamExample);
		if(list != null && list.size() > 0){
			return HishopResult.ok(list.get(0));
		}
		
		return HishopResult.ok();
	}

}
