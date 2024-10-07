package com.f1v3.coupon.repository;

import com.f1v3.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Coupon JPA Repository.
 *
 * @author 정승조
 * @version 2024. 10. 07.
 */
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
