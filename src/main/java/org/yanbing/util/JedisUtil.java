package org.yanbing.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JedisUtil {

    @Autowired
    private JedisPool jedisPool;

    private Jedis getJedis(){
        return jedisPool.getResource();
    }


    public byte[] set(byte[] key, byte[] value,int expire) {
        Jedis jedis = getJedis();
        try {
            jedis.setex(key,expire,value);
            return value;
        }finally {
            jedis.close();
        }
    }

    public void expire(byte[] key, int i) {
        Jedis jedis = getJedis();
        try {
            jedis.expire(key,i);
        }finally {
            jedis.close();
        }
    }

    public byte[] get(byte[] key) {
        Jedis jedis = getJedis();
        try {
            return jedis.get(key);
        }finally {
            jedis.close();
        }
    }

    public void del(byte[] key) {
        Jedis jedis = getJedis();
        try {
            jedis.del(key);
        }finally {
            jedis.close();
        }
    }

    public Set<byte[]> keys(String shiroSessionPrefix) {
        Jedis jedis = getJedis();
        try {
            return jedis.keys(shiroSessionPrefix+"*").stream().map(e->e.getBytes()).collect(Collectors.toSet());
        }finally {
            jedis.close();
        }
    }
}
