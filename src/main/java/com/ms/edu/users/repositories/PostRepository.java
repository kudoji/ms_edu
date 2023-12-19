package com.ms.edu.users.repositories;

import com.ms.edu.users.entities.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
