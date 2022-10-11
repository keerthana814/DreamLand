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
public class Journal {
    @Id
    Integer journalId;
    String fromAccount;
    String toAccount;
    Float amount;
    Float coin;
    @Temporal(TemporalType.DATE)
    Date recordDate;
}
