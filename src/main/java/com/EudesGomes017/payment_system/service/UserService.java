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

    public Users registerUser(Users user) {

        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("This email already exists");
        } else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            String randomCode = RandomString.generateRandomString(64);
            user.setVerificationCode(randomCode);
            user.setEnabled(false);

            Users savedUser = userRepository.save(user);


            return savedUser;

        }


    }

}
