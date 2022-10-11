package com.dreamland.dreamlandspringapp.dal;


import com.dreamland.dreamlandspringapp.domain.Journal;
import com.dreamland.dreamlandspringapp.repository.JournalRepository;

public interface JournalDAO {

    public Journal saveJournalEntryforCoinTransaction(String bookName1, String bookName2, Float coin,JournalRepository journalRepository);

    public Journal saveJournalEntryforAmountTransaction(String bookName1, String bookName2, Float amount, JournalRepository journalRepository);

}
