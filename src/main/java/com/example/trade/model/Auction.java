package com.example.trade.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Auction {
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
    
    @OneToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;
    
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "auction_id")
    private List<Bid> bids = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "auction_user",
            joinColumns = @JoinColumn(name = "auction_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participants = new ArrayList<>();

    public void addUser(User user) {
        this.participants.add(user);
        user.getAuctions().add(this);
    }

    public void removeUser(User user) {
        this.participants.remove(user);
        user.getAuctions().remove(this);
    }
}
