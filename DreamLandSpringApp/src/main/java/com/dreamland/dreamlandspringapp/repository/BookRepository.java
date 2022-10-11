package com.dreamland.dreamlandspringapp.repository;

import com.dreamland.dreamlandspringapp.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
