package com.f1v3.coupon.service;

import com.f1v3.coupon.domain.Coupon;
import com.f1v3.coupon.repository.CouponCountRepository;
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
    private final CouponCountRepository couponCountRepository;


    /**
     * 쿠폰 발급 로직
     */
    public void apply(Long userId) {

        Long increment = couponCountRepository.increment();

        if (increment > 100) {
            // todo: 쿠폰 발급 불가 예외처리
            return;
        }

        Coupon coupon = new Coupon(userId);
        couponRepository.save(coupon);
    }
}
