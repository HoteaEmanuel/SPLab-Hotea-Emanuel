package com.example.sp_lab.persistence;

import com.example.sp_lab.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Exposes the Spring Data repositories to the upper layers only through the DataRepository abstraction
@Configuration
public class PersistenceConfig {

    @Bean
    public DataRepository<Book, Integer> booksDataRepository(BookRepository bookRepository) {
        return new JpaRepositoryAdapter<>(bookRepository);
    }
}