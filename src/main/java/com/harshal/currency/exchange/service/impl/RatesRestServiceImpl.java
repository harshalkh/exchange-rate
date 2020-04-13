package com.harshal.currency.exchange.service.impl;

import com.harshal.currency.exchange.model.ExchangeRate;
import com.harshal.currency.exchange.service.RatesRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@Service
public class RatesRestServiceImpl implements RatesRestService {

    @Autowired
    RestTemplate restTemplate;

    private final String RATES_API_URL = "https://api.ratesapi.io/api/";

    private HttpHeaders httpHeaders;

    @Override
    public ExchangeRate latestRate(String base, Set<String> currencies) {

        String symbols = String.join(",", currencies);
        StringBuilder url = new StringBuilder();
        url.append(RATES_API_URL);
        url.append("latest?base=").append(base);
        url.append("&symbols=").append(symbols);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", getHttpHeaders());
        ResponseEntity<ExchangeRate> response = restTemplate.exchange(url.toString(), HttpMethod.GET, entity, ExchangeRate.class);
        return response.getBody();
    }

    @Override
    public List<ExchangeRate> getRatesOnDate(String base, LocalDate date, Set<String> currencies) {
        HttpEntity<String> entity = new HttpEntity<String>("parameters", getHttpHeaders());
        List<ExchangeRate> exchangeRates = new ArrayList<ExchangeRate>();
        String symbols = String.join(",", currencies);
        StringBuilder url = new StringBuilder();
        url.append(RATES_API_URL);
        url.append("%s?base=").append(base);
        url.append("&symbols=").append(symbols);
        ResponseEntity<ExchangeRate> response = null;
        for(int i=0; i<6; i++) {
            String finalURL = String.format(url.toString(), date.minusMonths(i).toString());
            response = restTemplate.exchange(finalURL.toString(), HttpMethod.GET, entity, ExchangeRate.class);
            exchangeRates.add(response.getBody());
        }
        return exchangeRates;
    }

    private HttpHeaders getHttpHeaders(){
        if(this.httpHeaders == null){
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            this.httpHeaders = headers;
        }
        return this.httpHeaders;
    }
}
