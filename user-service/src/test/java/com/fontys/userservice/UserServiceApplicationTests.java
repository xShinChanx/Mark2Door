package com.fontys.userservice;

import com.fontys.userservice.model.UserDetails;
import com.fontys.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testCreateOrUpdateUserAddress() throws Exception {
		String userDetailsJson = "{ \"address\": \"123 Main St\", \"city\": \"Metropolis\", \"houseNo\": \"101\", \"userID\": 1000 }";

		mockMvc.perform(post("/user/create")
						.contentType("application/json")
						.content(userDetailsJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.address").value("123 Main St"))
				.andExpect(jsonPath("$.city").value("Metropolis"))
				.andExpect(jsonPath("$.houseNo").value("101"))
				.andExpect(jsonPath("$.userID").value(1000));

		Optional<UserDetails> userDetailsOptional = userRepository.findByUserID(1000L);
		assertTrue(userDetailsOptional.isPresent());
		UserDetails userDetails = userDetailsOptional.get();
		assertEquals("123 Main St", userDetails.getAddress());
		assertEquals("Metropolis", userDetails.getCity());
		assertEquals("101", userDetails.getHouseNo());
		assertEquals(Long.valueOf(1000), userDetails.getUserID());
	}

	@Test
	public void testDeleteUserAddress() throws Exception {
		Long userID = 1000L;

		mockMvc.perform(delete("/user/{userID}", userID))
				.andExpect(status().isOk());

		// Verify the user address was deleted using the TestH2Repository
		Optional<UserDetails> userDetailsOptional = userRepository.findByUserID(userID);
		assertFalse(userDetailsOptional.isPresent());
	}

}
