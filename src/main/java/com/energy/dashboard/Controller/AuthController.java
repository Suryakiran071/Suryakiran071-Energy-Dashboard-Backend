package com.energy.dashboard.Controller;

import com.energy.dashboard.Model.User;
import com.energy.dashboard.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user){
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest){
        Optional<User> user = authService.login(loginRequest.getUsername(),loginRequest.getPassword());

        if(user.isPresent()){
            if (!user.get().isApproved()) {
                return ResponseEntity.status(403).body("Account pending admin approval");
            }
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(401).body("Invalid Credentials");
    }
}
