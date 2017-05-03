package com.hishop.rest.service;

import java.util.List;

import com.hishop.pojo.TbContent;

public interface ContentService {
	List<TbContent> getContentList(long contentCid);
}
