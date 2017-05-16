package com.hishop.protal.service;

import com.hishop.protal.pojo.SearchResult;

public interface SearchService {
	SearchResult search(String queryString,int page);
}
