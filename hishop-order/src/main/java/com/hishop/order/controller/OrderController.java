package com.hishop.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hishop.common.util.HishopResult;
import com.hishop.order.pojo.Order;
import com.hishop.order.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/creat",method=RequestMethod.POST)
	@ResponseBody
	public HishopResult creatOrder(@RequestBody Order  order){
		try {
			HishopResult result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
			return result;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return HishopResult.build(500, "errorsg"); 
		}
	}
}
