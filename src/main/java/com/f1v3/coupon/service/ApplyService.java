package com.f1v3.coupon.service;

import com.f1v3.coupon.domain.Coupon;
import com.f1v3.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Coupon Apply Service.
 *
 * @author 정승조
 * @version 2024. 10. 07.
 */
@Service
@RequiredArgsConstructor
public class ApplyService {

    private final CouponRepository couponRepository;

    /**
     * 쿠폰 발급 비즈니스 로직
     *
     * @param userId 사용자 ID
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
