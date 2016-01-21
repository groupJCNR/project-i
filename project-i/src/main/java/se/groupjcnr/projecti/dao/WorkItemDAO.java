package se.groupjcnr.projecti.dao;

import java.util.List;

import se.groupjcnr.projecti.model.WorkItem;

public interface WorkItemDAO extends CrudDAO<WorkItem> {

	List<WorkItem> getItemByStatus();
	
	List<WorkItem> getItemByTeam();
	
	List<WorkItem> getItemByUser();
}
