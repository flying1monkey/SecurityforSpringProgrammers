package edu.berliner.securityforspringprogrammers.controllers;

import edu.berliner.securityforspringprogrammers.UserService;
import edu.berliner.securityforspringprogrammers.models.Role;
import edu.berliner.securityforspringprogrammers.models.Userz;
import edu.berliner.securityforspringprogrammers.repositories.RoleRepository;
import edu.berliner.securityforspringprogrammers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
public class HomeController
{
    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;
    /*
     *From lesson 21
     */
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model)
    {
        model.addAttribute("user", new Userz());
        return "registration";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") Userz user, BindingResult result, Model model)
    {
        model.addAttribute("user", user);
        if(result.hasErrors())
        {
            return "registration";
        }
        else
        {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "index2";
    }
    /*
     *End of Lesson 21 Addition
     */

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
        model.addAttribute("newuser", new Userz());
        return "newuser";
    }

    @PostMapping("/newuser")
    public String submitUser(@ModelAttribute("newuser") Userz newUser, Model model)
    {
        //Collection<Role> test = newUser.getRoles();

        //Userz testUser = userRepo.findByUsername(newUser.getUsername());
        //Role userRole = roleRepo.findByRole("USER");//

        //Set user's "enabled" to true-for deletions (not yet implemented)
        newUser.setEnabled(true);

        //Get the "user" role from the repo
        Role gettingRole = roleRepo.findByRole("USER");
        //add new user to "user" role - not needed, because "mapped by" in Role gets save from User and iterates
        //  gettingRole.addToCollection(newUser);
        //add role "user" to new user
        newUser.addToCollection(gettingRole);
        //save role-not needed, because "mapped by" in Role gets save from User and iterates
        //  roleRepo.save(gettingRole);
        //save user
        userRepo.save(newUser);
        model.addAttribute("newuser", newUser);
//        return "addeduser";
        return "index2";
    }
    @RequestMapping("/adduser")
    public @ResponseBody String addUser2()  //makes sure
    {
        Userz u = new Userz();
        u.setEmail("user@email.com");
        //add the rest of the user info
        u.setUsername("joe");
        u.setFirstName("Joe");
        u.setLastName("Joeyson");
        u.setEnabled(true);
        u.setPassword("password");
        u.addRole(roleRepo.findByRole("ADMIN")); //addRole is equivalent to my addToCollection()
        userRepo.save(u);
        return "User Added";

    }
    @RequestMapping("/index2")
    public String temp(Principal p)
    {
        System.out.println(p.getName().toString());
        return "index2";
    }
    @RequestMapping("/testroles")
    public @ResponseBody String showRoles(Principal p)
    {
        p.getName();
        System.out.println(p.getName().toString());
        Iterable <Role> r = roleRepo.findAll();
        String x="<h2>ROLE DETAILS</h2>";
        for(Role item:r)
        {
            x+="Role details:"+item.getRole()+" has an ID of "+item.getId()+"<br/>";
        }

        Role findR = roleRepo.findByRole("ADMIN");
        x+=findR.getRole()+" was found with an ID of "+findR.getId();
        return x;

    }
}
