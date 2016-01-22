package se.groupjcnr.projecti.resource;


import java.util.List;

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
import se.groupjcnr.projecti.model.WorkItem;



@Path("project-i")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectiResource {

	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("PI");
	private static final UserDAO userDAO = new UserJPADAO(factory);
	private static final IssueDAO issueDAO = new IssueJPADAO(factory);
	
	@Context
	private UriInfo uriInfo;
	
	@POST
	public Response createUser(User user) {
		return null;
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
		return null;
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
		return null;
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
