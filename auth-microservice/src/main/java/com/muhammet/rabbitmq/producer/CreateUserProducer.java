package com.muhammet.rabbitmq.producer;

import com.muhammet.rabbitmq.model.CreateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserProducer {
    /**
     * RabbitMQ üzerinde bir mesaj göndermek için özel bir template e ihtiyacımız vardır.
     */
    private final RabbitTemplate rabbitTemplate;
    /**
     * Eğer bir mesaj gönderen method yazacaksanız mutlaka void olmalıdır.
     */
    public void converdAndSendMessageCreateUser(CreateUser createUser){
        rabbitTemplate.convertAndSend("exchange-auth","key-auth",createUser);
    }
}
