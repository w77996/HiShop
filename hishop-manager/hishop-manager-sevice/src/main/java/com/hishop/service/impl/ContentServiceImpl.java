package com.hishop.service.impl;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hishop.common.util.HishopResult;
import com.hishop.common.util.HttpClientUtil;
import com.hishop.mapper.TbContentMapper;
import com.hishop.pojo.TbContent;
import com.hishop.service.ContentService;
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper tbContentMapper;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;
	@Override
	public HishopResult insertContent(TbContent tbContent) {
		// TODO Auto-generated method stub
		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		tbContentMapper.insert(tbContent);
		try {
			HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+tbContent.getCategoryId());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			return HishopResult.ok();
	}

}
