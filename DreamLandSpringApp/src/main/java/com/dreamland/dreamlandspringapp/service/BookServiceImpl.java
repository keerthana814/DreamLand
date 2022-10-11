package com.dreamland.dreamlandspringapp.service;


import com.dreamland.dreamlandspringapp.dal.BookDAO;
import com.dreamland.dreamlandspringapp.dal.BookDAOImpl;
import com.dreamland.dreamlandspringapp.domain.Book;
import com.dreamland.dreamlandspringapp.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{

    BookDAO bookDAO = new BookDAOImpl();

    @Override
    public void removeBook(Book book, BookRepository bookRepository) {
        bookDAO.deletBookEntry(book,bookRepository);
    }

    @Override
    public Book addNewBook(String username, Float openingCoinBalance, Float openingAmountBalance, BookRepository bookRepository) {
        return bookDAO.saveBookEntry(username, openingCoinBalance, openingAmountBalance, bookRepository);
    }

    @Override
    public Book findBook(String user, BookRepository bookRepository) {
        return bookDAO.findBook(user, bookRepository);
    }

    @Override
    public boolean addUserCoinsInUserAccount(String user, Float coins, BookRepository bookRepository) {
        return bookDAO.addUserCoins(user, coins, bookRepository);
    }

    @Override
    public void removeCoinsInCompanyAccount(String company, Float coins, BookRepository bookRepository) {
        bookDAO.removeCompanyCoins(company, coins,bookRepository);
    }

    @Override
    public boolean addUserCashInUserAccount(String user, Float cash, BookRepository bookRepository) {
        return bookDAO.addUserCash(user, cash,bookRepository);
    }

    @Override
    public void removeCashInCompanyAccount(String company, Float cash, BookRepository bookRepository) {
        bookDAO.removeCompanyCash(company, cash,bookRepository);
    }
}
