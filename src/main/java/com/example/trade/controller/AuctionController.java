package com.example.trade.controller;

import com.example.trade.dto.BidRequest;
import com.example.trade.dto.BidResponse;
import com.example.trade.error.BidException;
import com.example.trade.model.Auction;
import com.example.trade.model.Bid;
import com.example.trade.model.User;
import com.example.trade.repository.AuctionRepository;
import com.example.trade.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

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
        Auction auction = auctionRepository.findById(request.getAuctionId())
                .orElseThrow(() -> new IllegalArgumentException("Неверный номер аукциона"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Неверный пользователь"));

        BigDecimal max = auction.getMaxBid().orElse(BigDecimal.ZERO);

        if (request.getValue().compareTo(max) > 0)
            auction.makeBid(new Bid(null, LocalDateTime.now(), request.getValue(), user));
        else {
            log.warn("Ставка {} меньше максимальной в аукционе {}", request.getValue(), max);
            throw new BidException("Ставка меньше максимальной");
        }

        return new BidResponse(true, LocalDateTime.now());
    }
}
