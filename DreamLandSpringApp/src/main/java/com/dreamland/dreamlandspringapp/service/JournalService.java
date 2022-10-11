package com.dreamland.dreamlandspringapp.service;

import com.dreamland.dreamlandspringapp.domain.Journal;
import com.dreamland.dreamlandspringapp.repository.JournalRepository;

public interface JournalService {
    Journal makeAmountEntry(String bookName1, String bookName2, Float amount,JournalRepository journalRepository);
    Journal makeCoinEntry(String bookName1, String bookName2, Float coin,JournalRepository journalRepository);

}
