package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.RoleService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public String showAllUser(Model model,  @AuthenticationPrincipal User principal) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("principal", principal);
        return "admin/all-Users";
    }

    @GetMapping("/user-create")
    public String createUserForm(@ModelAttribute("user") User user){
    return "admin/all-Users";
}

    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/admin/all";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "admin/all-Users";
    }

    @PatchMapping("admin/user-update")
    public String updateUser(User user){
        userService.saveUser(user);
        return "redirect:/admin/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "admin/all-Uqsers";
    }
    @DeleteMapping("admin/user-delete")
    public String deleteUser(Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/all";
    }

}