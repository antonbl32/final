package it.free.final_spring.mapper;

import it.free.final_spring.dto.UserDTO;
import it.free.final_spring.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MainMapper {
    private static ModelMapper modelMapper;

    public MainMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static UserDTO getUserDTO(UserEntity original){
        return modelMapper.map(original,UserDTO.class);
    }

    public static UserEntity getUserEntity(UserDTO original){
        return modelMapper.map(original,UserEntity.class);
    }

}
