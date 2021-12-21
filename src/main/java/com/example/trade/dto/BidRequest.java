package com.example.trade.dto;

import com.example.trade.model.Auction;
import com.example.trade.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class BidRequest
{
    @JsonProperty("auction_id")
    private Long auctionId;
    @JsonProperty("user_id")
    private Long userId;
    private BigDecimal value;
}