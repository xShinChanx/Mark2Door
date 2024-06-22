package com.fontys.shopservice;

import com.fontys.shopservice.model.Shop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ShopServiceIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestH2Repository testH2Repository;

    @Test
    public void testCreateAndFindShop() throws Exception {
        // Create a shop using the MockMvc
        String shopJson = "{ \"name\": \"Lulu\", \"description\": \"Indian Shop\", \"ownerId\": 1 }";

        mockMvc.perform(post("/shop/createShop")
                        .contentType("application/json")
                        .content(shopJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Shop created successfully"));

        // Verify the shop was created using the TestH2Repository
        Optional<Shop> shopOptional = testH2Repository.findByOwnerId(1L);
        assertTrue(shopOptional.isPresent());
        Shop shop = shopOptional.get();
        assertEquals("Lulu", shop.getName());
        assertEquals("Indian Shop", shop.getDescription());
        assertEquals(Long.valueOf(1), shop.getOwnerId());
    }

    @Test
    public void testHelloEndpoint() throws Exception {
        mockMvc.perform(get("/shop/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }
}