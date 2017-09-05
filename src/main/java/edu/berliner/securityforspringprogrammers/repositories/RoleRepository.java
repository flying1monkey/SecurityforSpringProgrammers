package edu.berliner.securityforspringprogrammers.repositories;

import edu.berliner.securityforspringprogrammers.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long>
{

}
