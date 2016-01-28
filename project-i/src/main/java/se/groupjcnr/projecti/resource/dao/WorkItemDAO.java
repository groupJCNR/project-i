package se.groupjcnr.projecti.resource.dao;

import java.util.List;

import se.groupjcnr.projecti.model.User;
import se.groupjcnr.projecti.model.WorkItem;

public interface WorkItemDAO extends CrudDAO<WorkItem> {
	
	List<WorkItem> getWorkItemsByUser(User user);
	
	List<WorkItem> getWorkItemsByPartDesc(String partialDesc);
	
    List<WorkItem> getAll();

}
