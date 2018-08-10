package com.kdb.dao;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class KdbDaoPool extends GenericObjectPool<KdbDao> {

    public KdbDaoPool(PooledObjectFactory<KdbDao> factory) {
        super(factory);
    }

    public KdbDaoPool(PooledObjectFactory<KdbDao> factory, GenericObjectPoolConfig config) {
        super(factory, config);
    }

    public KdbDaoPool(PooledObjectFactory<KdbDao> factory, GenericObjectPoolConfig config, AbandonedConfig abandonedConfig) {
        super(factory, config, abandonedConfig);
    }
}
