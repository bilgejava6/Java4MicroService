package com.muhammet.utility;

import java.util.List;
import java.util.Optional;

/**
 * Servis Sözleşmesi, burada kalıp olarak oluşturulan methodlar
 * tüm servislerde aynıyla kullanılmak zorundadır.
 * SORUNLAR;
 * 1- Eğer buradaki methodlar tüm servisler tarafından kullanılacak ise, katdetme işleminde hangi
 * entity adı yazılmalı???
 * Çözüm: Type olarak belirsiz durumlarda, Object gibi katı türleri kullanmak yerine esnek bir
 * şablon çıkartmak doğru olacaktır. bunu yapabimek için, GenericType ile çözülür
 * 2- Peki, id her zaman long olamaz, String olabilir , Integer olabilir o zaman id için Long
 * kullanmak zorun yaratacaktır.
 *
 */
public interface IService<T, ID> {
    T save(T t);
    Iterable<T> saveAll(Iterable<T> t);
    T update(T t);
    void delete(T t);
    void deleteById(ID id);
    List<T> findAll();
    Optional<T> findById(ID id);
}
