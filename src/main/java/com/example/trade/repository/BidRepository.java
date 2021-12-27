package com.example.trade.repository;

import com.example.trade.persistance.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {
    @Query("select b from Bid b where b.auction.id = :auctionId")
    @Lock(LockModeType.PESSIMISTIC_READ)
    List<Bid> getBids(Long auctionId);
}
