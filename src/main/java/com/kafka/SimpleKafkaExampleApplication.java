package com.kafka;

import com.dto.UserDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@SpringBootApplication
public class SimpleKafkaExampleApplication {

    @KafkaListener(topics="msg")
    public void msgListener(ConsumerRecord<Long, UserDto> msg) { // Consumer
        System.out.println("==============Consumer=====================");
        System.out.println("partition: "+ msg.partition());
        System.out.println("headers: " + msg.headers());
        System.out.println("key: " + msg.key());
        System.out.println("value: " + msg.value());
        System.out.println("topic: " + msg.topic());
        System.out.println("time: " + msg.timestampType());
        System.out.println("==========================================");
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleKafkaExampleApplication.class, args);
    }
}