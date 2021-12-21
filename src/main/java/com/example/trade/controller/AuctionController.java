package com.example.trade.controller;

import com.example.trade.dto.BidRequest;
import com.example.trade.dto.BidResponse;
import com.example.trade.model.Auction;
import com.example.trade.model.Bid;
import com.example.trade.model.User;
import com.example.trade.repository.AuctionRepository;
import com.example.trade.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@Slf4j
public class AuctionController
{
    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;

    @PostMapping("/auction")
    @Transactional
    public BidResponse makeBid(@RequestBody BidRequest request)
    {
        Auction auction = auctionRepository.getById(request.getAuctionId());
        User user = userRepository.getById(request.getUserId());

        auction.makeBid(new Bid(null, LocalDateTime.now(), request.getValue(), user));

        return new BidResponse(true, LocalDateTime.now());
    }
}
