package com.example.trade.service;

import com.example.trade.persistance.Auction;
import com.example.trade.persistance.User;

import java.util.Optional;

public interface AuctionService
{
    Optional<Auction> findAuctionById(Long auctionId);

    void deleteAuction(Long auctionId);

    Optional<User> findUserById(Long userId);

    void deleteUser(Long userId);

    void deleteUserBids(User user);
}
