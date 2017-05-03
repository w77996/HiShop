package com.hishop.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hishop.common.util.ExceptionUtil;
import com.hishop.common.util.HishopResult;
import com.hishop.rest.dao.JedisClient;
import com.hishop.rest.service.RedisService;
@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private JedisClient jedisClient;
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	@Override
	public HishopResult syncContent(long contentCid) {
		// TODO Auto-generated method stub
		try {
			jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid+"");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return HishopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return HishopResult.ok();
	}
	
}
