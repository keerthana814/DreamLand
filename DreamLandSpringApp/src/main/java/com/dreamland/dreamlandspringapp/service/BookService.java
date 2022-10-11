package com.dreamland.dreamlandspringapp.service;


import com.dreamland.dreamlandspringapp.domain.Book;
import com.dreamland.dreamlandspringapp.repository.BookRepository;

public interface BookService {
    public void removeBook(Book book, BookRepository bookRepository);
    public Book addNewBook(String username, Float openingCoinBalance, Float openingAmountBalance, BookRepository bookRepository);

    Book findBook(String user, BookRepository bookRepository);

    boolean addUserCoinsInUserAccount(String user, Float coins, BookRepository bookRepository);

    void removeCoinsInCompanyAccount(String company, Float coins, BookRepository bookRepository);

    boolean addUserCashInUserAccount(String user, Float cash, BookRepository bookRepository);

    void removeCashInCompanyAccount(String company, Float cash, BookRepository bookRepository);
}
