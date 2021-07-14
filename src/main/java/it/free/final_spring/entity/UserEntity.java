package it.free.final_spring.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@NamedEntityGraph(name = "user-note-graph",
        attributeNodes =@NamedAttributeNode("notes"))
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username",updatable = false,unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "time_create", updatable = false)
    private LocalDateTime timeCreate;
    @OneToMany(fetch = FetchType.LAZY,targetEntity = NoteEntity.class,orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<NoteEntity> notes;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @PrePersist
    public void setDefault() {
        this.timeCreate = LocalDateTime.now();
        if(role==null){
            role=Role.USER;
        }
    }

}
