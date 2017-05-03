package com.hishop.rest.service;

import com.hishop.common.util.HishopResult;

public interface RedisService {
	HishopResult syncContent(long contentCid);
}
