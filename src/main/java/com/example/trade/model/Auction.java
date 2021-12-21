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
    
    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;
    
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "auction_id")
    private List<Bid> bids = new ArrayList<>();
    
    @ManyToMany(mappedBy = "auctions",fetch = FetchType.EAGER)
    private List<User> participants;
}
