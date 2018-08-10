package com.kdb.dao;


import kx.c;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class KdbDaoFactory extends BasePooledObjectFactory<KdbDao> {
    private static final int TIMEOUT = 10;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String host;
    private int port;
    private String username;
    private char[] password;

    public KdbDaoFactory(String host, int port, String username, char[] password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public KdbDaoFactory(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public KdbDao create() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Callable<c> getConnectionTask;
        if(null != username && null != password) {
            getConnectionTask = () -> new c(host, port, username + ":" + String.valueOf(password));
        } else {
            getConnectionTask = () -> new c(host, port);

        }
        Future<c> futureConnection = executorService.submit(getConnectionTask);

        c connection = futureConnection.get(TIMEOUT, TimeUnit.SECONDS);
        futureConnection.cancel(true);

        return new KdbTickerplantDao(connection);
    }

    @Override
    public PooledObject<KdbDao> wrap(KdbDao kdbDao) {
        return new DefaultPooledObject<>(kdbDao);
    }



    @Override
    public boolean validateObject(PooledObject<KdbDao> p) {
        return p.getObject().isValid();
    }

    @Override
    public void destroyObject(PooledObject<KdbDao> p) throws Exception {
        logger.info("Destroying object {}", p.hashCode());
        p.getObject().getConnection().close();
        p.invalidate();
    }
}
