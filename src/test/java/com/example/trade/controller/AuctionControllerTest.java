package com.example.trade.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuctionControllerTest
{
    @Autowired private MockMvc mockMvc;

    @Test
    void givenSmallBid_whenBid_thenFail() throws Exception {
        String json = "{\n" +
                "    \"user_id\" : 3,\n" +
                "    \"auction_id\" : 1,\n" +
                "    \"value\" : 1.00\n" +
                "}";

        mockMvc.perform(post("/auction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenWrongAuctionId_whenBid_thenFail() throws Exception {
        String json = "{\n" +
                "    \"user_id\" : 3,\n" +
                "    \"auction_id\" : 100,\n" +
                "    \"value\" : 1.00\n" +
                "}";

        mockMvc.perform(post("/auction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}