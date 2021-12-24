package com.example.trade;

import com.example.trade.model.Auction;
import com.example.trade.model.User;
import com.example.trade.repository.AuctionRepository;
import com.example.trade.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TradeApplicationTests {
	@Autowired
	private UserRepository repository;

	@Test
	void contextLoads() {
	}

	@Test
	void test1() {
		User user = repository.getById(3L);
		repository.delete(user);
	}
}
