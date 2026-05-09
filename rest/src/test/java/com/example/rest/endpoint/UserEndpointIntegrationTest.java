package com.example.rest.endpoint;

import com.example.model.User;
import com.example.model.UserRole;
import com.example.repository.UserRepository;
import com.example.rest.dto.SaveUserRequest;
import com.example.rest.dto.UserAuthRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserEndpointIntegrationTest extends BaseEndpointIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void register_persistsUserWithEncodedPassword() throws Exception {
        SaveUserRequest request = new SaveUserRequest("Ada", "Lovelace",
                "ada@example.com", "plain-password", UserRole.USER);

        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        User saved = userRepository.findByUsername("ada@example.com").orElseThrow();
        assertNotNull(saved.getId());
        // password is hashed, not stored raw
        assertTrue(passwordEncoder.matches("plain-password", saved.getPassword()));
    }

   

    @Test
    void auth_validCredentials_returnsJwt() throws Exception {
        seedUser("user1@example.com", "pw1");

        mockMvc.perform(post("/user/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new UserAuthRequest("user1@example.com", "pw1"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", isA(String.class)))
                .andExpect(jsonPath("$.token", not("")));
    }

    @Test
    void auth_unknownUser_returnsUnauthorized() throws Exception {
        mockMvc.perform(post("/user/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new UserAuthRequest("ghost@example.com", "pw"))))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void auth_wrongPassword_returnsUnauthorized() throws Exception {
        seedUser("user2@example.com", "correct");

        mockMvc.perform(post("/user/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new UserAuthRequest("user2@example.com", "wrong"))))
                .andExpect(status().isUnauthorized());
    }


    @Test
    void shouldReturnUnauthorized_whenAnonymousCallsLegalCase() throws Exception {

        mockMvc.perform(post("/legal-cases")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                      "legalCase": "x",
                      "title": "test"
                    }
                    """))
                .andExpect(status().isUnauthorized());
    }

    private void seedUser(String username, String rawPassword) {
        User u = new User();
        u.setName("Test");
        u.setSurname("User");
        u.setUsername(username);
        u.setPassword(passwordEncoder.encode(rawPassword));
        u.setRole(UserRole.USER);
        userRepository.save(u);
    }
}