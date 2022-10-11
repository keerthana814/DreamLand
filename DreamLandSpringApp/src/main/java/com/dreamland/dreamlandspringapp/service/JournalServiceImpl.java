package com.dreamland.dreamlandspringapp.service;

import com.dreamland.dreamlandspringapp.dal.JournalDAOImpl;
import com.dreamland.dreamlandspringapp.domain.Journal;
import com.dreamland.dreamlandspringapp.repository.JournalRepository;

public class JournalServiceImpl implements JournalService {
    com.dreamland.dreamlandspringapp.dal.JournalDAO JournalDAO = new JournalDAOImpl();

    @Override
    public Journal makeAmountEntry(String bookName1, String bookName2, Float amount,JournalRepository journalRepository) {
        return JournalDAO.saveJournalEntryforAmountTransaction(bookName1, bookName2, amount,journalRepository);
    }

    @Override
    public Journal makeCoinEntry(String bookName1, String bookName2, Float coin,JournalRepository journalRepository) {
       return JournalDAO.saveJournalEntryforCoinTransaction(bookName1, bookName2, coin,journalRepository);
    }

}
