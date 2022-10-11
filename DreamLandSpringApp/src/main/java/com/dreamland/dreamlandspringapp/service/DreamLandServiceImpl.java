package com.dreamland.dreamlandspringapp.service;


import com.dreamland.dreamlandspringapp.domain.Book;
import com.dreamland.dreamlandspringapp.domain.BookEntry;
import com.dreamland.dreamlandspringapp.domain.Journal;
import com.dreamland.dreamlandspringapp.repository.BookEntryRepository;
import com.dreamland.dreamlandspringapp.repository.BookRepository;
import com.dreamland.dreamlandspringapp.repository.JournalRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class DreamLandServiceImpl implements DreamLandService {

    // opening balance for the company
    Float openingCoinBalance = 10F;
    Float openingAmountBalance = 150F;
    // opening balance for user is 0
    //  Processing fee beared by the company is 100 (hardcoding it)
    Float processingFee = 1F;

    BookService bookService = new BookServiceImpl();
    JournalService journalService = new JournalServiceImpl();
    BookEntryService bookEntryService = new BookEntryServiceImpl();

    @Override
    public Book createUserAccount(String username, BookRepository bookRepository) {
        return bookService.addNewBook(username, 0F, 0F, bookRepository);
    }

    @Override
    public Float retreiveUserCoins(String user,BookRepository bookRepository) {
        Book userBook = bookService.findBook(user, bookRepository);
        return userBook.getClosingCoinBalance();
    }

    @Override
    public Float retreiveUserCash(String user, BookRepository bookRepository) {
        Book userBook = bookService.findBook(user, bookRepository);
        return userBook.getClosingAmountBalance();
    }

    @Override
    public Book createCompanyAccount(String company, BookRepository bookRepository) {
        bookService.addNewBook(company+"_feeAccount", 0F,0F, bookRepository);
        return bookService.addNewBook(company, openingCoinBalance, openingAmountBalance, bookRepository);
    }

    @Override
    public Float retreiveCompanyCoins(String company,BookRepository bookRepository) {
        Book userBook = bookService.findBook(company, bookRepository);
        return userBook.getClosingCoinBalance();
    }

    @Override
    public Float retreiveCompanyCash(String company, BookRepository bookRepository) {
        Book userBook = bookService.findBook(company,  bookRepository);
        return userBook.getClosingAmountBalance();
    }

    @Override
    public boolean addUserCoins(String company, String user, Float coins, BookRepository bookRepository,
                                JournalRepository journalRepository, BookEntryRepository bookEntryRepository) {
        Book userBook = bookService.findBook(user, bookRepository);
        Book companyBook =bookService.findBook(company, bookRepository) ;
        if (userBook== null){
          userBook = createUserAccount(user, bookRepository );

        }
        if(companyBook==null){
            companyBook = createCompanyAccount(company, bookRepository);
        }
        if(userBook.getClosingCoinBalance()+coins<=5|userBook.getClosingAmountBalance()<=45){

        }
        if (bookService.addUserCoinsInUserAccount(user, coins, bookRepository)) {
            bookService.removeCoinsInCompanyAccount(company, coins,bookRepository);
            Journal je = journalService.makeCoinEntry(companyBook.getName(), userBook.getName(), coins, journalRepository);
            bookEntryService.saveCreditEntry(userBook.getBookId(),je.getJournalId() , user, coins, bookEntryRepository);
            bookEntryService.saveDebitEntry(companyBook.getBookId(),je.getJournalId(), company, coins, bookEntryRepository);
            return true;
        }
        return false;
    }

    @Override
    public boolean addUserCash(String company, String user, Float cash, BookRepository bookRepository,
                               JournalRepository journalRepository, BookEntryRepository bookEntryRepository) {
        Book userBook = bookService.findBook(user, bookRepository);
        Book companyBook = bookService.findBook(company, bookRepository);
        if (bookService.addUserCashInUserAccount(user, cash,bookRepository)) {
            bookService.removeCashInCompanyAccount(company, cash,bookRepository);
            Journal je = journalService.makeAmountEntry(companyBook.getName(), userBook.getName(), cash, journalRepository);
            bookEntryService.saveCreditEntry(userBook.getBookId(),je.getJournalId(), user, cash, bookEntryRepository);
            bookEntryService.saveDebitEntry(companyBook.getBookId(),je.getJournalId(), company, cash,bookEntryRepository);
            return true;
        }
        return false;
    }

    @Override
    public void processCompanyFee(String company, BookRepository bookRepository,
                                  JournalRepository journalRepository,BookEntryRepository bookEntryRepository) {
        Book companyFeeBook = bookService.findBook(company+"_feeAccount", bookRepository);
        Book companyBook = bookService.findBook(company,bookRepository);
        bookService.addUserCashInUserAccount(company+"_feeAccount", processingFee,bookRepository);
        bookService.removeCashInCompanyAccount(company, processingFee,bookRepository);
        Journal je = journalService.makeAmountEntry(companyFeeBook.getName(), companyBook.getName(), processingFee,journalRepository);
        bookEntryService.saveCreditEntry(companyFeeBook.getBookId(),je.getJournalId(),company+"_feeAccount", processingFee,bookEntryRepository);
        bookEntryService.saveDebitEntry(companyBook.getBookId(), je.getJournalId(), company, processingFee,bookEntryRepository);
    }

    @Override
    public Float retrieveCoinsWonForTheDay(String user,  JournalRepository journalRepository) {
        Float coinsWonForTheDay = 0F;
        List<Journal> journalList = journalRepository.findAllWithCreationDateTime(new Date());
        for (Journal journal : journalList) {
            if (journal.getToAccount().equalsIgnoreCase(user)) {
                coinsWonForTheDay = coinsWonForTheDay + journal.getCoin();
            }
        }
        return coinsWonForTheDay;
    }

}
