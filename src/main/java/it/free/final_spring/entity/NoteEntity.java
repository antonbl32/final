package it.free.final_spring.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
@NoArgsConstructor
@Data
@Entity
@Table(name = "note")
public class NoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserEntity userEntity;
}
