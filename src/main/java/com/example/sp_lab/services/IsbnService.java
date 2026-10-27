package com.example.sp_lab.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

// Fake external ISBN provider with no committed SLA: every call takes a random 2-5 seconds
@Service
public class IsbnService {
    private static final Logger log = LoggerFactory.getLogger(IsbnService.class);
    private static final int MIN_DELAY_MS = 2000;
    private static final int MAX_DELAY_MS = 5000;

    public String generateIsbn() {
        int delay = ThreadLocalRandom.current().nextInt(MIN_DELAY_MS, MAX_DELAY_MS + 1);
        log.info("Requesting ISBN from external service (will take {} ms)", delay);

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for the ISBN service", e);
        }

        String isbn = randomIsbn13();
        log.info("Received ISBN {}", isbn);
        return isbn;
    }

    // 978 prefix + 9 random digits + ISBN-13 check digit
    private static String randomIsbn13() {
        StringBuilder digits = new StringBuilder("978");
        for (int i = 0; i < 9; i++)
            digits.append(ThreadLocalRandom.current().nextInt(10));

        int sum = 0;
        for (int i = 0; i < digits.length(); i++)
            sum += (digits.charAt(i) - '0') * (i % 2 == 0 ? 1 : 3);

        return digits.append((10 - sum % 10) % 10).toString();
    }
}