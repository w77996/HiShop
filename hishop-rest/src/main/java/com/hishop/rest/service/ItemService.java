package com.hishop.rest.service;

import com.hishop.common.util.HishopResult;

public interface ItemService {
	HishopResult getItemBaseInfo(long itemId);
	HishopResult getItemDesc(long itemId);
	HishopResult getItemParam(long itemId);
}
