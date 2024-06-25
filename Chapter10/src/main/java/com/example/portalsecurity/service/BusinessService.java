package com.example.portalsecurity.service;

import java.util.List;

public interface BusinessService<T, ID> {

    List<T> findAll();

    T findById(ID id);

    public T insert(T t);

    T update(Long id, T t);

    void delete(ID id);
}