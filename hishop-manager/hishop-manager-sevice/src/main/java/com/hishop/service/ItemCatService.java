package com.hishop.service;

import java.util.List;

import com.hishop.common.pojo.EUTreeNode;

public interface ItemCatService {
	List<EUTreeNode> getCatList(long parentId);
}
