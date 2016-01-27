package se.groupjcnr.projecti.resource.dao;


import java.util.List;

import se.groupjcnr.projecti.model.Team;
import se.groupjcnr.projecti.model.User;

public interface UserDAO extends CrudDAO<User>{

    List<User> getAll();

    User getUserByFirstName(String firstName);

    User getUserByLastName(String lastName);

    User getUserByUsername(String username);
    
    User getUserByUserID(String userId);
    
    List<User> getUserByTeam(Team team);

}
