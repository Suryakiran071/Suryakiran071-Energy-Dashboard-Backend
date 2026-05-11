package com.energy.dashboard.Service;

import com.energy.dashboard.Model.Line;
import com.energy.dashboard.Model.User;
import com.energy.dashboard.Repository.LineRepository;
import com.energy.dashboard.Repository.UserRepository;
import jakarta.transaction.Transactional;
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void declineUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    @Transactional
    public User approveUser(Long userId, Long lineId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Line line = lineRepository.findById(lineId)
                .orElseThrow(() -> new RuntimeException("Line not found"));

        user.setApproved(true);
        user.setRole("ROLE_USER");
        user.setAssignedLine(line);

        return userRepository.save(user);
    }

}
