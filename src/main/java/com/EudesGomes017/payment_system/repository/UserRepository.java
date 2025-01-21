package com.EudesGomes017.payment_system.repository;

import com.EudesGomes017.payment_system.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository  extends JpaRepository<Users, Long> {

    UserDetails findByEmail(String email);
    Users findByVerificationCode(String verificationCode);


}
