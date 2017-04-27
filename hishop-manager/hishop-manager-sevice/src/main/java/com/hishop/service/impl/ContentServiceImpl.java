package com.hishop.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hishop.common.util.HishopResult;
import com.hishop.mapper.TbContentMapper;
import com.hishop.pojo.TbContent;
import com.hishop.service.ContentService;
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper tbContentMapper;
	@Override
	public HishopResult insertContent(TbContent tbContent) {
		// TODO Auto-generated method stub
		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		tbContentMapper.insert(tbContent);
		return HishopResult.ok();
	}

}
