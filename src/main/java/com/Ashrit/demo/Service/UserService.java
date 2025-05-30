package com.Ashrit.demo.Service;
import com.Ashrit.demo.Entity.User;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> findByEmail(String email);

    // new method
    User findById(Long id);
    User login(User user);
}