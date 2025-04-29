package com.parqueadero.gestion_parqueadero.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.parqueadero.gestion_parqueadero.entity.UserEntity;
import com.parqueadero.gestion_parqueadero.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity saveUser(UserEntity user) {
        // Validar rol
        if (!user.getRole().matches("ROLE_(RECTOR|DOCENTE|ESTUDIANTE)")) {
            throw new IllegalArgumentException("Rol no válido. Use: ROLE_RECTOR, ROLE_DOCENTE, ROLE_ESTUDIANTE");
        }
        // Encriptar contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    
    
}
