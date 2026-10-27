package com.example.sp_lab.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Adapter: makes any Spring Data JPA repository usable through our DataRepository interface
public class JpaRepositoryAdapter<T, ID> implements DataRepository<T, ID> {
    private final JpaRepository<T, ID> jpaRepository;

    public JpaRepositoryAdapter(JpaRepository<T, ID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public T save(T entity) {
        return jpaRepository.save(entity);
    }

    @Override
    public boolean existsById(ID id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public void deleteById(ID id) {
        jpaRepository.deleteById(id);
    }
}