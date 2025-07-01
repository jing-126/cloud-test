package com.jing.cloud.jedis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("www.jinglecode.top", 6336);
        jedis.auth("jing@1234");
        System.out.println(jedis.ping());

        jedis.set("jedis_str", "demo");
        jedis.sadd("jedis_set", "set_1", "set_2", "set_3");
        jedis.lpush("jedis_list", "list_1", "list_2", "list_3");
        jedis.hset("jedis_hash", "hash_1", "1");
        jedis.zadd("jedis_zset", 10, "zset_1");

        Map<String, Double> zsetMap = new HashMap<>();
        zsetMap.put("zset_2", 5.0);
        zsetMap.put("zset_3", 15.0);
        zsetMap.put("zset_4", 1.0);
        jedis.zadd("jedis_zset", zsetMap);

        Set<String> keys = jedis.keys("jedis_*");
        keys.forEach(System.out::println);

        System.out.println(jedis.get("jedis_str"));

        Set<String> jedisStr = jedis.smembers("jedis_set");
        jedisStr.forEach(System.out::println);

        List<String> jedisLrange = jedis.lrange("jedis_lrange", 0, -1);
        jedisLrange.forEach(System.out::println);

        Map<String, String> jedisHash = jedis.hgetAll("jedis_hash");
        jedisHash.forEach((k, v) -> System.out.println(k + "=" + v));

        List<String> jedisZset = jedis.zrange("jedis_zset", 0, -1);
        jedisZset.forEach(System.out::println);

    }
}
