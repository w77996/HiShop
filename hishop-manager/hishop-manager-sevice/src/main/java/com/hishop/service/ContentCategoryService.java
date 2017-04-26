package com.hishop.service;

import java.util.List;

import com.hishop.common.pojo.EUTreeNode;
import com.hishop.common.util.HishopResult;

public interface ContentCategoryService {
	List<EUTreeNode> getCategoryList(long parentId);

	HishopResult insertContentCategory(long parentId, String name);
}
