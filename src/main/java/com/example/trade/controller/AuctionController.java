package com.example.trade.controller;

import com.example.trade.dto.BidRequest;
import com.example.trade.dto.BidResponse;
import com.example.trade.error.BidException;
import com.example.trade.persistance.Auction;
import com.example.trade.persistance.Bid;
import com.example.trade.persistance.User;
import com.example.trade.repository.AuctionRepository;
import com.example.trade.repository.BidRepository;
import com.example.trade.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/auction")
public class AuctionController
{
    private final AuctionRepository auctionRepository;
    private final UserRepository userRepository;
    private final BidRepository bidRepository;

    @PostMapping("")
    @Transactional
    public BidResponse makeBid(@RequestBody BidRequest request)
    {
        Auction auction = auctionRepository.findById(request.getAuctionId())
                .orElseThrow(() -> new IllegalArgumentException("Неверный номер аукциона"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Неверный пользователь"));

        //BigDecimal max = auction.getMaxBid().orElse(BigDecimal.ZERO);
        BigDecimal max = getMaxBid(auction.getId());

        if (request.getValue().compareTo(max) > 0)
            auction.addBid(new Bid(null, Instant.now(), request.getValue(), auction, user));
        else {
            log.warn("Ставка {} меньше максимальной в аукционе {}", request.getValue(), max);
            throw new BidException("Ставка меньше максимальной");
        }

        return new BidResponse(true, Instant.now());
    }

    @PostMapping("/delete/{id}")
    public void deleteAuction(@PathVariable Long id)
    {
        Auction auction = auctionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No such auction"));

        auctionRepository.delete(auction);
    }

    /**
     * Поиск максимальной ставки в аукционе используюя блокировку
     * @param auctionId идентификатор аукциона
     * @return максимальная ставка в аукционе
     */
    private BigDecimal getMaxBid(Long auctionId) {
        List<Bid> bidList = bidRepository.getBids(auctionId);
        return bidList.stream().map(Bid::getValue).max(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
    }
}
