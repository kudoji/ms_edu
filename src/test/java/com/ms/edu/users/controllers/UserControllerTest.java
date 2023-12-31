package com.ms.edu.users.controllers;

import org.junit.jupiter.api.*;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {
    private static final String USERS_URL = "/users";
    private static final String USER_FN = "fn1";
    private static final String USER_SN = "sn1";
    private static final String USER_MN = "mn1";
    private static final String USER_EMAIL = "fn1@mail.fn";

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    private String getUserRequest() {
        return "{\n" +
                "    \"firstName\": \"" + USER_FN + "\",\n" +
                "    \"secondName\": \"" + USER_SN + "\",\n" +
                "    \"middleName\": \"" + USER_MN + "\",\n" +
                "    \"email\": \"" + USER_EMAIL + "\"\n" +
                "}";
    }

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Order(1)
    public void createUser_positiveTest() throws Exception {
        long userId = 1L;
        String request = getUserRequest();

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .post(USERS_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
        );

        resultActions
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is((int) userId)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is(USER_FN)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.secondName", is(USER_SN)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.middleName", is(USER_MN)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", is(USER_EMAIL)))
        ;
    }

    @Test
    public void createUser_negativeTest() throws Exception {
        String request = "";

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .post(USERS_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
        );

        resultActions
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        ;
    }

    @Test
    @Order(2)
    public void getUser_positiveTest() throws Exception {
        long userId = 1L;

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .get(USERS_URL + "/" + userId)
        );

        resultActions
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is((int) userId)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is(USER_FN)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.secondName", is(USER_SN)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.middleName", is(USER_MN)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", is(USER_EMAIL)))
        ;
    }

    @Test
    public void getUser_negativeTest() throws Exception {
        long userId = 1L;

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .get(USERS_URL + "/" + userId)
        );

        resultActions
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        ;
    }

    @Test
    @Order(3)
    public void updateUser_positiveTest() throws Exception {
        long userId = 1L;

        String request = getUserRequest();

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .put(USERS_URL + "/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
        );

        resultActions
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is((int) userId)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", is(USER_FN)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.secondName", is(USER_SN)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.middleName", is(USER_MN)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", is(USER_EMAIL)))
        ;
    }

    @Test
    public void updateUser_negativeTest() throws Exception {
        long userId = 1L;

        String request = getUserRequest();

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .put(USERS_URL + "/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
        );

        resultActions
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        ;
    }

    @Test
    @Order(4)
    public void deleteUser_positiveTest() throws Exception {
        long userId = 1L;

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .delete(USERS_URL + "/" + userId)
        );

        resultActions
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        ;
    }

    @Test
    public void deleteUser_negativeTest() throws Exception {
        long userId = 1L;

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .delete(USERS_URL + "/" + userId)
        );

        resultActions
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
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