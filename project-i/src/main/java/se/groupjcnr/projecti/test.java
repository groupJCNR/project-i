package se.groupjcnr.projecti;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import se.groupjcnr.projecti.dao.UserDAO;
import se.groupjcnr.projecti.dao.jpa.UserJPADAO;
import se.groupjcnr.projecti.model.User;

public class test {
	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("PI");
	private static final UserDAO userDAO = new UserJPADAO(factory);
	
	public static void main(String[] args) {
		User u = new User("ninja1", "alfons1", "karate1");
		userDAO.save(u);
		System.out.println("saved");
		
		List<User> users = userDAO.getAll();
		System.out.println(users.get(0));
	}
}
