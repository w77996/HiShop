package com.hishop.order.pojo;

import java.util.List;

import com.hishop.pojo.TbOrder;
import com.hishop.pojo.TbOrderItem;
import com.hishop.pojo.TbOrderShipping;

public class Order extends TbOrder{
	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
}
