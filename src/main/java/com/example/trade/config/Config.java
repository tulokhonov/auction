package com.example.trade.config;

import com.example.trade.persistance.Auction;
import com.example.trade.persistance.Bid;
import com.example.trade.persistance.User;
import com.example.trade.repository.AuctionRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Component
public class Config implements InitializingBean
{
    private final AuctionRepository auctionRepository;

    public Config(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @Override
    public void afterPropertiesSet()
    {
        User organizer = new User();
        organizer.setName("John");

        User user = new User();
        user.setName("Jack");

        User user2 = new User();
        user2.setName("Paul");

        List<Bid> bids = List.of(
                new Bid(null, Instant.now().minusSeconds(2), new BigDecimal("1.00"), null, user),
                new Bid(null, Instant.now().minusSeconds(1), new BigDecimal("2.00"), null, user),
                new Bid(null, Instant.now(), new BigDecimal("2.01"), null, user2)
        );

        Auction auction = new Auction();
        auction.setName("Auction 1");
        auction.setStartPrice(new BigDecimal("0.50"));

        bids.forEach(auction::addBid);

        auction.setOrganizer(organizer);
        auction.addUser(user);
        auction.addUser(user2);

        auctionRepository.save(auction);
    }
}