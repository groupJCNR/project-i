package se.groupjcnr.projecti.resource.dao.jpa;

import static java.util.function.Function.identity;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import se.groupjcnr.projecti.model.User;
import se.groupjcnr.projecti.model.WorkItem;
import se.groupjcnr.projecti.model.WorkItem.Status;
import se.groupjcnr.projecti.resource.dao.WorkItemDAO;

public class WorkItemJPADAO extends AbstractJPADAO<WorkItem> implements WorkItemDAO {

	public WorkItemJPADAO(EntityManagerFactory factory) {
		super(WorkItem.class, factory);
	}

	@Override
	public List<WorkItem> getWorkItemByStatus(Status status) {
		if (queryVariable("status", status.toString(), "WorkItem.getWorkItemByStatus", identity()).size() > 0) {
			return queryVariable("status", status.toString(), "WorkItem.getWorkItemByStatus", identity());
		} else {
			return null;
		}
	}

	@Override
	public List<WorkItem> getWorkItemsByTeam(Long id) {
		if (queryVariable("teamid", id.toString(), "WorkItem.getWorkItemByTeam", identity()).size() > 0) {
			return queryVariable("teamid", id.toString(), "WorkItem.getWorkItemByTeam", identity());
		} else {
			return null;
		}
	}

	@Override
	public List<WorkItem> getWorkItemsByUser(User user) {
		return query("WorkItem.getWorkItemByUser", identity());
	}
	
	@Override
	public List<WorkItem> getWorkItemsByPartDesc(String partialDesc) {
		return query("WorkItem.getWorkItemsByPartDesc", identity());
	}

	@Override
	public List<WorkItem> getAll() {
		return query("WorkItem.getAll", identity());
	}

}
