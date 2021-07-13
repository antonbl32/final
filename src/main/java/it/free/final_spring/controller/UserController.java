package it.free.final_spring.controller;

import it.free.final_spring.dto.UserDTO;
import it.free.final_spring.entity.UserEntity;
import it.free.final_spring.exception.NotFoundUserException;
import it.free.final_spring.mapper.MainMapper;
import it.free.final_spring.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static java.util.stream.Collectors.toList;

@Controller
public class UserController {
    private UserService userService;
    private MainMapper mapper;

    public UserController(UserService userService, MainMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }
    @GetMapping("/all")
    public String getAllUsers(ModelMap model) {
        model.addAttribute("users", userService.findAll().stream()
                .map(a -> mapper.getUserDTO(a)).collect(toList()));
        return "users";
    }
    @GetMapping("/add")
    public String registerUser(Model model){
        UserDTO userDTO=new UserDTO();
        model.addAttribute("userDTO",userDTO);
        return "user";
    }
    @PostMapping()
    public String addUser(@ModelAttribute("userDTO") UserDTO user, Model model) {
        UserEntity userEntity = mapper.getUserEntity(user);
        model.addAttribute("user", userService.save(userEntity));
        return "users";
    }

    @GetMapping("{id}")
    public String getById(@PathVariable(value = "id") String id, Model model) {
        model.addAttribute("user", mapper.getUserDTO(userService.findByIdWithNotes(Long.valueOf(id))));
        return "usernotes";
    }

    @ExceptionHandler(NotFoundUserException.class)
    public ModelAndView handleCustomException(NotFoundUserException ex) {
        ModelAndView model = new ModelAndView("error/error");
        model.addObject("errMsg", ex.getMessage());
        return model;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {
        ModelAndView model = new ModelAndView("error/error");
        model.addObject("errMsg", this.getClass()+" BAD REQUEST. NOT FOUND ENDPOINT");
        return model;
    }

}