package com.ms.edu.users.services;

import com.ms.edu.users.entities.User;
import com.ms.edu.users.repositories.UserRepository;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        Mockito.reset(userRepository);
    }

    @Test
    public void validInput_dbProblems() {
        Mockito.when(userRepository.save(user2save)).thenThrow(PersistenceException.class);

        Executable executable = () -> userService.createUser(user2save);

        assertThrows(
                PersistenceException.class,
                executable
        );

        Mockito.reset(userRepository);
    }
}