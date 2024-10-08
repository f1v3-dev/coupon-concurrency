package com.f1v3.coupon.service;

import com.f1v3.coupon.domain.Coupon;
import com.f1v3.coupon.repository.CouponRepository;
import com.f1v3.coupon.repository.RedisCouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Redis Coupon Service.
 *
 * @author 정승조
 * @version 2024. 10. 07.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisCouponRepository redisRepository;
    private final CouponRepository couponRepository;

    public void apply(Long userId) {

        // 싱글 스레드 기반의 레디스를 활용한 방법
        Long count = redisRepository.increment();

        if (count > 100L) {
            return;
        }

        Coupon coupon = new Coupon(userId);
        couponRepository.save(coupon);
    }
}
