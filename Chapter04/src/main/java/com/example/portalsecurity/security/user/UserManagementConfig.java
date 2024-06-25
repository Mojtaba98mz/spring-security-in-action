package com.example.portalsecurity.security.user;

import com.example.portalsecurity.security.password.Sha512PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class UserManagementConfig {

    //start 4.1.2
    @Bean
    public PasswordEncoder passwordEncoder2() {
        return new Sha512PasswordEncoder();
    }
    //end 4.1.2

    //start 4.1.4
    //@Bean         TODO : doesn't worked
    public PasswordEncoder passwordEncoder3() {
        Map<String, PasswordEncoder> encoderMap = new HashMap<>();
        encoderMap.put("noop", NoOpPasswordEncoder.getInstance());
        encoderMap.put("bcrypt", new BCryptPasswordEncoder());
        encoderMap.put("scrypt", new SCryptPasswordEncoder(
                16384, 8, 11, 32, 64));
        return new DelegatingPasswordEncoder("bcrypt", encoderMap);
    }
    //end 4.1.4


    //start 4.2.2 TODO : work on it later ?
    //@Bean
    public PasswordEncoder passwordEncoder4() {
        String string = KeyGenerators.string().generateKey();
        byte[] secure = KeyGenerators.secureRandom().generateKey();
        byte[] shared = KeyGenerators.shared(16).generateKey();

        String password = "secret";
        String valueToEncrypt = "HELLO";

        BytesEncryptor e = Encryptors.standard(password, string);
        byte[] encrypted = e.encrypt(valueToEncrypt.getBytes());
        byte[] decrypted = e.decrypt(encrypted);
        return null;
    }
    //end 4.2.2
}