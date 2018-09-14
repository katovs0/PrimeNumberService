package com.service.number.solr;

import org.apache.solr.common.SolrDocumentList;

import java.util.Map;

public interface SolrSearchService {

    String getSolrSearch(String collection, Map<String, String> queryParamsMap);

    SolrDocumentList getSolrCollection(String collection, Map<String, String> queryParamsMap);
}
