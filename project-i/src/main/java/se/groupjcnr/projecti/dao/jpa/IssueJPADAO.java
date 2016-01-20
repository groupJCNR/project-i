package se.groupjcnr.projecti.dao.jpa;

import javax.persistence.EntityManagerFactory;

import se.groupjcnr.projecti.dao.IssueDAO;
import se.groupjcnr.projecti.model.Issue;

public final class IssueJPADAO extends AbstractJPADAO<Issue> implements IssueDAO {

	public IssueJPADAO(EntityManagerFactory factory) {
		super(Issue.class, factory);
	}

}
