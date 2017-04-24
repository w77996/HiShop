package com.hishop.service;

import com.hishop.common.util.HishopResult;
import com.hishop.pojo.TbItemParam;

public interface ItemParamService {
	HishopResult getItemParamByCid(long cid);
	HishopResult insertItemParam(TbItemParam tbItemParam);
}
