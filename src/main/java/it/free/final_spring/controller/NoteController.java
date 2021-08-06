package it.free.final_spring.controller;

import it.free.final_spring.entity.NoteEntity;
import it.free.final_spring.entity.UserEntity;
import it.free.final_spring.exception.NotFoundUserException;
import it.free.final_spring.mapper.MainMapper;
import it.free.final_spring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/note")
public class NoteController {
    private UserService userService;
    private MainMapper mapper;

    public NoteController(UserService userService, MainMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping
    public String addNote(Model model){
        model.addAttribute("note",new NoteEntity());
        return "note";
    }
    @PostMapping
    public String saveNote(@ModelAttribute("note") NoteEntity noteEntity, Principal principal, Model model){
        UserEntity userEntity= userService.findByUsername(principal.getName());
        userService.addNoteToUser(noteEntity,userEntity);
        model.addAttribute("user", mapper.getUserDTO(userEntity));
        return "usernotes";
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
