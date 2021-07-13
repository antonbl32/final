package it.free.final_spring.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
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
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username",updatable = false,unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "time_create", updatable = false)
    private LocalDateTime timeCreate;
    @OneToMany(fetch = FetchType.LAZY,targetEntity = NoteEntity.class)
    @JoinColumn(name = "note_id")
    private List<NoteEntity> notes;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "role")
    private Set<ERole> authorities;

    @PrePersist
    public void setTimeCreate() {
        this.timeCreate = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
