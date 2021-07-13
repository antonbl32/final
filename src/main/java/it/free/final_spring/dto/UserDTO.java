package it.free.final_spring.dto;

import it.free.final_spring.entity.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private Long id;
    @NotBlank
    @NotBlank
    private String username;
    @NotBlank
    @NotBlank
    private String password;
    private List<NoteEntity> notes;
}
