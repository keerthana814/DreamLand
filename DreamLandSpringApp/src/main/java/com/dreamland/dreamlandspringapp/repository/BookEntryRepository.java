package com.dreamland.dreamlandspringapp.repository;

import com.dreamland.dreamlandspringapp.domain.BookEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BookEntryRepository extends JpaRepository<BookEntry, Integer> {


}
