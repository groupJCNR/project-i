package se.groupjcnr.projecti.resource.dao;

import java.util.List;

import se.groupjcnr.projecti.model.User;
import se.groupjcnr.projecti.model.WorkItem;

public interface WorkItemDAO extends CrudDAO<WorkItem> {

	List<WorkItem> getItemByStatus();
	
	List<WorkItem> getItemByTeam();
	
	List<WorkItem> getItemByUser();
	
    List<WorkItem> getAll();

}
