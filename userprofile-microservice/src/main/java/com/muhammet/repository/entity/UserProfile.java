package com.muhammet.repository.entity;

import com.muhammet.utility.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Table(name = "tbluserprofile")
@Entity
public class UserProfile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    /**
     * Auth Microservice te kayıt olan bir kullanıcının id bilgisini tutar.
     */
    Long authid;
    String username;
    String email;
    String phone;
    String address;
    String profileimage;
    String avatar;
    String info;
    String facebook;
    String twitter;
    String instagram;



}
