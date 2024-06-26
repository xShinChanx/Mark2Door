package com.fontys.shopservice;

import com.fontys.shopservice.model.Shop;
import com.fontys.shopservice.repository.ShopRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ShopServiceIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ShopRepository shopRepository;

    @Test
    public void testCreateAndFindShop() throws Exception {
        String shopJson = "{ \"name\": \"Lulu\", \"description\": \"Indian Shop\", \"ownerId\": 99 }";

        mockMvc.perform(post("/shop/createShop")
                        .contentType("application/json")
                        .content(shopJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Shop created successfully"));

        Optional<Shop> shopOptional = shopRepository.findByOwnerId(99L);
        assertTrue(shopOptional.isPresent());
        Shop shop = shopOptional.get();
        assertEquals("Lulu", shop.getName());
        assertEquals("Indian Shop", shop.getDescription());
        assertEquals(Long.valueOf(99), shop.getOwnerId());
    }

    @Test
    public void testDeleteShop() throws Exception {
        Long ownerId = 99L;

        mockMvc.perform(delete("/shop/delete/{ownerId}", ownerId))
                .andExpect(status().isOk())
                .andExpect(content().string("Shop deleted successfully"));

        Optional<Shop> shopOptional = shopRepository.findByOwnerId(ownerId);
        assertFalse(shopOptional.isPresent());
    }
}
