package com.f1v3.coupon.controller;

import com.f1v3.coupon.domain.Coupon;
import com.f1v3.coupon.domain.UserRequest;
import com.f1v3.coupon.repository.CouponRepository;
import com.f1v3.coupon.repository.RedisCouponRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * {class name}.
 *
 * @author 정승조
 * @version 2024. 10. 08.
 */
@SpringBootTest
@AutoConfigureMockMvc
class ApplyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    RedisCouponRepository redisCouponRepository;

    @Autowired
    ObjectMapper objectMapper;

    @AfterEach
    void after() {
        redisCouponRepository.reset();
    }

    @Test
    void test1() throws Exception {
        UserRequest request = new UserRequest(1L);

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/coupon/apply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        Optional<Coupon> actual = couponRepository.findByUserId(1L);
        assertNotNull(actual);
    }
}