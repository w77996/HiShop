package com.hishop.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.hishop.search.pojo.SearchResult;

public interface SearchDao {
	SearchResult search(SolrQuery solrQuery) throws Exception;
}
