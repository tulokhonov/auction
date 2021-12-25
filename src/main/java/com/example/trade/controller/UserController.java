package com.example.trade.controller;

import com.example.trade.model.Auction;
import com.example.trade.model.Bid;
import com.example.trade.model.User;
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
public class UserController {
    private EntityManager em;

    @PostMapping("/delete/{id}")
    @Transactional
    public void deleteUser(@PathVariable Long id)
    {
        User u = em.find(User.class, id);
        if (u == null) throw new IllegalArgumentException("User not found!");

        // создаем новый список, так как изменять список во время цикла нельзя
        List<Auction> auctions = new ArrayList<>(u.getAuctions());
        auctions.forEach(a -> a.removeUser(u));

        // Удаляем ставки
        em.createQuery("select b from Bid b where b.user = :user", Bid.class)
                .setParameter("user", u).getResultList()
                .forEach(b -> em.remove(b));

        // Удаляем юзера
        em.remove(u);
    }
}
