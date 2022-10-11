package com.dreamland.dreamlandspringapp.service;


import com.dreamland.dreamlandspringapp.repository.BookEntryRepository;

public interface BookEntryService {

    void saveCreditEntry(int bookid, int jid, String user, Float cash, BookEntryRepository bookEntryRepository);

    void saveDebitEntry(int bookid, int jid, String company, Float cash,BookEntryRepository bookEntryRepository);
}
