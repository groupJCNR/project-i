package se.groupjcnr.projecti.resource.dao.jpa;

import javax.persistence.EntityManagerFactory;

import se.groupjcnr.projecti.model.Issue;
import se.groupjcnr.projecti.resource.dao.IssueDAO;

public final class IssueJPADAO extends AbstractJPADAO<Issue> implements IssueDAO {

	public IssueJPADAO(EntityManagerFactory factory) {
		super(Issue.class, factory);
	}


}
