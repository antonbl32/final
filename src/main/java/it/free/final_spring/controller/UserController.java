package it.free.final_spring.controller;

import it.free.final_spring.dto.UserDTO;
import it.free.final_spring.entity.NoteEntity;
import it.free.final_spring.entity.UserEntity;
import it.free.final_spring.exception.NotFoundUserException;
import it.free.final_spring.mapper.MainMapper;
import it.free.final_spring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import static java.util.stream.Collectors.toList;

@Controller
public class UserController {
    private UserService userService;
    private MainMapper mapper;

    public UserController(UserService userService, MainMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }
    @GetMapping("/admin")
    public String adminPage(Principal principal,Model model){
        model.addAttribute("users", userService.findAll().stream()
                .map(a -> mapper.getUserDTO(a)).collect(toList()));
        return "admin";
    }


    @GetMapping
    public String helloPage(Principal principal,Model model){
        model.addAttribute("username",principal.getName());
        return "hello";
    }

    @GetMapping("/note")
    public String addNote(Principal principal,Model model){
        NoteEntity noteEntity=new NoteEntity();
        noteEntity.setUserEntity(userService.findByUsername(principal.getName()));
        model.addAttribute("note",noteEntity);
        return "note";
    }
    @PostMapping("/noteadd")
    public String saveNote(@ModelAttribute("note") NoteEntity noteEntity,Model model){
        UserEntity userEntity= userService.findByIdWithNotes(noteEntity.getUserEntity().getId());
        userEntity.getNotes().add(noteEntity);
        userService.save(userEntity);
        return "usernotes";
    }

    @GetMapping("/all")
    public String getAllUsers(ModelMap model) {
        model.addAttribute("users", userService.findAll().stream()
                .map(a -> mapper.getUserDTO(a)).collect(toList()));
        return "users";
    }

    @GetMapping("/add")
    public String registerUser(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "user";
    }

    @PostMapping
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

    @PostMapping("{id}")
    public String deleteById(@PathVariable(value = "id") String id, Model model) {
        userService.deleteUser(Long.valueOf(id));
        model.addAttribute("users", userService.findAll().stream()
                .map(a -> mapper.getUserDTO(a)).collect(toList()));
        return "users";
    }


    @ExceptionHandler(NotFoundUserException.class)
    public ModelAndView handleCustomException(NotFoundUserException ex) {
        ModelAndView model = new ModelAndView("error/error");
        model.addObject("errMsg", ex.getMessage());
        model.addObject("errEx", ex);
        return model;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {
        ModelAndView model = new ModelAndView("error/error");
        model.addObject("errMsg", this.getClass() + " BAD REQUEST. NOT FOUND ENDPOINT");
        model.addObject("errEx", ex);
        return model;
    }
}
