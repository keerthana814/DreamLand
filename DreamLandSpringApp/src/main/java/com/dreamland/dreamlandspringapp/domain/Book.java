package com.dreamland.dreamlandspringapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="book")
public class Book {

    @Id

    Integer bookId;
    @Column(name = "bookName")
    String name;

    @Column(name = "openingCoinBalance")
    Float openingCoinBalance = 0F;

    @Column(name = "openingAmountBalance")
    Float openingAmountBalance = 0F;

    @Column(name = "closingCoinBalance")
    Float closingCoinBalance = 0F;

    @Column(name = "closingAmountBalance")
    Float closingAmountBalance = 0F;

    @Column(name = "Owner")
    String Owner;

    public Book(int bookId, String name, Float openingCoinBalance, Float openingAmountBalance, String owner) {
        this.bookId = bookId;
        this.name = name;
        this.openingAmountBalance = openingAmountBalance;
        this.openingCoinBalance = openingCoinBalance;
        Owner = owner;
    }
}
