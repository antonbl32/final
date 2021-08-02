package it.free.final_spring;

import it.free.final_spring.entity.UserEntity;
import it.free.final_spring.security.SecurityUser;
import it.free.final_spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.Assert;

@SpringBootTest
class FinalSpringApplicationTests {
    private UserService userService;
    @Autowired
    public FinalSpringApplicationTests(UserService userService) {
        this.userService = userService;
    }

    @Test
    void getUserTest() {
        UserEntity userEntity=userService.findByIdWithoutNotes(5L);
        Assert.hasText("admin",userEntity.getUsername());
    }
    @Test
    void addAndDeleteTest(){
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername("nn");
        userEntity.setPassword("123");
        UserEntity userEntity1=userService.save(userEntity);
        Assert.notNull(userEntity1.getId(),"After save id must be");
        userService.deleteUser(userEntity1.getId());
    }
    @Test
    void getAllPermissionsByUser(){
        UserEntity userEntity=userService.findByIdWithoutNotes(5L);
        User user = (User) SecurityUser.fromUser(userEntity);
        Assert.noNullElements(user.getAuthorities(),"Authorities must be");
    }
}
