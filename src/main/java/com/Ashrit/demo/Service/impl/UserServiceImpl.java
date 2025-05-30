package com.Ashrit.demo.Service.impl;

import com.Ashrit.demo.Entity.User;
import com.Ashrit.demo.Repository.UserRepository;
import com.Ashrit.demo.Service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
    @Override
    public User login(User user) {
    Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
    if (existingUser.isPresent()) {
        User dbUser = existingUser.get();
        if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            return dbUser;
        }
    }
    throw new RuntimeException("Invalid email or password");
}

}
