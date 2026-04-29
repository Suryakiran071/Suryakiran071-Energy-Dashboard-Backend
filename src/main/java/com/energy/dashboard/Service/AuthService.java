package com.energy.dashboard.Service;

import com.energy.dashboard.Model.Line;
import com.energy.dashboard.Model.User;
import com.energy.dashboard.Repository.LineRepository;
import com.energy.dashboard.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.beans.Encoder;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole("ROLE_USER");
        }
        user.setApproved(false);

        return userRepository.save(user);
    }

    public Optional<User> login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User approveUser(Long userId, Long lineId) {
        // 1. Find the User
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Find the Line
        Line line = lineRepository.findById(lineId)
                .orElseThrow(() -> new RuntimeException("Line not found"));

        // 3. Link them and Approve
        user.setApproved(true);
        user.setRole("ROLE_USER");
        user.setAssignedline(line); // Using your exact setter name

        return userRepository.save(user);
    }
}
