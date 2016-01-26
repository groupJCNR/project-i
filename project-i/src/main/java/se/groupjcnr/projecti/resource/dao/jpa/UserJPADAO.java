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
	public List<User> getAll() {
		return query("User.getAll", identity());
	}

	@Override
	public User getUserByFirstName(String firstName) {
		return query("User.getUserByFirstName", identity()).get(0);
	}

	@Override
	public User getUserByLastName(String lastName) {
		return query("User.getUserByLastName", identity()).get(0);
	}

	@Override
	public User getUserByUsername(String username) {
		return query("User.getUserByUsername", identity()).get(0);
	}

	@Override
	public User getUserByUserID(String userId) {
		return query("User.getUserByUserId", identity()).get(0);
	}

	@Override
	public List<User> getUserByTeam(Team team) {
		return query("User.getUserByTeam", identity());
	}
}
