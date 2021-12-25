package com.example.trade.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

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
    
    private LocalDateTime startDateTime;
    
    private LocalDateTime registrationDateTime;
    
    private LocalDateTime endDateTime;
    
    private String rules;
    
    private BigDecimal startPrice;
    
    private BigDecimal priceStep;
    
    private Integer bidWindow;
    
    private String priceForm;
    
    private Boolean withVAT;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organizer_id")
    private User organizer;
    
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "auction_id")
    private List<Bid> bids = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
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

    public void makeBid(Bid bid) {
        this.bids.add(bid);
    }

    /**
     * Ищет максимальную ставку
     * @return максимальная ставка
     */
    public Optional<BigDecimal> getMaxBid() {
        return this.bids.stream().map(Bid::getValue).max(Comparator.naturalOrder());
    }
}
