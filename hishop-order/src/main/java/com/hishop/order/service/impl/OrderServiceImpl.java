package com.hishop.order.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.hishop.common.util.HishopResult;
import com.hishop.mapper.TbOrderItemMapper;
import com.hishop.mapper.TbOrderMapper;
import com.hishop.mapper.TbOrderShippingMapper;
import com.hishop.order.dao.JedisClient;
import com.hishop.order.service.OrderService;
import com.hishop.pojo.TbOrder;
import com.hishop.pojo.TbOrderItem;
import com.hishop.pojo.TbOrderShipping;
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	TbOrderMapper tbOrderMapper;
	@Autowired
	TbOrderItemMapper tbOrderItemMapper;
	@Autowired
	TbOrderShippingMapper tbOrderShippingMapper;
	@Autowired
	JedisClient jedisClient;
	@Value("${ORDER_GEN_KEY}")
	public String ORDER_GEN_KEY;
	@Value("${ORDER_INIT_ID}")
	public String ORDER_INIT_ID;
	@Value("${ORDER_DETAIL_GEN_KEY}")
	public String ORDER_DETAIL_GEN_KEY;
	@Override
	public HishopResult createOrder(TbOrder order, List<TbOrderItem> items, TbOrderShipping orderShipping) {
		// TODO Auto-generated method stub
		
		
		String st = jedisClient.get(ORDER_GEN_KEY);
		if(StringUtils.isEmpty(st)){
			jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
		}
		long orderId = jedisClient.incr(ORDER_GEN_KEY);
		order.setOrderId(orderId+"");
		order.setStatus(1);
		Date date = new Date();
		order.setCreateTime(date);
		order.setUpdateTime(date);
		order.setBuyerRate(0);
		for (TbOrderItem tbOrderItem : items) {
			long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
			tbOrderItem.setId(orderDetailId+"");
			tbOrderItem.setOrderId(orderId+"");
			tbOrderItemMapper.insert(tbOrderItem);
		}
		orderShipping.setOrderId(orderId+"");
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		tbOrderShippingMapper.insert(orderShipping);
		return HishopResult.ok(orderId);
	}

}
