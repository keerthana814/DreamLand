package com.dreamland.dreamlandspringapp.service;


import com.dreamland.dreamlandspringapp.domain.Book;
import com.dreamland.dreamlandspringapp.repository.BookEntryRepository;
import com.dreamland.dreamlandspringapp.repository.BookRepository;
import com.dreamland.dreamlandspringapp.repository.JournalRepository;

public interface DreamLandService {
    public Book createUserAccount(String username,BookRepository bookRepository);
    public Float retreiveUserCoins(String username, BookRepository bookRepository);
    public Float retreiveUserCash(String username,BookRepository bookRepository);
    public boolean addUserCoins(String company, String user, Float coins, BookRepository repository,
                                JournalRepository journalRepository, BookEntryRepository bookEntryRepository);
    public boolean addUserCash(String company, String user, Float cash,BookRepository bookRepository,
                               JournalRepository journalRepository, BookEntryRepository bookEntryRepository);
    public Book createCompanyAccount(String company, BookRepository bookRepository);
    public Float retreiveCompanyCoins(String company,BookRepository bookRepository);
    public Float retreiveCompanyCash(String company,BookRepository bookRepository);
    public void processCompanyFee(String company,BookRepository bookRepository,
                                  JournalRepository journalRepository,BookEntryRepository bookEntryRepository);
    Float retrieveCoinsWonForTheDay(String user, JournalRepository journalRepository);
}
