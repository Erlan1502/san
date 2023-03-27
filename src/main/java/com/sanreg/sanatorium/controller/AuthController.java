package com.sanreg.sanatorium.controller;

import com.sanreg.sanatorium.dto.RegistrationDto;
import com.sanreg.sanatorium.entity.UserEntity;
import com.sanreg.sanatorium.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class AuthController {
    private UserService userService;
    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user",user);
        return "register";
    }
    @PostMapping("/register/save")
    public String registerSave(@Valid @ModelAttribute("user")RegistrationDto user, BindingResult bindingResult, Model model){
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if (existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()){
            bindingResult.rejectValue("email","Пользователь с данным email уже существует");
            return "redirect:/register?fail";
        }
        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
        if (existingUserUsername != null && existingUserUsername.getEmail() != null && !existingUserUsername.getEmail().isEmpty()){
            bindingResult.rejectValue("email","Пользователь с данным именем уже существует");
            return "redirect:/register?fail";
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("user",user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/login?activationMessage";
    }
    @GetMapping("/register/activate/{code}")
    public String activate(@PathVariable String code){
        boolean isActivated = userService.activateUser(code);
        if(isActivated){
            return "redirect:/login?successActivationMessage";
        } else{
            return "redirect:/login?failActivationMessage";
        }
    }
}
