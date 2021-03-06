package com.example.trade.service;

import com.example.trade.persistance.Auction;
import com.example.trade.persistance.Bid;
import com.example.trade.persistance.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AuctionServiceImpl implements AuctionService
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Auction> findAuctionById(Long auctionId) {
        return Optional.ofNullable(entityManager.find(Auction.class, auctionId));
    }

    @Override
    @Transactional
    public void deleteAuction(Long auctionId) {
        Auction auction = entityManager.find(Auction.class, auctionId);
        entityManager.remove(auction);
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return Optional.ofNullable(entityManager.find(User.class, userId));
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        User user = entityManager.find(User.class, userId);
        entityManager.remove(user);
    }

    @Override
    @Transactional
    public void deleteUserBids(User user) {
        // Удаляем ставки
        entityManager.createQuery("select b from Bid b where b.user = :user", Bid.class)
                .setParameter("user", user)
                .getResultList()
                .forEach(b -> entityManager.remove(b));
    }
}
