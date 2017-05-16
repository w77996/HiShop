package com.hishop.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.wrapper.BaseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hishop.common.util.HishopResult;
import com.hishop.common.util.JsonUtils;
import com.hishop.mapper.TbItemDescMapper;
import com.hishop.mapper.TbItemMapper;
import com.hishop.mapper.TbItemParamItemMapper;
import com.hishop.mapper.TbItemParamMapper;
import com.hishop.pojo.TbItem;
import com.hishop.pojo.TbItemDesc;
import com.hishop.pojo.TbItemParam;
import com.hishop.pojo.TbItemParamItem;
import com.hishop.pojo.TbItemParamItemExample;
import com.hishop.pojo.TbItemParamItemExample.Criteria;
import com.hishop.rest.dao.JedisClient;
import com.hishop.rest.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${JEDIS_ITEM_EXPIRE}")
	private Integer JEDIS_ITEM_EXPIRE;
	@Autowired
	private JedisClient jedisClient;
	@Override
	public HishopResult getItemBaseInfo(long itemId) {
		// TODO Auto-generated method stub
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY+":"+itemId+":base");
			if(!StringUtils.isBlank(json)){
				TbItem item = JsonUtils.jsonToPojo(json,TbItem.class);
				return HishopResult.ok(item);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		TbItem item = tbItemMapper.selectByPrimaryKey(itemId);
		try {
			jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":base", JsonUtils.objectToJson(item));
			jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":base", JEDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HishopResult.ok(item);
	}
	@Override
	public HishopResult getItemDesc(long itemId) {
		// TODO Auto-generated method stub
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY+":"+itemId+":desc");
			if(!StringUtils.isBlank(json)){
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(json,TbItemDesc.class);
				return HishopResult.ok(itemDesc);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
		try {
			jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":desc", JsonUtils.objectToJson(tbItemDesc));
			jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":desc", JEDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return HishopResult.ok(tbItemDesc);
	}
	@Override
	public HishopResult getItemParam(long itemId) {
		// TODO Auto-generated method stub
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY+":"+itemId+":param");
			if(!StringUtils.isBlank(json)){
				TbItemParamItem itemParamItem = JsonUtils.jsonToPojo(json,TbItemParamItem.class);
				return HishopResult.ok(itemParamItem);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		TbItemParamItemExample tbItemParamItemExample = new TbItemParamItemExample();
		Criteria criteria = tbItemParamItemExample.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(tbItemParamItemExample);
		TbItemParamItem tbItemParamItem = null;
		if(list!=null&&list.size()>0){
			 tbItemParamItem = list.get(0);
			 try {
					jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":param", JsonUtils.objectToJson(tbItemParamItem));
					jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":param", JEDIS_ITEM_EXPIRE);
					return HishopResult.ok(tbItemParamItem);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			
		}
		return HishopResult.build(400, "无此商品信息");
	}

}
