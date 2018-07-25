package org.yanbing.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml","classpath:spring/spring-jedis.xml"})
public class JedisUtilTest {

    @Autowired
    private JedisUtil jedisUtil;

    String key="yanbing";
    String value="严兵";

    @Test
    public void set() {
        byte[] set = jedisUtil.set(key.getBytes(), value.getBytes(), 600);
        Assert.assertEquals(value,new String(set));
    }

    @Test
    public void expire() {
    }

    @Test
    public void get() {
        Assert.assertEquals(value,new String(jedisUtil.get(key.getBytes())));
    }

    @Test
    public void del() {
    }

    @Test
    public void keys() {
    }
}