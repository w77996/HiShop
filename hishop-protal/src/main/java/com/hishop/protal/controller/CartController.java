package com.hishop.protal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hishop.common.util.HishopResult;
import com.hishop.protal.pojo.CartItem;
import com.hishop.protal.service.CarService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	public CarService carService;
	
	@RequestMapping("/add/{itemId}")
	public String addCartItem(@PathVariable Long itemId,@RequestParam(defaultValue="1")Integer num,HttpServletRequest request,HttpServletResponse response){
		HishopResult result = carService.addCarItem(itemId, num, request, response);
		return "redirect:/cart/success.html";
	}
	
	@RequestMapping("/success")
	public String showSuccess(){
		return "cartSuccess";
	}
	@RequestMapping("/cart")
	public String showCart(HttpServletRequest request,HttpServletResponse response,Model model){
		List<CartItem> list = carService.getCartItemList(request, response);
		model.addAttribute("cartList",list);
		return "cart";
	}
	@RequestMapping("/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId,HttpServletRequest request,HttpServletResponse response){
		HishopResult result = carService.deleteCartItem(itemId, request, response);
		return "redirect:/cart/cart.html";
	}
}
