package com.energy.dashboard.Repository;

import com.energy.dashboard.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    // Custom query to find pending users if you want to filter them specifically
    List<User> findByApprovedFalse();
}
