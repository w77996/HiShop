package com.hishop.protal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hishop.common.util.HishopResult;
import com.hishop.protal.pojo.CartItem;

public interface CarService {

	HishopResult addCarItem(long itemId,int num,HttpServletRequest request,HttpServletResponse response);
	List<CartItem> getCartItemList(HttpServletRequest request,HttpServletResponse response);
	HishopResult deleteCartItem(long itemId,HttpServletRequest request,HttpServletResponse response);
}
