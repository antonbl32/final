package it.free.final_spring;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinalSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalSpringApplication.class, args);
    }

    @Bean
    public ModelMapper createModelMapper(){
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }
}
