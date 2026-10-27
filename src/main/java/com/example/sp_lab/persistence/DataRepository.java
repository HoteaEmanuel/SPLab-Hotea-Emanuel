package com.example.sp_lab.persistence;

import java.util.List;
import java.util.Optional;


public interface DataRepository<T, ID> {
    List<T> findAll();

    Optional<T> findById(ID id);

    T save(T entity);

    boolean existsById(ID id);

    void deleteById(ID id);
}