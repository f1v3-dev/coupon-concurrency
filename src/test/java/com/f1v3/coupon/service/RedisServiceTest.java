package com.f1v3.coupon.service;

import com.f1v3.coupon.repository.CouponRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RedisServiceTest {

    @Autowired
    RedisService redisService;

    @Autowired
    CouponRepository couponRepository;

    @Test
    @DisplayName("여러명이 동시에 쿠폰 발급 요청")
    void test2() throws InterruptedException {

        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            long userId = i;
            executorService.submit(() -> {
                try {
                    redisService.apply(userId);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();


        long count = couponRepository.count();
        assertEquals(100, count);
    }
}