package com.f1v3.coupon.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Coupon Count Repository (Redis)
 *
 * @author Seungjo, Jeong
 */
@Repository
@RequiredArgsConstructor
public class CouponCountRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public Long increment() {
        return redisTemplate.opsForValue()
                .increment("coupon:count", 1);
    }
}
