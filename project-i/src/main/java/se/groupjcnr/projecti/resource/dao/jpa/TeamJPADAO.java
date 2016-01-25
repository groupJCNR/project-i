package se.groupjcnr.projecti.resource.dao.jpa;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import se.groupjcnr.projecti.model.Team;
import se.groupjcnr.projecti.resource.dao.TeamDAO;

import static java.util.function.Function.identity;

public class TeamJPADAO  extends AbstractJPADAO<Team> implements TeamDAO {

	public TeamJPADAO(EntityManagerFactory factory) {
		super(Team.class, factory);
	}

	@Override
	public List<Team> getAll() {
		return query("Team.getAll", identity());
	}

}
