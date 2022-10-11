package com.dreamland.dreamlandspringapp.dal;

import com.dreamland.dreamlandspringapp.domain.Journal;
import com.dreamland.dreamlandspringapp.repository.JournalRepository;

import java.util.Date;

public class JournalDAOImpl implements JournalDAO{
    int journalIdSequence = 0;

    @Override
    public Journal saveJournalEntryforCoinTransaction(String bookName1, String bookName2, Float coin,JournalRepository journalRepository) {
        Journal journalEntry = new Journal();
        int id = ++journalIdSequence;
        journalEntry.setJournalId(id);
        journalEntry.setFromAccount(bookName1);
        journalEntry.setToAccount(bookName2);
        journalEntry.setCoin(coin);
        journalEntry.setAmount(0F);
        journalEntry.setRecordDate(new Date());
        journalRepository.save(journalEntry);
        return journalEntry;
    }

    @Override
    public Journal saveJournalEntryforAmountTransaction(String bookName1, String bookName2, Float amount, JournalRepository journalRepository) {
        Journal journalEntry = new Journal();
        int id = ++journalIdSequence;
        journalEntry.setJournalId(id);
        journalEntry.setFromAccount(bookName1);
        journalEntry.setToAccount(bookName2);
        journalEntry.setCoin(0F);
        journalEntry.setAmount(amount);
        journalEntry.setRecordDate(new Date());
        journalRepository.save(journalEntry);
        return journalEntry;
    }
}
