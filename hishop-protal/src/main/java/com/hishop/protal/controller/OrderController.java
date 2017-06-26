package com.hishop.protal.controller;

import java.util.List;
import java.util.function.Predicate;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hishop.protal.pojo.CartItem;
import com.hishop.protal.pojo.Order;
import com.hishop.protal.service.CarService;
import com.hishop.protal.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private CarService cartService;
	@Autowired 
	private OrderService orderService;
	@RequestMapping("/order-cart")
	public String showOrderCat(HttpServletRequest request,HttpServletResponse response,Model model){
		
		List<CartItem> list = cartService.getCartItemList(request, response);
		model.addAttribute("cartList",list);
		return "order-cart";
	}
	
	@RequestMapping("/creat")
	public String createOrder(Order order,Model model){
		String orderId = orderService.creatOrder(order);
		model.addAttribute("orderId", orderId);
		model.addAttribute("payment", order.getPayment());
		model.addAttribute("date", new DateTime().plus(3).toString("yyyy-MM-dd"));
		return "success";
	}
}
