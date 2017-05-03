package com.hishop.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hishop.common.util.JsonUtils;
import com.hishop.mapper.TbContentMapper;
import com.hishop.pojo.TbContent;
import com.hishop.pojo.TbContentExample;
import com.hishop.pojo.TbContentExample.Criteria;
import com.hishop.rest.dao.JedisClient;
import com.hishop.rest.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper tbContentMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	@Override
	public List<TbContent> getContentList(long contentCid) {
		// TODO Auto-generated method stub
		//从缓存中取内容
				try {
					String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid + "");
					if (!StringUtils.isBlank(result)) {
						//把字符串转换成list
						List<TbContent> resultList = JsonUtils.jsonToList(result, TbContent.class);
						return resultList;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//根据内容分类id查询内容列表
				TbContentExample example = new TbContentExample();
				Criteria criteria = example.createCriteria();
				criteria.andCategoryIdEqualTo(contentCid);
				//执行查询
				List<TbContent> list = tbContentMapper.selectByExample(example);
				
				//向缓存中添加内容
				try {
					//把list转换成字符串
					String cacheString = JsonUtils.objectToJson(list);
					jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCid + "", cacheString);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return list;
	}

}
