package com.ms.edu.users.repositories;

import com.ms.edu.users.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
