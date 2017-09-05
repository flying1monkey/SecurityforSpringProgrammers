package edu.berliner.securityforspringprogrammers.controllers;

import edu.berliner.securityforspringprogrammers.models.Role;
import edu.berliner.securityforspringprogrammers.models.User;
import edu.berliner.securityforspringprogrammers.repositories.RoleRepository;
import edu.berliner.securityforspringprogrammers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class HomeController
{
    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @RequestMapping({"/", "/index"})
    public String index()
    {
        return "index";
    }
    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    @RequestMapping("/secure")
    public String secure()
    {
        return "secure";
    }
    @RequestMapping("/admin")
    public String admin()
    {
        return "admin";
    }

    @GetMapping("/newuser")
    public String addUser(Model model)
    {
        model.addAttribute("newuser", new User());
        return "newuser";
    }

    @PostMapping("/newuser")
    public String submitUser(@ModelAttribute("newuser") User newUser)
    {
        //Collection<Role> test = newUser.getRoles();

        //User testUser = userRepo.findByUsername(newUser.getUsername());
        //Role userRole = roleRepo.findByRole("USER");//

        //Set user's "enabled" to true-for deletions
        newUser.setEnabled(true);
        //Get the "user" role from the repo
        Role gettingRole = roleRepo.findByRole("USER");
        //add new user to "user" role
        gettingRole.addToCollection(newUser);
        //add role "user" to new user
        newUser.addToCollection(gettingRole);
        //save role
        roleRepo.save(gettingRole);
        //save user
        //userRepo.save(newUser);
        return "addeduser";
    }
}
