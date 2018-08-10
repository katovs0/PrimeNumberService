package com.kdb.dao;

import kx.c;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

public class KdbTickerplantDao implements KdbDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private c connection;

    public KdbTickerplantDao(c connection) {
        logger.info("Creating new KdbTickerplantDao");
        this.connection = connection;
    }

    @Override
    public Object executeQuery(Object[] query) throws IOException {
        logger.info("Executing query {}", Arrays.deepToString(query));
        try {
            return connection.k(query);
        } catch (c.KException e) {
            logger.error("There were an KExcetion while executing sync query {}", Arrays.deepToString(query), e);
            throw new RuntimeException(String.format("Failed to execute KDB query {}. Exception {}", Arrays.deepToString(query), e));
        }
    }

    @Override
    public Object executeQuery(String query) throws IOException {
        logger.info("Executing query {}", query);
        try {
            return connection.k(query);
        } catch (c.KException e) {
            logger.error("There were an KExcetion while executing sync query {}", query, e);
            throw new RuntimeException(String.format("Failed to execute KDB query {}. Exception {}", query, e));
        }
    }

    @Override
    public void executeAsyncQuery(String func, String table, Object[] params) throws IOException {
        logger.info("Executing query: {} {} {}",func, table, Arrays.deepToString(params));
        connection.ks(func, table, params);
    }

    @Override
    public void executeAsyncStatement(Object[] statement) throws IOException {
        logger.info("Executing query {}", Arrays.deepToString(statement));
        connection.ks(statement);
    }

    @Override
    public c getConnection() {
        return this.connection;
    }

    @Override
    public boolean isValid() {
        try{
            connection.k("1 + 1");
            return true;
        } catch (Exception e) {
            logger.warn("There was exception while validating the current connection...");
            return false;
        }
    }
}
