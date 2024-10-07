package com.f1v3.coupon.service;

import com.f1v3.coupon.repository.CouponRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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

}