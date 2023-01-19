package com.muhammet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BaseRequestDto {
    @NotBlank
    @Size(min = 35)
    String token;
    /**
     * Geçerli Sayfa numarası
     */
    Integer currentPage;
    /**
     * Bir sayfada gösterilecek kayıt sayısı
     */
    Integer pageSize;
    /**
     * Sıralama yapılacak sutunadı
     */
    String sortParameter;
    /**
     * sıralamanın yönü, ASC,DESC
     */
    String direction;
}
