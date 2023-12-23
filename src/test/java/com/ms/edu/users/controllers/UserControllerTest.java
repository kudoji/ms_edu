package com.ms.edu.users.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    private static final String USERS_URL = "/users";
    private static final String USER_FN = "fn1";
    private static final String USER_SN = "sn1";
    private static final String USER_MN = "mn1";
    private static final String USER_EMAIL = "fn1@mail.fn";

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createUserTest() throws Exception {
        String request = "{\n" +
                "    \"firstName\": \"" + USER_FN + "\",\n" +
                "    \"secondName\": \"" + USER_SN + "\",\n" +
                "    \"middleName\": \"" + USER_MN + "\",\n" +
                "    \"email\": \"" + USER_EMAIL + "\"\n" +
                "}";

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .post(USERS_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
        );

        resultActions
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", isA(Integer.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is(USER_FN)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.secondName", is(USER_SN)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.middleName", is(USER_MN)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", is(USER_EMAIL)))
        ;
    }

    @Test
    public void getUserTest() throws Exception {
        long userId = 1L;

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .get(USERS_URL + "/" + userId)
        );

        String response = "{\n" +
                "    \"id\": " + userId + ",\n" +
                "    \"firstName\": \"fName1\",\n" +
                "    \"secondName\": \"sName1\",\n" +
                "    \"middleName\": \"mName1\",\n" +
                "    \"email\": \"email1@email.com\"\n" +
                "}";

        resultActions
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    public void updateUserTest() throws Exception {
        long userId = 1L;

        String request = "{\n" +
                "    \"firstName\": \"fName12\",\n" +
                "    \"secondName\": \"sName1\",\n" +
                "    \"middleName\": \"mName1\",\n" +
                "    \"email\": \"email1@email.com\"\n" +
                "}";

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .put(USERS_URL + "/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
        );

        String response = "{\n" +
                "    \"id\": " + userId + ",\n" +
                "    \"firstName\": \"fName12\",\n" +
                "    \"secondName\": \"sName1\",\n" +
                "    \"middleName\": \"mName1\",\n" +
                "    \"email\": \"email1@email.com\"\n" +
                "}";

        resultActions
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @Test
    public void deleteUserTest() throws Exception {
        long userId = 1L;

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .delete(USERS_URL + "/" + userId)
        );

        String response = "{\n" +
                "    \"id\": " + userId + ",\n" +
                "    \"firstName\": \"fName12\",\n" +
                "    \"secondName\": \"sName1\",\n" +
                "    \"middleName\": \"mName1\",\n" +
                "    \"email\": \"email1@email.com\"\n" +
                "}";

        resultActions
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        ;
    }

    @Test
    public void getUsersTest() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .get(USERS_URL)
        );

        resultActions
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", isA(ArrayList.class)))
        ;
    }
}