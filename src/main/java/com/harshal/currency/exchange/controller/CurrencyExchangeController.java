package com.harshal.currency.exchange.controller;

import com.harshal.currency.exchange.model.ExchangeRate;
import com.harshal.currency.exchange.service.RatesRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@RestController
public class CurrencyExchangeController {

    @Autowired
    RatesRestService ratesRestService;

    @GetMapping("/latest")
    public ExchangeRate LatestExchangeRate(){
        HashSet<String> currencies = new HashSet<>();
        currencies.add("USD");
        currencies.add("GBP");
        currencies.add("HKD");
        return ratesRestService.latestRate("EUR", currencies);
    }

    @GetMapping("/history")
    public List<ExchangeRate> HistoricalExchangeRates(){
        HashSet<String> currencies = new HashSet<>();
        currencies.add("USD");
        currencies.add("GBP");
        currencies.add("HKD");

        LocalDate date = LocalDate.now();
        return ratesRestService.getRatesOnDate("EUR", date, currencies);
    }
}
