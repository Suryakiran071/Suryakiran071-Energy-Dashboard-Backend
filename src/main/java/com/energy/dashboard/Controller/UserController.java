package com.energy.dashboard.Controller;
import com.energy.dashboard.Model.User;
import com.energy.dashboard.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private AuthService authService;

    // Get all users so the Admin can see the list
    @GetMapping
    public List<User> getAllUsers() {
        return authService.getAllUsers();
    }

    // Approve a user and assign them to a line
    @PutMapping("/{id}/approve")
    public User approveUser(@PathVariable Long id, @RequestParam Long lineId) {
        return authService.approveUser(id, lineId);
    }
}