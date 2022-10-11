package com.dreamland.dreamlandspringapp.dal;

import com.dreamland.dreamlandspringapp.repository.BookEntryRepository;

public interface BookEntryDAO {
    void saveCredit(int bookId, int jid, String user, Float cash, BookEntryRepository bookEntryRepository);

    void saveDebit(int bookId, int jid, String company, Float cash,BookEntryRepository bookEntryRepository);
}
