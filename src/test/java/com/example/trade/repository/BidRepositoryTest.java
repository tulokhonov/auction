package com.example.trade.repository;

import com.example.trade.persistance.Bid;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BidRepositoryTest {
    @Autowired
    private BidRepository bidRepository;

    @Test
    @Transactional
    void test1() {
        List<Bid> bids = bidRepository.getBids(1L);
        bids.forEach(b -> log.info(b.getValue().toString()));
    }
}