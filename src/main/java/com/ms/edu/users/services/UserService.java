package com.ms.edu.users.services;

import com.ms.edu.users.entities.User;
import com.ms.edu.users.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final static String USER_CREATED_INFO = "User w/ name '%s' created, id: %s";

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(User user) {
        User userSaved = userRepository.save(user);

        return String.format(
                USER_CREATED_INFO,
                userSaved.getFirstName(),
                userSaved.getId()
        );
    }
}
