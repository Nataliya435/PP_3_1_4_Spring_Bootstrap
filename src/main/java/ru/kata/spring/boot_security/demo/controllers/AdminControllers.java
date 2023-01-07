package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.services.PersonService;
import ru.kata.spring.boot_security.demo.services.RoleService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminControllers {

    private final PersonService personService;
    private final RoleService roleService;

    public AdminControllers(PersonService personService, RoleService roleService) {
        this.personService = personService;
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public String showAllPerson(Model model,  @AuthenticationPrincipal Person principal) {
        List<Person> allPersons = personService.getAllPersons();
        model.addAttribute("allPersons", allPersons);
        model.addAttribute("principal", principal);
        return "admin/all-Persons";
    }

    @GetMapping("/user-create")
    public String createUserForm(@ModelAttribute("person") Person person){
    return "admin/all-Persons";
}

    @PostMapping("/user-create")
    public String createUser(Person person){
        personService.savePerson(person);
        return "redirect:/admin/all";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("person", personService.getPersonById(id));
        return "admin/all-Persons";
    }

    @PatchMapping("admin/user-update")
    public String updateUser(Person person){
        personService.savePerson(person);
        return "redirect:/admin/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("person", personService.getPersonById(id));
        return "admin/all-Persons";
    }
    @DeleteMapping("admin/user-delete")
    public String deleteUser(Long id) {
        personService.deletePerson(id);
        return "redirect:/admin/all";
    }

}