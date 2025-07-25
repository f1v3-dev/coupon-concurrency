package com.f1v3.coupon.repository;

import com.f1v3.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Coupon Entity Repository
 *
 * @author Seungjo, Jeong
 */
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
