package it.free.final_spring.service;

import it.free.final_spring.entity.UserEntity;
import it.free.final_spring.exception.NotFoundUserException;
import it.free.final_spring.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    }
    @Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true)
    @Override
    public UserEntity findByIdWithNotes(Long id) {
        return userRepository.findByIdOrderByUsername(id)
                .orElseThrow(()-> new NotFoundUserException("user with id"+id+" not found"));
    }
    @Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true)
    @Override
    public UserEntity findByIdWithoutNotes(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new NotFoundUserException("user with id"+id+" not found"));
    }
    @Transactional(isolation = Isolation.READ_COMMITTED,readOnly = true)
    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public UserEntity save(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                ()-> new NotFoundUserException("user with username "+username+" not found"));
    }

    public void deleteUser(Long id){
        Optional<UserEntity> userEntity= userRepository.findById(id);
        if(userEntity.isPresent()){
            userRepository.delete(userEntity.get());
        }
    }
}
