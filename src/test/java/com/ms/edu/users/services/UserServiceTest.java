package com.ms.edu.users.services;

import com.ms.edu.users.entities.User;
import com.ms.edu.users.repositories.UserRepository;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest {
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
    public void createUser_positiveTest() {
        Mockito.when(userRepository.save(user2save)).thenReturn(userSaved);

        User result = userService.createUser(user2save);

        assertEquals(
                userSaved,
                result
        );

        Mockito.reset(userRepository);
    }

    @Test
    public void createUser_negativeTest() {
        Mockito.when(userRepository.save(user2save)).thenThrow(PersistenceException.class);

        Executable executable = () -> userService.createUser(user2save);

        assertThrows(
                PersistenceException.class,
                executable
        );

        Mockito.reset(userRepository);
    }

    @Test
    public void getUser_positiveTest() {
        long userId = 10L;
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(userSaved));

        User result = userService.getUser(userId);

        assertEquals(
                userSaved,
                result
        );

        Mockito.reset(userRepository);
    }

    @Test
    public void getUser_negativeTest() {
        long userId = 10L;
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Executable executable = () -> userService.getUser(userId);

        assertThrows(
                ResponseStatusException.class,
                executable
        );

        Mockito.reset(userRepository);
    }

    @Test
    public void updateUser_positiveTest() {
        long userId = 10L;
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(userSaved));
        Mockito.when(userRepository.save(user2save)).thenReturn(userSaved);

        User result = userService.updateUser(user2save, userId);

        assertEquals(
                userSaved,
                result
        );
        assertEquals(userId, user2save.getId());

        Mockito.reset(userRepository);
    }

    @Test
    public void updateUser_negativeTest() {
        long userId = 10L;
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(user2save)).thenReturn(userSaved);

        Executable executable = () -> userService.updateUser(user2save, userId);

        assertThrows(
                ResponseStatusException.class,
                executable
        );

        Mockito.reset(userRepository);
    }
}