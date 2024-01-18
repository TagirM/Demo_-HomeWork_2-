package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();


        model.addAttribute("users", users);
        return "user-list";
        //return "home.html";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }
    //    Удаление пользователя
    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int userId){
        userService.deleteById(userId);
        return "redirect:/users";
    }

    //    Обновление информации о пользователе
    @GetMapping("/user-update/{id}")
    public String getUserToUpdate(@PathVariable(name = "id") int id, Model model){
        User user = userService.getOne(id);
        model.addAttribute(user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user){
        userService.updateById(user);
        return "redirect:/users";
    }
}

