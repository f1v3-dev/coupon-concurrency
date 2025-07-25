package com.f1v3.coupon.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * 쿠폰 생성 프로듀서
 *
 * @author Seungjo, Jeong
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CouponCreateProducer {

    private static final String TOPIC_NAME = "coupon-create";

    private final KafkaTemplate<String, Long> kafkaTemplate;

    /**
     * 카프카 템플릿을 사용하여 coupon-create 토픽에 userId 메시지 발행
     */
    public void create(Long userId) {
        kafkaTemplate.send(TOPIC_NAME, userId);
        log.info("메시지 발생 성공, topicName: {}, userId: {}", TOPIC_NAME, userId);
    }
}
