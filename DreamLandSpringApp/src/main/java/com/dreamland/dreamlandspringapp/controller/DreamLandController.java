package com.dreamland.dreamlandspringapp.controller;

import com.dreamland.dreamlandspringapp.domain.Book;
import com.dreamland.dreamlandspringapp.repository.BookEntryRepository;
import com.dreamland.dreamlandspringapp.repository.BookRepository;
import com.dreamland.dreamlandspringapp.repository.JournalRepository;
import com.dreamland.dreamlandspringapp.service.DreamLandService;
import com.dreamland.dreamlandspringapp.service.DreamLandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DreamLandController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookEntryRepository bookEntryRepository;

    @Autowired
    private JournalRepository journalRepository;

    DreamLandService service = new DreamLandServiceImpl();
    @GetMapping
    public List<Book> getUserToken() {
        return bookRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/dreamLand", headers = {
            "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    void processTokensWon(@RequestBody Player details) {
        service.addUserCoins(details.companyName, details.playerName,
                details.tokensWon, bookRepository, journalRepository,bookEntryRepository);
        service.addUserCash(details.companyName, details.playerName,
                (details.tokensWon*15),bookRepository,journalRepository, bookEntryRepository);
        service.processCompanyFee(details.companyName, bookRepository, journalRepository, bookEntryRepository);
    }

    @ResponseBody
    @RequestMapping(value = "/dreamLand/cash/{user}", headers = {
            "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    Float retreiveTotalCashOfUser(@PathVariable("user") String playerName) {

        return service.retreiveUserCash(playerName, bookRepository);
    }

    @ResponseBody
    @RequestMapping(value = "/dreamLand/coins/{user}", headers = {
            "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    Float retreiveTotalCoinsOfUser(@PathVariable("user") String playerName) {
        return service.retreiveUserCoins(playerName, bookRepository);
    }

    @ResponseBody
    @RequestMapping(value = "/dreamLand/coinStats/{user}", headers = {
            "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    Float retreiveCoinStatsOfUser(@PathVariable("user") String playerName) {
        return service.retrieveCoinsWonForTheDay(playerName, journalRepository);
    }
}
