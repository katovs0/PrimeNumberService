package com.kdb.dao;

import kx.c;

import java.io.IOException;

public interface KdbDao {

    Object executeQuery(Object[] query) throws IOException;

    Object executeQuery(String query) throws IOException;

    void executeAsyncQuery(String func, String table, Object[] params) throws IOException;

    void executeAsyncStatement(Object[] statement) throws IOException;

    c getConnection();

    boolean isValid();


}
