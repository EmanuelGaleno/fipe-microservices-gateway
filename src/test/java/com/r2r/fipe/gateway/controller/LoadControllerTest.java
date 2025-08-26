package com.r2r.fipe.gateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.r2r.fipe.gateway.dto.InitialLoadRequestDTO;
import com.r2r.fipe.gateway.enums.VehicleType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureWebMvc
@ActiveProfiles("test")
class LoadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testTriggerInitialLoad() throws Exception {
        InitialLoadRequestDTO request = InitialLoadRequestDTO.builder()
                .types(Set.of(VehicleType.CARROS))
                .build();

        mockMvc.perform(post("/carga-inicial")
                        .header("X-API-Key", "test-api-key")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isAccepted());
    }

    @Test
    void testTriggerInitialLoadWithoutAuth() throws Exception {
        InitialLoadRequestDTO request = InitialLoadRequestDTO.builder()
                .types(Set.of(VehicleType.CARROS))
                .build();

        mockMvc.perform(post("/carga-inicial")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }
}
