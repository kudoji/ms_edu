package com.ms.edu.users.services;

import com.ms.edu.users.entities.User;
import com.ms.edu.users.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final static String USER_CREATED_INFO = "User w/ name '%s' created, id: %s";
    private final static String USER_UPDATED_INFO = "User w/ name '%s' updated";
    private final static String USER_DELETED_INFO = "User w/ id '%s' deleted";

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

    public User getUser(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String updateUser(User user, long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        userRepository.save(user);

        return String.format(
                USER_UPDATED_INFO,
                user.getSecondName()
        );
    }

    public String deleteUser(long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        userRepository.deleteById(id);

        return String.format(
                USER_DELETED_INFO,
                id
        );
    }
}
