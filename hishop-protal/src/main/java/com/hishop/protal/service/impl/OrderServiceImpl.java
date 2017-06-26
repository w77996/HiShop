package com.hishop.protal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hishop.common.util.HishopResult;
import com.hishop.common.util.HttpClientUtil;
import com.hishop.common.util.JsonUtils;
import com.hishop.protal.pojo.Order;
import com.hishop.protal.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
	@Value("${ORDER_BASE_URL}")
	public String ORDER_BASE_URL;
	@Value("${ORDER_CREAT_URL}")
	public String ORDER_CREAT_URL;
	@Override
	public String creatOrder(Order order) {
		// TODO Auto-generated method stub
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL+ORDER_CREAT_URL,JsonUtils.objectToJson(order));
		HishopResult result = HishopResult.format(json);
		if(result.getStatus()==200){
			Object orderId = (Long) result.getData();
			return orderId.toString();
		}
		return "";
	}

}
