package edu.berliner.securityforspringprogrammers.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Collection<Userz> users;

    public Role()
    {
        users = new ArrayList<Userz>();
    }
    public void addToCollection(Userz newUser)
    {
        this.users.add(newUser);
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public Collection<Userz> getUsers()
    {
        return users;
    }

    public void setUsers(Collection<Userz> users)
    {
        this.users = users;
    }
}
