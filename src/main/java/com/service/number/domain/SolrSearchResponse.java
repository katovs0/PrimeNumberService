package com.service.number.domain;

import org.apache.solr.common.SolrDocumentList;

public class SolrSearchResponse {
    private String initial;
    private SolrDocumentList result;

    public SolrSearchResponse() {
    }

    public SolrSearchResponse(String initial, SolrDocumentList result) {
        this.initial = initial;
        this.result = result;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public SolrDocumentList getResult() {
        return result;
    }

    public void setResult(SolrDocumentList result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SolrSearchResponse{" +
                "initial='" + initial + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
