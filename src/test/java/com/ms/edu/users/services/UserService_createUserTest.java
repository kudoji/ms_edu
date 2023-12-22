package com.ms.edu.users.services;

import com.ms.edu.users.entities.User;
import com.ms.edu.users.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserService_createUserTest {
    private static UserRepository userRepository;
    private static UserService userService;
    private static User user2save;
    private static User userSaved;

    @BeforeAll
    public static void beforeClass() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);

        user2save = new User("fn", "sn", "mn", "email");
        userSaved = new User(1L, user2save);
    }

    @Test
    public void validInput_userCreated() {
        Mockito.when(userRepository.save(user2save)).thenReturn(userSaved);

        User result = userService.createUser(user2save);

        assertEquals(
                userSaved,
                result
        );
    }
}