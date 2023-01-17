package com.muhammet.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    /**
     * Oluşturacağımız kuyruk sistemi için temel olan parametrelerin isimlendirmesini yapıyoruz.
     */
    private String exchangeAuth = "exchange-auth";
    private String keyAuth = "key-auth";
    private String queueAuth = "queue-auth-create-user";

    /**
     * oluşturlacak kuyruğuk için gerekli olan 2 Nesnemizin oluşması için methodları yazıyoruz.
     * 1- Exchange Nesnesi -> Direct, Fanout, Topic
     * 2- Queue Nesnesi -> kuyruk
     * DİKKAT!!! yazdğınız methodlardan Spring in nesne oluşturması için @Bean anotasyonunu eklemelisiniz.
     * @return
     */
    @Bean
    DirectExchange exchangeAuth(){  return new DirectExchange(exchangeAuth);   }
    @Bean
    Queue queueCreateUser(){    return new Queue(queueAuth);   }

    /**
     * Oluşturduğunuz iki nesneyi(Exchange, Queue) bir birine belli bir key üzerinden
     * bağlamak için Binding nesnesi yaratıyoruz. bu işlem rabbitMQ üzerinde bir kuyruk oluşturacaktır.
     * @return
     */
    @Bean
    public Binding bindingCreateUser(final Queue queueCreateUser, final DirectExchange exchangeAuth){
        return BindingBuilder.bind(queueCreateUser).to(exchangeAuth).with(keyAuth);
    }
}
