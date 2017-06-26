package com.hishop.protal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hishop.common.util.CookieUtils;
import com.hishop.common.util.HishopResult;
import com.hishop.common.util.HttpClientUtil;
import com.hishop.common.util.JsonUtils;
import com.hishop.pojo.TbItem;
import com.hishop.protal.pojo.CartItem;
import com.hishop.protal.service.CarService;

@Service
public class addCarServiceImpl implements CarService {

	@Value("${REST_BASE_URL}")
	public String REST_BASE_URL;
	@Value("${ITEM_INFO_URL}")
	public String ITEM_INFO_URL;

	@Override
	public HishopResult addCarItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {

		CartItem cartItem = null;
		List<CartItem> list = getCartItemList(request);
		for (CartItem item : list) {
			if (item.getId() == itemId) {
				item.setNum(item.getNum() + num);
				cartItem = item;
				break;
			}
		}
		if (cartItem == null) {
			cartItem = new CartItem();
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
			HishopResult result = HishopResult.formatToPojo(json, TbItem.class);
			if (result.getStatus() == 200) {
				TbItem item = (TbItem) result.getData();
				cartItem.setId(item.getId());
				cartItem.setPrice(item.getPrice());
				cartItem.setTitle(item.getTitle());
				cartItem.setNum(num);
				cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);
			}
			list.add(cartItem);
		}
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(list), true);
		return HishopResult.ok();
	}

	public List<CartItem> getCartItemList(HttpServletRequest request) {
		String json = CookieUtils.getCookieValue(request, "TT_CART", true);
		if(json==null){
			return new ArrayList<>();
		}
		try {
			List<CartItem> list = JsonUtils.jsonToList(json, CartItem.class);
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> list = getCartItemList(request);
		return list;
	}

	@Override
	public HishopResult deleteCartItem(long itemId,HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<CartItem> list = getCartItemList(request);
		for (CartItem cartItem : list) {
			if(cartItem.getId() == itemId){
				list.remove(cartItem);
				break;
			}
			
		}
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(list),true);
		return HishopResult.ok();
	}
}
