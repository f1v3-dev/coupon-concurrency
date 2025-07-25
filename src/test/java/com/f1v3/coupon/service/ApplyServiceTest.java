package com.f1v3.coupon.service;

import com.f1v3.coupon.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {class name}.
 *
 * @author Seungjo, Jeong
 */
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
}