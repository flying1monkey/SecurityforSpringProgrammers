package edu.berliner.securityforspringprogrammers;

import edu.berliner.securityforspringprogrammers.models.Userz;
import edu.berliner.securityforspringprogrammers.repositories.RoleRepository;
import edu.berliner.securityforspringprogrammers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    public UserService(UserRepository userRepo)
    {
        this.userRepo=userRepo;
    }
    public Userz findByEmail(String email)
    {
        return userRepo.findByEmail(email);
    }

    public Long countByEmail(String email)
    {
        return userRepo.countByEmail(email);
    }

    public Userz findByUsername(String username)
    {
        return userRepo.findByUsername(username);
    }

    public void saveUser(Userz user)
    {
        user.setRoles(Arrays.asList(roleRepo.findByRole("USER")));
        user.setEnabled(true);
        userRepo.save(user);
    }
    public void saveAdmin(Userz user)
    {
        user.setRoles(Arrays.asList(roleRepo.findByRole("ADMIN")));
        user.setEnabled(true);
        userRepo.save(user);
    }
}
