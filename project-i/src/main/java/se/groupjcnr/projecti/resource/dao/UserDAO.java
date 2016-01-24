package se.groupjcnr.projecti.resource.dao;


import java.util.List;

import se.groupjcnr.projecti.model.Team;
import se.groupjcnr.projecti.model.User;

public interface UserDAO extends CrudDAO<User>{
	
    User getUserByUserID(String userId);
    
    //name söker på username, first + lastName
    User getUserByName(String name);
    
    List<User> getUserByTeam(Team team);

    List<User> getAll();
    
    
}