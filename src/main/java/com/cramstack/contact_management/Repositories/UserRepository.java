package com.cramstack.contact_management.Repositories;

import com.cramstack.contact_management.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
