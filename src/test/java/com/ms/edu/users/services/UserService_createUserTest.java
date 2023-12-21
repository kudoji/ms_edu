package com.ms.edu.users.services;

import com.ms.edu.users.entities.User;
import com.ms.edu.users.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UserService_createUserTest {

    @Test
    void validInput_userCreated() {
        User user2save = new User("fn", "sn", "mn", "email");
        User userSaved = new User(1L, user2save);

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.save(user2save)).thenReturn(userSaved);

        UserService userService = new UserService(userRepository);

        String result = userService.createUser(user2save);

        assertEquals(
                String.format(
                        UserService.USER_CREATED_INFO,
                        userSaved.getFirstName(),
                        userSaved.getId()
                ),
                result
        );
    }
}