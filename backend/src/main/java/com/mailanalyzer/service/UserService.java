package com.mailanalyzer.service;

import com.mailanalyzer.entity.User;
import com.mailanalyzer.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User registerUser(User user) {
        // E-posta daha önce kullanılmış mı kontrol et
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Bu e-posta adresi zaten kullanılıyor!");
        }

        return userRepository.save(user);
    }

    // E-posta ile kullanıcı bulma mantığı
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}