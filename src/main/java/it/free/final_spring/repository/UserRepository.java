package it.free.final_spring.repository;

import it.free.final_spring.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedEntityGraph;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @EntityGraph(value = "user-note-graph")
    Optional<UserEntity> findByIdOrderByUsername(Long id);

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByUsername(String name);
}
