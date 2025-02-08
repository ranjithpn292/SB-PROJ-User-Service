package com.ran.user.service.respositories;

import com.ran.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    // you can implement any custom methods or queries
}
