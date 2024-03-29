package com.leemon.mall.service;

/**
 * redis操作service
 * 对象和数组都以JSON形式存储
 */
public interface RedisService {
    /**
     * 存储数据
     *
     * @param key
     * @param value
     */
    void set(String key, String value);

    String get(String key);

    /**
     * 设置超时时间
     *
     * @param key
     * @param expire
     * @return
     */
    boolean expire(String key, long expire);

    void remove(String key);

    /**
     * 自增操作
     *
     * @param key
     * @param delta 自增步长
     * @return
     */
    Long increment(String key, long delta);

}
