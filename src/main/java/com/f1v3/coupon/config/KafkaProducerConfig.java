package com.f1v3.coupon.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 카프카 프로듀서 설정 클래스
 *
 * @author Seungjo, Jeong
 */
@Configuration
public class KafkaProducerConfig {

    /**
     * 카프카 프로듀서 팩토리 빈 설정
     * - Kafka 서버 주소: localhost:9092
     * - Key 직렬화: StringSerializer
     * - Value 직렬화: LongSerializer
     */
    @Bean
    public ProducerFactory<String, Long> producerFactory() {

        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, LongSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    /**
     * 카프카 템플릿 빈 설정
     * - 프로듀서는 해당 카프카 템플릿을 사용해서 토픽에 메시지를 발행
     */
    @Bean
    public KafkaTemplate<String, Long> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
