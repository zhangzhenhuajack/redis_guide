package com.example.redis.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created By jack on 16/12/2017
 *
 * 多线程环境下，线程池的正确使用方法，单例的连接池，单例的配置。
 * 此处给大家提供一个种思路，如果用spring boot的话，可以基于@Configuration 和@Bean的配置方法，此处仅仅是举例说明。
 **/
public class JedisClientPoolUtil {
    @Value("{spring.redis.host}")
    private static String host;
    @Value("{spring.redis.port}")
    private static Integer port;

    private final static byte[] temp_lock = new byte[1];
    private static JedisPool jedisPool;

    /**
     * 把连接池做成单例的，这点需要注意
     *
     * @return
     */
    private static JedisPool getJedisPool() {
        if (jedisPool == null) {
            synchronized (temp_lock) {
                if (jedisPool == null) {
                    jedisPool = new JedisPool(jedisPoolConfig(),host,port);
                }
            }
        }
        return jedisPool;
    }

    /**
     * 设置一些连接池的配置，来管理每一个连接。
     *
     * @return
     */
    private static JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxWaitMillis(1000);
        return jedisPoolConfig;
    }

    /**
     * 对外只暴露这一个方法即可
     *
     * @return
     */
    public static Jedis getJedis(){
        return getJedisPool().getResource();
    }
    public static void main(String[] args) {
//        JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1",6379);
//        Jedis jedis = pool.getResource();
        Jedis jedis = JedisClientPoolUtil.getJedis();
        try {
            jedis.set("foot", "bar");
            String value = jedis.get("foot");
            System.out.println(value);
        } finally {
            //注意关闭

            jedis.close();
        }
    }
}
