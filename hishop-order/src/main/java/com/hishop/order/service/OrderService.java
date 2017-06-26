package com.hishop.order.service;

import java.util.List;

import com.hishop.common.util.HishopResult;
import com.hishop.pojo.TbOrder;
import com.hishop.pojo.TbOrderItem;
import com.hishop.pojo.TbOrderShipping;

public interface OrderService {
	HishopResult createOrder(TbOrder order,List<TbOrderItem> items,TbOrderShipping orderShipping);;
}
