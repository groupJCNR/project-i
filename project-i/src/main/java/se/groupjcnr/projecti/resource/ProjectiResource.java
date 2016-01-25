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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import se.groupjcnr.projecti.model.Issue;
import se.groupjcnr.projecti.model.User;
import se.groupjcnr.projecti.model.WorkItem;
import se.groupjcnr.projecti.resource.dao.IssueDAO;
import se.groupjcnr.projecti.resource.dao.UserDAO;
import se.groupjcnr.projecti.resource.dao.WorkItemDAO;
import se.groupjcnr.projecti.resource.dao.jpa.IssueJPADAO;
import se.groupjcnr.projecti.resource.dao.jpa.UserJPADAO;
import se.groupjcnr.projecti.resource.dao.jpa.WorkItemJPADAO;

@Path("i")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectiResource {

	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("PI");
	private static final UserDAO userDAO = new UserJPADAO(factory);
	private static final IssueDAO issueDAO = new IssueJPADAO(factory);
	private static final WorkItemDAO workItemDAO = new WorkItemJPADAO(factory);

	@Context
	private UriInfo uriInfo;
	
	@Context 
	private HttpHeaders headers;
	
	@GET
	public Response getUsers() {
		GenericEntity<Collection<User>> result = new GenericEntity<Collection<User>>(userDAO.getAll()) {};
		return Response.ok(result).build();
	}
	
	@GET
	@Path("{id}")
	public Response getUserById(@PathParam("id")String id) {
		
		if (userDAO.findById(Long.parseLong(id)) != null) {
			return Response.ok(userDAO.findById(Long.parseLong(id))).build();
		}
		
		return Response.status(Status.BAD_REQUEST).build();
	}

	@POST
	public Response createUser(User user) {
		user = userDAO.save(user);
		URI location = uriInfo.getAbsolutePathBuilder().path(user.getId().toString()).build();
		return Response.created(location).build();
	}

//	@POST
//	public Response createTeam(Team team) {
//		return null;
//	}
//
	
	@GET
	@Path("workitem/{id}")
	public Response getWorkItem(@PathParam("id")String id) {
		
		if (workItemDAO.findById(Long.parseLong(id)) != null) {
			return Response.ok(workItemDAO.findById(Long.parseLong(id))).build();
		}
		
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	@POST
	@Path("workitem")
	public Response createWorkItem(WorkItem workItem) {
		workItem = workItemDAO.save(workItem);
		URI location = uriInfo.getAbsolutePathBuilder().path(workItem.getId().toString()).build();
		return Response.created(location).build();
	}

	
	@GET
	@Path("issue/{id}")
	public Response getIssueById(@PathParam("id") String id) {
		
		if(issueDAO.findById(Long.parseLong(id)) != null) {
			return Response.ok(issueDAO.findById(Long.parseLong(id))).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	@POST
	@Path("issue")
	public Response createIssue(Issue issue) {
		issue = issueDAO.save(issue);
		URI location = uriInfo.getAbsolutePathBuilder().path(issue.getId().toString()).build();
		return Response.created(location).build();
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

//	@PUT
//	public Team updateTeam(Team team) {
//		return null;
//	}
//
//	@PUT
//	public Issue updateIssue(Issue issue) {
//		return null;
//	}

	@DELETE
	public Response deactivateUser(User user) {
		userDAO.getUserByUserID(user.getUserId()).setStatus(User.Status.INACTIVE);
		if(userDAO.getUserByUserID(user.getUserId()).equals(User.Status.INACTIVE)){
			return Response.accepted().build();
		}
		return Response.status(417).build();
	}

//	@DELETE
//	public Response deactivateTeam(Team team) {
//		return null;
//	}
//
//	@DELETE
//	public Response deactivateWorkItem(WorkItem workItem) {
//		return null;
//	}
//
//	@DELETE
//	public Response deactivateIssu(Issue issue) {
//		return null;
//	}

}
