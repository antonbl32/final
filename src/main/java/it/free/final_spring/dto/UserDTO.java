package it.free.final_spring.dto;

import it.free.final_spring.entity.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private Long id;
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String password;
    private List<NoteEntity> notes;
}
