package com.example.redis.utils;

import com.google.common.collect.Lists;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.List;

/**
 * 简单测试切片的写法
 */
public class ShardedJedisPoolUtil {
	public static void main(String[] args) {
		List<JedisShardInfo> shards = Lists.newArrayList();
		shards.add(new JedisShardInfo("127.0.0.1",6379));
		shards.add(new JedisShardInfo("127.0.0.1",6378));
		//通过list可以创建N个切片

		ShardedJedisPool shardedJedisPool = new ShardedJedisPool(new GenericObjectPoolConfig(),shards);
		ShardedJedis shardedJedis =shardedJedisPool.getResource();
		shardedJedis.set("key1","abc");
		System.out.println(shardedJedis.get("key1"));
	}
}
