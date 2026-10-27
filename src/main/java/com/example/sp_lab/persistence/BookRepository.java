package com.example.sp_lab.persistence;

import com.example.sp_lab.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository  extends JpaRepository<Book,Integer> {
}
