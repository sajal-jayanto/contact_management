package com.cramstack.contact_management.Services;

import com.cramstack.contact_management.Payloads.UserPayload;
import com.cramstack.contact_management.Models.User;
import com.cramstack.contact_management.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService  {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository , BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(UserPayload userPayload) {

        userPayload.setPassword(bCryptPasswordEncoder.encode(userPayload.getPassword()));
        User userEntity = new User(userPayload.getUsername(), userPayload.getEmail(), userPayload.getPassword());

        userRepository.save(userEntity);
    }

}
