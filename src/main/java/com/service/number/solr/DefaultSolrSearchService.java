package com.service.number.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
class DefaultSolrSearchService implements SolrSearchService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final List<String> solrUrls = Arrays.asList("http://localhost:8983/solr", "http://localhost:7574/solr");


    @Override
    public String getSolrSearch(String collection, Map<String, String> queryParamsMap) {
        return getStubSolrSearch(collection, queryParamsMap).toString();
    }

    @Override
    public SolrDocumentList getSolrCollection(String collection, Map<String, String> queryParamsMap) {
        return getStubSolrSearch(collection, queryParamsMap);
    }

    private SolrDocumentList getStubSolrSearch(String collection, Map<String, String> queryParamsMap) {
        final SolrClient solrClient = getCloudSolrClient(solrUrls);
        MapSolrParams queryParams = new MapSolrParams(queryParamsMap);
        QueryResponse response = null;

        try {
            response = solrClient.query(collection, queryParams);

        } catch (SolrServerException e) {
            logger.error("There was a SolrServerException while getting solr collection {} with parameters: {}",
                    collection, queryParamsMap, e);
        } catch (IOException e) {
            logger.error("There was an IOException while getting solr collection {} with parameters: {}",
                    collection, queryParamsMap, e);
        } finally {
            if (null != solrClient) {
                try {
                    solrClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (null != response && response.getStatus() == 0) {
            logger.info("Received successful response: {}", response.getStatus());
            final SolrDocumentList documents = response.getResults();
            return documents;
        } else {
            if (null == response) {
                logger.error("Received NULL Response.");
            } else {
                logger.error("Received ERROR response: {}", response.getStatus());
            }
            return null;

        }
    }

    private CloudSolrClient getCloudSolrClient(List<String> solrUrls) {
        return new CloudSolrClient.Builder(solrUrls)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
    }

}
