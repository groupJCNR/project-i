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
		if (queryVariable("firstname", firstName, "User.getUserByFirstName", identity()).size()>0) {
			return queryVariable("firstname", firstName, "User.getUserByFirstName", identity()).get(0);
		} else {
			return null;
		}
	}

	@Override
	public User getUserByLastName(String lastName) {
		if (queryVariable("lastname", lastName, "User.getUserByLastName", identity()).size()>0) {
			return queryVariable("lastname", lastName, "User.getUserByLastName", identity()).get(0);
		} else {
			return null;
		}
	}

	@Override
	public User getUserByUsername(String username) {
		if (queryVariable("username", username, "User.getUserByUsername", identity()).size()>0) {
			return queryVariable("username", username, "User.getUserByUsername", identity()).get(0);
		} else {
			return null;
		}
	}

	@Override
	public User getUserByUserID(String userId) {
		if (queryVariable("userid", userId, "User.getUserByUserId", identity()).size()>0) {
			return queryVariable("userid", userId, "User.getUserByUserId", identity()).get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<User> getUserByTeam(Team team) {
		return query("User.getUserByTeam", identity());
	}
}
