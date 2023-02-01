package org.example.controller;

import org.example.entity.UserEntity;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model){
        List<UserEntity> userEntities = userService.getAllUsers();
        model.addAttribute("users", userEntities);
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("user", new UserEntity());
        return "create";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUser(id));
        return "show";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        UserEntity oldUser = userService.getUser(id);
        model.addAttribute("user", oldUser);
        return "edit";
    }

    @PostMapping("/edit/{id}/update")
    public String update(@ModelAttribute UserEntity user, @PathVariable Long id){
        userService.update(id, user);
        return "redirect:/user/show/"+id;
    }

    @PostMapping("/show/{id}/delete")
    public String delete(@PathVariable Long id){
        userService.delete(id);
        return "redirect:/user";
    }


    @PostMapping("/create/post")
    public String createPost(@ModelAttribute UserEntity user){
        userService.createUser(user);
        return "redirect:/user";
    }





}
