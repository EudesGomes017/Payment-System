package com.EudesGomes017.payment_system.service;


import com.EudesGomes017.payment_system.dto.UserResponse;
import com.EudesGomes017.payment_system.entity.Users;
import com.EudesGomes017.payment_system.repository.UserRepository;
import com.EudesGomes017.payment_system.utils.RandomString;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    public UserResponse registerUser(Users user) throws MessagingException, UnsupportedEncodingException {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("This email already exists");
        } else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            String randomCode = RandomString.generateRandomString(64);
            user.setVerificationCode(randomCode);
            user.setEnabled(false);

            Users savedUser = userRepository.save(user);

            UserResponse userResponse = new UserResponse(

                    savedUser.getId(),
                    savedUser.getName(),
                    savedUser.getEmail(),
                    savedUser.getPassword()
            );

            mailService.sendVerificationEmail(user);
            return userResponse;

        }

    }

    public boolean verify(String verificationCode) {

        Users user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {

            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }

    }

}
