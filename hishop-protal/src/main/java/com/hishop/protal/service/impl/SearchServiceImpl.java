package com.hishop.protal.service.impl;

import java.util.HashMap;
import java.util.Map;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hishop.common.util.HishopResult;
import com.hishop.common.util.HttpClientUtil;
import com.hishop.protal.pojo.SearchResult;
import com.hishop.protal.service.SearchService;
@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL; 
	@Override
	public SearchResult search(String queryString, int page) {
		// TODO Auto-generated method stub
		Map<String,String> param = new HashMap<>();
		param.put("q", queryString);
		param.put("page", page+"");
		String json = HttpClientUtil.doGet(SEARCH_BASE_URL,param);
		try {
			HishopResult hishopResult = HishopResult.formatToPojo(json, SearchResult.class);
			
			if (hishopResult.getStatus()==200) {
				SearchResult result = (SearchResult) hishopResult.getData();
				return result;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

}
