package se.groupjcnr.projecti.resource.dao;

import java.util.List;

import se.groupjcnr.projecti.model.User;
import se.groupjcnr.projecti.model.WorkItem;
import se.groupjcnr.projecti.model.WorkItem.Status;

public interface WorkItemDAO extends CrudDAO<WorkItem> {

	List<WorkItem> getWorkItemByStatus(Status status);
	
	List<WorkItem> getWorkItemsByTeam(Long id);
	
	List<WorkItem> getWorkItemsByUser(User user);
	
	List<WorkItem> getWorkItemsByPartDesc(String partialDesc);
	
    List<WorkItem> getAll();

}
