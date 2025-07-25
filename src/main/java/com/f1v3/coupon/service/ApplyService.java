package com.f1v3.coupon.service;

import com.f1v3.coupon.domain.Coupon;
import com.f1v3.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Coupon Apply Service
 *
 * @author Seungjo, Jeong
 */
@Service
@RequiredArgsConstructor
public class ApplyService {

    private final CouponRepository couponRepository;

    /**
     * 쿠폰 발급 로직
     * - 쿠폰 개수 조회
     * - 쿠폰 발급 조건 확인 후 쿠폰 발급
     */
    public void apply(Long userId) {
        long count = couponRepository.count();

        if (count > 100) {
            return;
        }

        Coupon coupon = new Coupon(userId);
        couponRepository.save(coupon);
    }
}
