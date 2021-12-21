package com.example.trade.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "bid_organizer")
@Entity
public class AuctionOrganizer {
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
	private String organizationalForm;
    
	private String name;
    
	private String businessAddress;
    
	private String postAddress;
    
	private String inn;
    
	private String kpp;
    
	private String ogrn;
    
	private String managerName;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Auction> auctions;
	
}