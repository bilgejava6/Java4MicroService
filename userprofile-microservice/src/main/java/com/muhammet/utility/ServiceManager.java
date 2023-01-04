package com.muhammet.utility;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @param <T> -> Bu sınıfı miras almak isteyen bir servis hangi entity e
 *           hizmet ediyor ise onun Type olarak verecek
 * @param <ID> -> Bu sınıfı miras alan servisin  kullanmakta olduğu id tipini
 *            belirmesi gerekir, Long,String, Integer
 */
public class ServiceManager<T extends BaseEntity, ID> implements IService<T,ID> {
    /**
     * Tüm Servisler için ortak bir kullanım sunacak ise, burada repository
     * üzerinden işlem yapabiliryor olamalı.
     * @param t
     * @return
     */
    private final JpaRepository<T,ID> repository;
    public ServiceManager(JpaRepository<T,ID> repository){
        this.repository = repository;
    }
    @Override
    public T save(T t) {
        t.setCreatedate(System.currentTimeMillis());
        t.setUpdatedate(System.currentTimeMillis());
        t.setIsactive(true);
        return repository.save(t);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> t) {
        t.forEach(p->{
            p.setIsactive(true);
            p.setUpdatedate(System.currentTimeMillis());
            p.setCreatedate(System.currentTimeMillis());
        });
        return repository.saveAll(t);
    }

    @Override
    public T update(T t) {
        t.setUpdatedate(System.currentTimeMillis());
        return repository.save(t);
    }

    @Override
    public void delete(T t) {
        t.setIsactive(false);
        repository.save(t);
    }

    @Override
    public void deleteById(ID id) {
        Optional<T> t = repository.findById(id);
        if(t.isPresent()) {
            t.get().setIsactive(false);
            repository.save(t.get());
        }
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }
}
