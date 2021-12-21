package com.example.trade.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "user")
@Entity
public class User
{
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
	
	@ManyToMany(mappedBy = "participants")
    private List<Auction> auctions = new ArrayList<>();
}