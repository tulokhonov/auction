package com.example.trade.service;

import com.example.trade.persistance.Auction;
import com.example.trade.persistance.User;

public interface AuctionService
{
    Auction findAuctionById(Long auctionId);
    void deleteAuction(Long auctionId);

    User findUserById(Long userId);
}
