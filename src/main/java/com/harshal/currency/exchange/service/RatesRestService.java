package com.harshal.currency.exchange.service;

import com.harshal.currency.exchange.model.ExchangeRate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public interface RatesRestService {
    public ExchangeRate latestRate(String base, Set<String> currencies);

    public List<ExchangeRate> getRatesOnDate(String base, LocalDate date, Set<String> currencies);
}
