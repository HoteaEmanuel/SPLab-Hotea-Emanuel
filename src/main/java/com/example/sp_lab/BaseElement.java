package com.example.sp_lab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

// Root of the persisted Element hierarchy: holds the shared id, each subclass gets its own table joined on it
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class BaseElement implements Element {
    @Id
    @GeneratedValue
    private Integer id;

    public Integer getId() {
        return id;
    }
}