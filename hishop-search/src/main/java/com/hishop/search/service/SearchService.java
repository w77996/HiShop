package com.hishop.search.service;

import com.hishop.search.pojo.SearchResult;

public interface SearchService {
	SearchResult search(String queryString,int page,int rows) throws Exception;
}
