package com.muhammet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterResponseDto {
    Long authid;
    String content;
    String email;
    /**
     * 100- kayıt başarılı
     * 200- kayıt bekelemede
     * 300- hata
     * 400- parametre hataları
     */
    Integer registerstate;

}
