package com.example.trade.service;

import com.example.trade.persistance.Auction;
import com.example.trade.persistance.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class AuctionServiceImpl implements AuctionService
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Auction findAuctionById(Long auctionId) {
        return entityManager.find(Auction.class, auctionId);
    }

    @Override
    @Transactional
    public void deleteAuction(Long auctionId) {
        Auction auction = entityManager.find(Auction.class, auctionId);
        entityManager.remove(auction);
    }

    @Override
    public User findUserById(Long userId) {
        return entityManager.find(User.class, userId);
    }
}
