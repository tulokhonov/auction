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
        User organizer = new User();
        organizer.setName("John");

        User user = new User();
        user.setName("Jack");

        User user2 = new User();
        user2.setName("Paul");

        List<Bid> bids = List.of(
                new Bid(null, LocalDateTime.now().minusSeconds(1), new BigDecimal("1.00"), user),
                new Bid(null, LocalDateTime.now(), new BigDecimal("2.00"), user),
                new Bid(null, LocalDateTime.now(), new BigDecimal("2.01"), user2)
        );

        Auction auction = new Auction();
        auction.setName("Auction 1");
        auction.setBids(bids);
        auction.setOrganizer(organizer);

        auction.addUser(user);
        auction.addUser(user2);
        auctionRepository.save(auction);
    }
}