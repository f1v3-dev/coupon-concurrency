package com.f1v3.coupon.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Redis Coupon Repository.
 *
 * @author 정승조
 * @version 2024. 10. 07.
 */
@Repository
public class RedisCouponRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisCouponRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Long increment() {
        return redisTemplate
                .opsForValue()
                .increment("coupon-count");
    }

    public void reset() {
        redisTemplate.delete("coupon-count");
    }
}
