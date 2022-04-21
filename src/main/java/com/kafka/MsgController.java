package com.kafka;

import com.dto.Address;
import com.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("msg")
public class MsgController {

    @Autowired
    private KafkaTemplate<Long, UserDto> kafkaTemplate;

    @PostMapping
    public void sendMsg(Long msgId, UserDto user) { // Producer
        setUser(user);
        ListenableFuture<SendResult<Long, UserDto>> future = kafkaTemplate.send("msg", msgId, user);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }

    private void setUser(UserDto user) {
        user.setName("name");
        user.setAge(36L);
        user.setAddress(new Address("country1", "city1", "street1", 1L, 2L));

    }
}