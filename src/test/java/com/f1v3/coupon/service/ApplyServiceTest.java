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

/**
 * {class name}.
 *
 * @author 정승조
 * @version 2024. 10. 07.
 */
@SpringBootTest
class ApplyServiceTest {

    @Autowired
    ApplyService applyService;

    @Autowired
    CouponRepository couponRepository;

    @Test
    @DisplayName("쿠폰 한 번만 발급하는 테스트")
    void test1() {
        applyService.apply(1L);

        long count = couponRepository.count();
        assertEquals(1, count);
    }

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
                    applyService.apply(userId);
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