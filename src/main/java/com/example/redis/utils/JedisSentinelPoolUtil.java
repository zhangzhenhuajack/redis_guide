package com.example.redis.utils;

import com.google.common.collect.Sets;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 * Created By jack on 16/12/2017
 * 通过哨兵获得一个Master连接，DEMO
 **/
public class JedisSentinelPoolUtil {
    public static void main(String[] args) {
        //添加N个哨兵
        JedisSentinelPool pool = new JedisSentinelPool("redis_master_name",Sets.newHashSet("127.0.0.1:63791","127.0.0.1:63792"));
        //获得Master节点，如果有问题会重新通过哨兵获得一个Master节点
        Jedis jedis = pool.getResource();
        try {
            jedis.set("foot", "bar");
            String value = jedis.get("foot");
        } finally {
            //注意关闭
            jedis.close();
        }
    }
}
