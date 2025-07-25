package com.f1v3.coupon.service;

import com.f1v3.coupon.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApplyServiceTest {

    @Autowired
    ApplyService applyService;

    @Autowired
    CouponRepository couponRepository;

    @Test
    void 쿠폰_1회_발급() {
        applyService.apply(1L);

        long count = couponRepository.count();

        assertEquals(1, count);
    }

    @Test
    void 쿠폰_여러명_발급() throws InterruptedException {
        int threadCount = 1000;

        ExecutorService executorService = Executors.newFixedThreadPool(32);

        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            long userId = i;

            executorService.execute(() -> {
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