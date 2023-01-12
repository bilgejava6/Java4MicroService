package com.muhammet.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(indexName = "userprofile")
public class UserProfile {
    /**
     * Elasticsearch bir db gibi tavranacağı için tuttuğu datalara id vermesi olasıdır.
     * ayrıca indexleme gibi işlemler içinde gereklidir.
     * Bu nedenle isteğe bağlı olarak öncellek aldığınız db nin id bilgisini kayıt edebileceğiniz gibi
     * bu işlemi elasticsearch te yapabilir.
     */
    @Id
    String id;
    String userid;
    Long authid;
    String username;
    String profileimage;
}
