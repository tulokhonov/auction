package com.example.trade;

import com.example.trade.persistance.Auction;
import com.example.trade.persistance.Bid;
import com.example.trade.persistance.User;
import com.example.trade.service.TransactionRunner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.time.Instant;

@SpringBootTest
public class TransactionTests {
    @Autowired
    private TransactionRunner transactionRunner;

    @Test
    void test1() {
        transactionRunner.executeInTransaction(em -> {
                Auction auction = em.find(Auction.class, 1L);
                User user = em.find(User.class, 2L);
                auction.addBid(new Bid(null, Instant.now().minusSeconds(1), new BigDecimal("20.00"), auction, user));
                auction.addBid(new Bid(null, Instant.now(), new BigDecimal("21.00"), auction, user));
        });
    }
}
