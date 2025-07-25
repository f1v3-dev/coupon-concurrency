package com.f1v3.coupon.controller;

import com.f1v3.coupon.dto.UserRequest;
import com.f1v3.coupon.service.ApplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Coupon Apply Controller
 *
 * @author Seungjo, Jeong
 */
@Slf4j
@RestController
@RequestMapping("/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final ApplyService applyService;

    @PostMapping("/apply")
    public void applyCoupon(@RequestBody UserRequest request) {
        log.info("apply coupon request by userId = {}", request.userId());

        applyService.apply(request.userId());
    }
}
