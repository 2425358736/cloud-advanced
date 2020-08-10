package com.cloud.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author 刘志强
 * @date 2020/8/10 11:36
 * redis分布式锁
 */
@Component
public class RedisLock {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获得锁 ture 获得锁 false 没有获得锁
     * @param key
     * @return
     */
    private Boolean getLock(String key) {
        Boolean on;
        on = (Boolean) redisTemplate.execute(
                (RedisCallback<Boolean>) conn -> {
                    try {
                        Boolean on1 =  conn.setNX(redisTemplate.getStringSerializer().serialize(key),
                                redisTemplate.getStringSerializer().serialize(key));
                        if (on1) {
                            // 防止死锁。 10秒后删除key
                            redisTemplate.expire(key, 10, TimeUnit.SECONDS);
                        }
                        return on1;
                    } finally {
                        // 关闭释放链接
                        conn.close();
                    }
                });
        return on;
    }

    /**
     * 释放锁
     * @param key
     */
    private void unLock(String key) {
        redisTemplate.delete(key);
    }


    /**
     * 等待获取锁
     * @param key
     * @param second
     */
    private Boolean waitLock(String key, Long second) throws InterruptedException {
        Boolean on;
        for (int i = 0; i < second; i++) {
            Thread.sleep(1000);
            on = getLock(key);
            if (on) {
                return true;
            }
        }
        return  false;
    }

}
