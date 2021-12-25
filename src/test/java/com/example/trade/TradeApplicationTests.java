package com.example.trade;

import com.example.trade.model.Auction;
import com.example.trade.model.User;
import com.example.trade.repository.AuctionRepository;
import com.example.trade.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
class TradeApplicationTests {

	@Test
	void contextLoads() {
	}

}
