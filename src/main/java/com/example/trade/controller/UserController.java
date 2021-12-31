package com.example.trade.controller;

import com.example.trade.persistance.Auction;
import com.example.trade.persistance.Bid;
import com.example.trade.persistance.User;
import com.example.trade.service.AuctionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController
{
    private AuctionService service;

    @PostMapping("/delete/{id}")
    @Transactional
    public void deleteUser(@PathVariable Long id)
    {
        User user = service.findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("User is not found"));

        // создаем новый список, так как изменять список во время цикла нельзя
        List<Auction> auctions = new ArrayList<>(user.getAuctions());
        // удаляем юзера из аукциона
        auctions.forEach(auction -> auction.removeUser(user));

        // Удаляем ставки юзера
        service.deleteUserBids(user);

        // Удаляем юзера
        service.deleteUser(id);
    }
}
