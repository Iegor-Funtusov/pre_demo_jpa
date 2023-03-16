package ua.com.alevel.service;

import ua.com.alevel.entity.BaseEntity;

import java.util.Collection;

public interface BaseService<E extends BaseEntity> {

    void create(E entity);
    void update(Long id, E entity);
    void delete(Long id);
    E findById(Long id);
    Collection<E> findAll();
}
