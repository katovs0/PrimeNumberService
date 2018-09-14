package com.service.number;

import com.service.number.domain.SolrSearchResponse;
import com.service.number.solr.SolrSearchService;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SolrTestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SolrSearchService defaultSolrSearchService;

    @GetMapping(value = "/solr/{collection}",
            produces = {"application/json"})
    public SolrSearchResponse getSolrSearchResponse(@PathVariable String collection,
                                                    @RequestParam(name = "q", required = false, defaultValue = "*:*") String q,
                                                    @RequestParam(name = "fl", required = false, defaultValue = "id, name") String fl,
                                                    @RequestParam(name = "rows", required = false, defaultValue = "10") String rows,
                                                    @RequestParam(name = "sort", required = false, defaultValue = "id asc") String sort) {

        final Map<String, String> queryParamMap = new HashMap<String, String>();
        queryParamMap.put("q", q);
        queryParamMap.put("fl", fl);
        queryParamMap.put("rows", rows);
        queryParamMap.put("sort", sort);

        logger.info("Start querying Solr collection [{}], with params: {}", collection, queryParamMap);
        SolrDocumentList result;
        long start = System.currentTimeMillis();

        result = defaultSolrSearchService.getSolrCollection(collection, queryParamMap);


        logger.info("Finished querying Solr collection [{}] took {} ms.",
                collection,
                System.currentTimeMillis() - start);

        return new SolrSearchResponse(collection, result);
    }

    @GetMapping(value = "/solr/",
            produces = {"application/json"})
    public SolrSearchResponse getSolrSearchResponse() {
        return getSolrSearchResponse("techproducts", "*:*", "id, name", "10", "id asc");
    }

}