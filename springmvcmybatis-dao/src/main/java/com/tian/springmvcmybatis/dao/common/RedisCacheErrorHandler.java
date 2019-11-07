package com.tian.springmvcmybatis.dao.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.lang.Nullable;

/**
 * 重写redis与spring整合的缓存异常处理器, 当缓存出现异常时, 服务正常运行, 从DB获取数据,
 * Created by tianxiong on 2019/11/7.
 */
public class RedisCacheErrorHandler implements CacheErrorHandler {
    private static final Logger logger = LoggerFactory.getLogger(RedisCacheErrorHandler.class);
    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        logger.warn("redis handlerCacheGetError", exception);
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, @Nullable Object value) {
        logger.warn("redis handleCachePutError", exception);
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        logger.warn("redis handleCacheEvictError", exception);
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        logger.warn("redis handleCacheClearError", exception);
    }
}
