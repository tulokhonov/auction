package com.example.trade.config;

import com.example.trade.model.Auction;
import com.example.trade.model.Bid;
import com.example.trade.repository.AuctionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class Config implements InitializingBean
{
    private final AuctionRepository auctionRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        Auction auction = new Auction();
        auction.setBids(List.of(new Bid(null, LocalDateTime.now(), new BigDecimal("0.00"))));
        auctionRepository.save(auction);
    }
}
