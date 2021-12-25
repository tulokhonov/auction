package com.example.trade.controller;

import com.example.trade.model.Auction;
import com.example.trade.model.Bid;
import com.example.trade.model.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private EntityManager em;

    @PostMapping("/user/delete/{id}")
    @Transactional
    public void deleteUser(@PathVariable Long id)
    {
        User user = em.find(User.class, id);

        for (Auction a : user.getAuctions()) {
            if (a.getParticipants().size() == 1) {
                em.remove(a);
            } else {
                a.getParticipants().remove(user);
            }
        }
        // Удаляем ставки
        em.createQuery("select b from Bid b where b.user = :user", Bid.class)
                .setParameter("user", user).getResultList()
                .forEach(b -> em.remove(b));

        // Удаляем юзера
        em.remove(user);
    }
}
