package com.github.kalininaleksandrv.digitincreaserapi.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class NumberControllerTest {

    @Autowired
    private NumberController numberController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(numberController)
                .build();
    }

    @Test
    void testingContextLoad() {
        assertNotNull(numberController, "BreedController is NULL");
    }

    @Test
    void increase() throws Exception {
        String requestJson = "1 2 3";

        ResultActions resultActions = mockMvc.perform(post("/api/increase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk());

        assertNotNull(resultActions);

        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is("2 3 4")));
    }

    @Test
    void increaseContainsLetters() throws Exception {
        String requestJson = "1 2 3f";

        ResultActions resultActions = mockMvc.perform(post("/api/increase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isInternalServerError());

        assertNotNull(resultActions);

        resultActions
                .andDo(print())
                .andExpect(jsonPath("$", Matchers.is("request must contain digits separated by whitespaces")));
    }

    @Test
    void increaseContainsLargeDigits() throws Exception {
        String requestJson = "1 2 333333333333333333333333333333333333333333";

        ResultActions resultActions = mockMvc.perform(post("/api/increase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isInternalServerError());

        assertNotNull(resultActions);

        resultActions
                .andDo(print())
                .andExpect(jsonPath("$", Matchers.is("digits must be in range -2147483648 to 2147483647")));

    }
}