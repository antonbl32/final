package it.free.final_spring.service;

import it.free.final_spring.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity findByIdWithNotes(Long id);

    UserEntity findByIdWithoutNotes(Long id);

    List<UserEntity> findAll();

    UserEntity save(UserEntity userEntity);

    void deleteUser(Long id);
}
