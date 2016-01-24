package se.groupjcnr.projecti.resource;

import java.net.URI;
import java.util.Collection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import se.groupjcnr.projecti.dao.IssueDAO;
import se.groupjcnr.projecti.dao.UserDAO;
import se.groupjcnr.projecti.dao.jpa.IssueJPADAO;
import se.groupjcnr.projecti.dao.jpa.UserJPADAO;
import se.groupjcnr.projecti.model.Issue;
import se.groupjcnr.projecti.model.Team;
import se.groupjcnr.projecti.model.User;
import se.groupjcnr.projecti.model.User.Status;
import se.groupjcnr.projecti.model.WorkItem;

@Path("project-i")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectiResource {

	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("PI");
	private static final UserDAO userDAO = new UserJPADAO(factory);
	private static final IssueDAO issueDAO = new IssueJPADAO(factory);

	static {
		User user = userDAO.save(new User("test", "user", "testuser"));
	}
	
	@Context
	private UriInfo uriInfo;
	
	@GET
	public Response getUsers() {
		GenericEntity<Collection<User>> result = new GenericEntity<Collection<User>>(userDAO.getAll()) {};
		return Response.ok(result).build();
	}

	@POST
	public Response createUser(User user) {
		User temp = new User(user.getFirstName(), user.getLastName(), user.getUsername());
		userDAO.save(temp);
		URI location = uriInfo.getAbsolutePathBuilder().path(temp.getUserId()).build();
		return Response.created(location).build();
	}

	@POST
	public Response createTeam(Team team) {
		return null;
	}

	@POST
	public Response createWorkItem(WorkItem workItem) {
		return null;
	}

	@POST
	public Response createIssue(Issue issue) {
		return null;
	}

	@PUT
	public User updateUser(User user) {
		//fulkod, metod för att ändra/ersätta user borde finnas, men var bör den ligga?
		userDAO.getUserByUserID(user.getUserId()).setFirstName(user.getFirstName());
		userDAO.getUserByUserID(user.getUserId()).setLastName(user.getLastName());
		userDAO.getUserByUserID(user.getUserId()).setUsername(user.getUsername());
		userDAO.getUserByUserID(user.getUserId()).setTeams(user.getTeams());
		userDAO.getUserByUserID(user.getUserId()).setWorkItems(user.getWorkItems());
		userDAO.getUserByUserID(user.getUserId()).setStatus(user.getStatus());
		
		return userDAO.getUserByUserID(user.getUserId());
	}

	@PUT
	public Team updateTeam(Team team) {
		return null;
	}

	@PUT
	public Issue updateIssue(Issue issue) {
		return null;
	}

	@DELETE
	public Response deactivateUser(User user) {
		userDAO.getUserByUserID(user.getUserId()).setStatus(Status.INACTIVE);
		if(userDAO.getUserByUserID(user.getUserId()).equals(Status.INACTIVE)){
			return Response.accepted().build();
		}
		return Response.status(417).build();
	}

	@DELETE
	public Response deactivateTeam(Team team) {
		return null;
	}

	@DELETE
	public Response deactivateWorkItem(WorkItem workItem) {
		return null;
	}

	@DELETE
	public Response deactivateIssu(Issue issue) {
		return null;
	}

}
