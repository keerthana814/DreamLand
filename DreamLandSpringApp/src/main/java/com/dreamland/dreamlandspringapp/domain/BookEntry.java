package com.dreamland.dreamlandspringapp.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class BookEntry {
    @Id
    int bookEntryId;
    int journalId;
    int bookId;
    Float debit;
    Float credit;

}
