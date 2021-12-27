package com.example.trade.persistance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Auction
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String region;
    
    private String name;
    
    private Instant startDateTime;
    
    private Instant registrationDateTime;
    
    private Instant endDateTime;
    
    private String rules;
    
    private BigDecimal startPrice;
    
    private BigDecimal priceStep;
    
    private Integer bidWindow;
    
    private String priceForm;
    
    private Boolean withVAT;
    
    @ManyToOne(cascade = {PERSIST, MERGE})
    @JoinColumn(name = "organizer_id")
    private User organizer;
    
    @OneToMany(orphanRemoval = true, cascade = ALL)
    @JoinColumn(name = "auction_id")
    private List<Bid> bids = new ArrayList<>();
    
    @ManyToMany(fetch = LAZY, cascade = {PERSIST, MERGE})
    @JoinTable(name = "auction_user",
            joinColumns = @JoinColumn(name = "auction_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> participants = new HashSet<>();

    public void addUser(User user) {
        this.participants.add(user);
        user.getAuctions().add(this);
    }

    public void removeUser(User user) {
        this.participants.remove(user);
        user.getAuctions().remove(this);
    }

    public void addBid(Bid bid) {
        this.bids.add(bid);
        bid.setAuction(this);
    }

    public void removeBid(Bid bid) {
        this.bids.remove(bid);
        bid.setAuction(null);
    }

    /**
     * Ищет максимальную ставку
     * @return максимальная ставка
     */
    public Optional<BigDecimal> getMaxBid() {
        return this.bids.stream().map(Bid::getValue).max(Comparator.naturalOrder());
    }
}
