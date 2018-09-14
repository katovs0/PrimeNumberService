package com.service.number.solr;

import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
class HttpSolrSearchService implements SolrSearchService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public SolrDocumentList getSolrCollection(String collection, Map<String, String> queryParamsMap) {
        return null;
    }

    @Override
    public String getSolrSearch(String collection, Map<String, String> queryString) {
        return getStubSolrSearch();
    }

    private String getStubSolrSearch() {
        String url;
        RestTemplate httpClient = new RestTemplate();
        url
                = "http://192.168.1.64:8983/solr/techproducts/select?q=*:*";

        ResponseEntity<String> response
                = httpClient.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            logger.info("Received successful response: {}", response.getStatusCode());
            return response.getBody();
        } else {
            logger.error("Received ERROR response: {}", response.getStatusCode());
            return "ERROR";

        }
    }

}
