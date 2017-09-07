package edu.berliner.securityforspringprogrammers.repositories;

import edu.berliner.securityforspringprogrammers.models.Userz;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Userz, Long>
{
    Userz findByUsername(String username);

    Userz findByEmail(String email);

    Long countByEmail(String email);

    Long countByUsername(String username);
}
