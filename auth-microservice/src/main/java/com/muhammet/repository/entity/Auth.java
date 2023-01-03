package com.muhammet.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "tblauth")
@Entity
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String password;
    String email;
    Long createdate;
    Long updatedate;
    /**
     * Numara yapılarında, int değeri yada String değerini
     * seçmek için kullanırız.
     */
    @Enumerated(EnumType.STRING)
    State state;
}
