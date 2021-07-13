package it.free.final_spring.service;

import it.free.final_spring.entity.UserEntity;
import it.free.final_spring.exception.NotFoundUserException;
import it.free.final_spring.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public UserEntity findByIdWithNotes(Long id) {
        return userRepository.findByIdOrderByUsername(id)
                .orElseThrow(()-> new NotFoundUserException("user with id"+id+" not found"));
    }

    @Override
    public UserEntity findByIdWithoutNotes(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new NotFoundUserException("user with id"+id+" not found"));
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }
}
