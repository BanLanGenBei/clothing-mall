package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2021/2/4 19:27
 */
@SpringBootTest
public class RedisTest {
    @Resource
    private ValueOperations<String,String> valueOperations;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedis(){
        stringRedisTemplate.opsForValue().set("chencong","1234");

    }


}
