package se.groupjcnr.projecti.resource.dao.jpa;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import se.groupjcnr.projecti.model.WorkItem;
import se.groupjcnr.projecti.resource.dao.WorkItemDAO;

import static java.util.function.Function.identity;

public class WorkItemJPADAO extends AbstractJPADAO<WorkItem> implements WorkItemDAO {

	protected WorkItemJPADAO(EntityManagerFactory factory) {
		super(WorkItem.class, factory);
	}

	@Override
	public List<WorkItem> getItemByStatus() {
		return query("WorkItem.getItemByStatus", identity());
	}

	@Override
	public List<WorkItem> getItemByTeam() {
		return query("WorkItem.getItemByTeam", identity());
	}

	@Override
	public List<WorkItem> getItemByUser() {
		return query("WorkItem.getItemByUser", identity());
	}
}