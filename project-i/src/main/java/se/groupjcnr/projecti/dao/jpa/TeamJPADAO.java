package se.groupjcnr.projecti.dao.jpa;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import se.groupjcnr.projecti.dao.TeamDAO;
import se.groupjcnr.projecti.model.Team;
import static java.util.function.Function.identity;

public class TeamJPADAO  extends AbstractJPADAO<Team> implements TeamDAO {

	protected TeamJPADAO(EntityManagerFactory factory) {
		super(Team.class, factory);
	}

	@Override
	public List<Team> getAll() {
		return query("Team.getAll", identity());
	}

}
