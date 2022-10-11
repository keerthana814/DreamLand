package com.dreamland.dreamlandspringapp.repository;

import com.dreamland.dreamlandspringapp.domain.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface JournalRepository  extends JpaRepository<Journal, Integer> {
    @Query("select a from Journal a where a.recordDate <= :creationDateTime")
    List<Journal> findAllWithCreationDateTime(
            @Param("creationDateTime") Date creationDateTime);
}
