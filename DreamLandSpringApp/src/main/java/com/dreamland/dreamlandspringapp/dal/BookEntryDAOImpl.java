package com.dreamland.dreamlandspringapp.dal;


import com.dreamland.dreamlandspringapp.domain.BookEntry;
import com.dreamland.dreamlandspringapp.repository.BookEntryRepository;

import java.util.Date;

public class BookEntryDAOImpl implements BookEntryDAO{

    int bookEntryIdSequence = 0;

    @Override
    public void saveCredit(int bookId, int jid, String user, Float cash, BookEntryRepository bookEntryRepository) {
        BookEntry bookEntry = new BookEntry();
        int id = ++bookEntryIdSequence;
        bookEntry.setBookEntryId(id);
        bookEntry.setBookId(bookId);
        bookEntry.setCredit(cash);
        bookEntry.setJournalId(jid);
        bookEntry.setDebit(0F);
        bookEntryRepository.save(bookEntry);
    }

    @Override
    public void saveDebit(int bookId, int jid, String company, Float cash,BookEntryRepository bookEntryRepository) {
        BookEntry bookEntry = new BookEntry();
        int id = ++bookEntryIdSequence;
        bookEntry.setBookEntryId(id);
        bookEntry.setBookId(bookId);
        bookEntry.setCredit(0F);
        bookEntry.setDebit(cash);
        bookEntry.setJournalId(jid);
        bookEntryRepository.save(bookEntry);
    }
}
