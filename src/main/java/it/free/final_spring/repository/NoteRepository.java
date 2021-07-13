package it.free.final_spring.repository;

import it.free.final_spring.entity.NoteEntity;
import it.free.final_spring.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    Optional<NoteEntity> findByUserEntity(UserEntity userEntity);

    Optional<NoteEntity> findByName(String name);
}
