package com.muhammet.rabbitmq.consumer;

import com.muhammet.rabbitmq.model.CreateUser;
import com.muhammet.repository.entity.UserProfile;
import com.muhammet.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {
    private final UserProfileService userProfileService;
    /**
     * Dinlemek istediğimiz kuyturuğu Handle etmemiz gerekiyor(Listener Kullanılacak).
     * bu sayede. kuyrukta base64 formatında olan metin okunarak Deserialize edilir ve
     * CreateUser nesnesine dönüşür.
     * @param createUser
     */
    @RabbitListener(queues = "queue-auth-create-user")
    public void createUserFromHandleQueue(CreateUser createUser){
        userProfileService.save(UserProfile.builder()
                        .email(createUser.getEmail())
                        .username(createUser.getUsername())
                        .authid(createUser.getAuthid())
                .build());
    }
}
