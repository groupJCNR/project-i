package se.groupjcnr.projecti.dao;

import java.util.List;

import se.groupjcnr.projecti.model.Team;

public interface TeamDAO extends CrudDAO<Team> {
	
	List<Team> getAll();
	
}
