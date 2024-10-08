package com.f1v3.coupon.controller;

import com.f1v3.coupon.domain.UserRequest;
import com.f1v3.coupon.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Coupon Apply Controller.
 *
 * @author 정승조
 * @version 2024. 10. 08.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ApplyController {

    private final RedisService redisService;

    @PostMapping("/coupon/apply")
    public void apply(@RequestBody UserRequest request) {

        log.info("request by userId = {}", request.getUserId());
        redisService.apply(request.getUserId());
    }

}
