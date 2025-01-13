package com.EudesGomes017.payment_system.service;

import com.EudesGomes017.payment_system.entity.Users;
import com.EudesGomes017.payment_system.repository.UserRepository;
import com.EudesGomes017.payment_system.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users registerUser(Users users) {

        if (userRepository.findByEmail(users.getEmail()) != null) {
            throw new RuntimeException("This email already exists");
        } else  {
                String encondedPassword = passwordEncoder.encode(users.getPassword());
            users.setPassword(encondedPassword);

            String randomCode = RandomString.generateRandomString(64);
            users.setVerificationCode(randomCode);
            users.setEnabled(false);

            Users saveUser = userRepository.save(users);


            return saveUser;

        }


    }


}
