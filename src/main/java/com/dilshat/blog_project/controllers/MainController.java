package com.dilshat.blog_project.controllers;

import com.dilshat.blog_project.dtos.RegistrationUserDTO;
import com.dilshat.blog_project.exceptions.UserAlreadyExistException;
import com.dilshat.blog_project.models.User;
import com.dilshat.blog_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class MainController {

    ModelAndView registrationMaV = new ModelAndView("registration");

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String loginPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/posts";
    }

    @GetMapping("/registration")
    public String registrationPage(
            Model model
    ){
        RegistrationUserDTO userDTO = new RegistrationUserDTO();
        model.addAttribute("user" , userDTO);
        return "registration";
    }

    @PostMapping("/registration")
    public String register(
            @ModelAttribute(name = "user") @Valid RegistrationUserDTO userDTO, BindingResult bindingResult,
            Model model
    ){
        try {
            User user = userService.registerNewUserAccount(userDTO);
        } catch (UserAlreadyExistException e) {
            model.addAttribute("user", userDTO);
            model.addAttribute("message", "Account already exist!");
            return "registration";
        }
        return "redirect:/login";
    }
}
