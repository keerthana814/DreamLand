package com.dreamland.dreamlandspringapp.service;


import com.dreamland.dreamlandspringapp.dal.BookEntryDAO;
import com.dreamland.dreamlandspringapp.dal.BookEntryDAOImpl;
import com.dreamland.dreamlandspringapp.repository.BookEntryRepository;

public class BookEntryServiceImpl implements BookEntryService {

    BookEntryDAO bookDAO = new BookEntryDAOImpl();

    @Override
    public void saveCreditEntry(int bookId, int jid, String user, Float cash, BookEntryRepository bookEntryRepository) {
        bookDAO.saveCredit(bookId, jid, user, cash, bookEntryRepository);
    }

    @Override
    public void saveDebitEntry(int bookId, int jid, String company, Float cash,BookEntryRepository bookEntryRepository) {
        bookDAO.saveDebit(bookId, jid, company, cash,bookEntryRepository);
    }
}
