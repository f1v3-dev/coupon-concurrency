package com.f1v3.coupon.controller;

import com.f1v3.coupon.dto.UserRequest;
import com.f1v3.coupon.repository.CouponCountRepository;
import com.f1v3.coupon.repository.CouponRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CouponControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CouponCountRepository couponCountRepository;

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    ObjectMapper objectMapper;

    @AfterEach
    void tearDown() {
        couponCountRepository.deleteByKey("coupon:count");
        couponCountRepository.deleteByKey("applied:user");
    }


    @Test
    void applyCoupon() throws Exception {

        UserRequest request = new UserRequest(1L);

        mockMvc.perform(post("/coupon/apply")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        assertNotNull(couponRepository.findById(request.userId()));
    }

}