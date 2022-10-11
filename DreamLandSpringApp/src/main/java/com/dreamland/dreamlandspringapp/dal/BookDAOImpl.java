package com.dreamland.dreamlandspringapp.dal;


import com.dreamland.dreamlandspringapp.domain.Book;
import com.dreamland.dreamlandspringapp.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAOImpl implements BookDAO{
    int bookIdSequence = 0;
    private static final Logger logger = LoggerFactory.getLogger(BookDAOImpl.class);
    Float OAB;
    @Autowired
    private BookRepository bookRepository ;

    @Override
    public Book saveBookEntry(String username, Float openingCoinBalance, Float openingAmountBalance, BookRepository bookRepository) {
        Book toBeSaved = new Book();
        int id = ++bookIdSequence;
        toBeSaved.setBookId(id);
        toBeSaved.setName(username);
        toBeSaved.setOpeningAmountBalance(openingAmountBalance);
        toBeSaved.setOpeningCoinBalance(openingCoinBalance);
        bookRepository.save(toBeSaved);
        logger.info("BOOK SAVED -> ID {}, NAME {}, OAB {}, CAB {}, OCB {}, CCB {}",
                toBeSaved.getBookId(), toBeSaved.getName(),toBeSaved.getOpeningAmountBalance(),
                toBeSaved.getClosingAmountBalance(),toBeSaved.getOpeningCoinBalance(), toBeSaved.getClosingCoinBalance());
        return toBeSaved;
    }

    @Override
    public void deletBookEntry(Book bookEntity, BookRepository bookRepository) {
     bookRepository.delete(bookEntity);
    }

    @Override
    public Book findBook(String user, BookRepository bookRepository) {
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            System.out.println(book.getName());
            if (book.getName().equalsIgnoreCase(user)) {
                logger.info("BOOK FOUND - {}", book.getName());
                return book;
            }
        }
        logger.info("BOOK NOT FOUND FOR USER - {}", user);

        return null;
    }

    @Override
    public boolean addUserCoins(String user, Float coins, BookRepository bookRepository) {
        Book book = findBook(user, bookRepository);
        logger.info("Initial Closing coin bal - {} for book - {}",book.getClosingCoinBalance(), book.getName() );
        if ((book.getClosingCoinBalance()+coins)<=5F) {
            Float finalCoin = coins + book.getClosingCoinBalance();
            book.setClosingCoinBalance(finalCoin);
            bookRepository.save(book);
            return true;
        }

        logger.info("Final Closing coin bal - {} for book - {}",book.getClosingCoinBalance(), book.getName() );
        return false;
    }

    @Override
    public void removeCompanyCoins(String company, Float coins,BookRepository bookRepository) {
        Book book = findBook(company,bookRepository);
        logger.info("Initial Closing coin bal - {} for book - {}",book.getClosingCoinBalance(), book.getName() );
        book.setClosingCoinBalance(book.getOpeningCoinBalance() - coins);
        book.setOpeningCoinBalance(book.getOpeningCoinBalance() - coins);
        bookRepository.save(book);
        logger.info("Initial Closing coin bal - {} for book - {}",book.getClosingCoinBalance(), book.getName() );
    }

    @Override
    public boolean addUserCash(String user, Float cash, BookRepository bookRepository) {
        Book book = findBook(user, bookRepository);
        logger.info("Initial Closing cash bal - {} for book - {}",book.getClosingAmountBalance(), book.getName() );
        if ((book.getClosingCoinBalance()+cash)<=75F) {
            Float finalCash = book.getClosingAmountBalance() + cash;
            book.setClosingAmountBalance(finalCash);
            bookRepository.save(book);
            return true;
        }
        logger.info("Final Closing cash bal - {} for book - {}",book.getClosingAmountBalance(), book.getName() );
        return false;
    }

    @Override
    public void removeCompanyCash(String company, Float cash, BookRepository bookRepository) {
        Book book = findBook(company,bookRepository);
        logger.info("Initial Closing cash bal - {} for book - {}",book.getClosingCoinBalance(), book.getName() );
        book.setClosingAmountBalance(book.getOpeningAmountBalance() - cash);
        book.setOpeningAmountBalance(book.getOpeningAmountBalance()- cash);
        bookRepository.save(book);
        logger.info("Initial Closing cash bal - {} for book - {}",book.getClosingCoinBalance(), book.getName() );
    }
}
