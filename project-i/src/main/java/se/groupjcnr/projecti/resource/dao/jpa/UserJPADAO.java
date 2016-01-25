package se.groupjcnr.projecti.resource.dao.jpa;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import se.groupjcnr.projecti.model.Team;
import se.groupjcnr.projecti.model.User;
import se.groupjcnr.projecti.resource.dao.UserDAO;

import static java.util.function.Function.identity;

public class UserJPADAO extends AbstractJPADAO<User> implements UserDAO {

	public UserJPADAO(EntityManagerFactory factory) {
		super(User.class, factory);
	}

	@Override
	public User getUserByUserID(String userId) {
		return query("User.getUserByUserId", identity()).get(0);
	}

	@Override
	public User getUserByName(String name) {
		return query("User.getUserByName", identity()).get(0);
	}

	@Override
	public List<User> getUserByTeam(Team team) {
		return query("User.getUserByTeam", identity());
	}

	@Override
	public List<User> getAll() {
		return query("User.getAll", identity());
	}

}
