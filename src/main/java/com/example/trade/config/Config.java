package com.example.trade.config;

import com.example.trade.model.Auction;
import com.example.trade.model.Bid;
import com.example.trade.model.User;
import com.example.trade.repository.AuctionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class Config implements InitializingBean
{
    private final AuctionRepository auctionRepository;

    public Config(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        User user = new User();
        List<Bid> bids = List.of(
                new Bid(null, LocalDateTime.now(), new BigDecimal("0.00"), user),
                new Bid(null, LocalDateTime.now(), new BigDecimal("0.00"), user)
        );

        Auction auction = new Auction();
        auction.setBids(bids);
        auction.setOrganizer(user);
        auctionRepository.save(auction);
    }
}
