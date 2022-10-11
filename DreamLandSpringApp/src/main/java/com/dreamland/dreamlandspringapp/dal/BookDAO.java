package com.dreamland.dreamlandspringapp.dal;


import com.dreamland.dreamlandspringapp.domain.Book;
import com.dreamland.dreamlandspringapp.repository.BookRepository;

public interface BookDAO {
    Book saveBookEntry(String username, Float openingCoinBalance, Float openingAmountBalance, BookRepository bookRepository);

    void deletBookEntry(Book book,BookRepository bookRepository);

    Book findBook(String user, BookRepository bookRepository);

    boolean addUserCoins(String user, Float coins, BookRepository bookRepository);

    void removeCompanyCoins(String company, Float coins, BookRepository bookRepository);

    boolean addUserCash(String user, Float cash, BookRepository bookRepository);

    void removeCompanyCash(String company, Float cash, BookRepository bookRepository);
}
