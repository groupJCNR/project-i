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

import se.groupjcnr.projecti.model.Team;
import se.groupjcnr.projecti.model.Issue;

import se.groupjcnr.projecti.model.User;
import se.groupjcnr.projecti.resource.dao.TeamDAO;
import se.groupjcnr.projecti.model.WorkItem;
import se.groupjcnr.projecti.resource.dao.IssueDAO;
import se.groupjcnr.projecti.resource.dao.UserDAO;
import se.groupjcnr.projecti.resource.dao.jpa.TeamJPADAO;
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
	private static final TeamDAO teamDAO = new TeamJPADAO(factory);
	private static final IssueDAO issueDAO = new IssueJPADAO(factory);
	private static final WorkItemDAO workItemDAO = new WorkItemJPADAO(factory);

	@Context
	private UriInfo uriInfo;

	@Context
	private HttpHeaders headers;

	@GET
	@Path("user")
	public Response getUsers() {

		GenericEntity<Collection<User>> result = new GenericEntity<Collection<User>>(userDAO.getAll()) {
		};

		if (result.getEntity().isEmpty()) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		return Response.ok(result).build();
	}

	@GET
	@Path("user/{id}")
	public Response getUserById(@PathParam("id") Long id) {
		User user = userDAO.findById(id);
		if (user != null && !user.getStatus().equals(User.Status.REMOVED)) {
			return Response.ok(userDAO.findById(id)).build();
		}

		return Response.status(Status.BAD_REQUEST).build();
	}

	@POST
	@Path("user")
	public Response createUser(User user) {

		user = userDAO.save(user);
		URI location = uriInfo.getAbsolutePathBuilder().path(user.getId().toString()).build();

		return Response.created(location).build();
	}

	@GET
	@Path("user/byname/{name}")
	public Response getUserByName(@PathParam("name") String name) {

		User temp = userDAO.getUserByFirstName(name);
		if (temp != null && temp.getFirstName().equals(name)) {
			return Response.ok(userDAO.getUserByFirstName(name)).build();
		}

		temp = userDAO.getUserByLastName(name);
		if (temp != null && userDAO.getUserByLastName(name).getLastName().equals(name)) {
			return Response.ok(userDAO.getUserByLastName(name)).build();
		}

		temp = userDAO.getUserByUsername(name);
		if (temp != null && userDAO.getUserByUsername(name).getUsername().equals(name)) {
			return Response.ok(userDAO.getUserByUsername(name)).build();
		}

		return Response.status(Status.BAD_REQUEST).build();
	}

	@GET
	@Path("workitem")
	public Response getWorkItems() {
		GenericEntity<Collection<WorkItem>> result = new GenericEntity<Collection<WorkItem>>(workItemDAO.getAll()) {};

		return Response.ok(result).build();
	}

	@POST
	@Path("team")
	public Response createTeam(Team team) {
		team = teamDAO.save(team);
		URI location = uriInfo.getAbsolutePathBuilder().path(team.getId().toString()).build();
		return Response.created(location).build();
	}

	@GET
	@Path("workitem/{id}")
	public Response getWorkItem(@PathParam("id") Long id) {
		WorkItem workItem = workItemDAO.findById(id);
		if (workItem != null && !workItem.getStatus().equals(WorkItem.Status.REMOVED)) {
			return Response.ok(workItemDAO.findById(id)).build();
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
	public Response getIssueById(@PathParam("id") Long id) {
		Issue issue = issueDAO.findById(id);
		if (issue != null && !issue.getStatus().equals(Issue.Status.REMOVED)) {
			return Response.ok(issueDAO.findById(id)).build();
		}
		
		return Response.status(Status.BAD_REQUEST).build();
	}

	@PUT
	@Path("workitem/{workitemid}/issue/{issueid}")
	public Issue mapIssueToWorkItem(@PathParam("workitemid") Long workItemId, @PathParam("issueid") Long issueId) {
		WorkItem workItem = workItemDAO.findById(workItemId);
		Issue issue = issueDAO.findById(issueId);
		workItem = workItem.addIssue(issue);
		workItem = workItemDAO.save(workItem);
		issue = issue.setWorkItem(workItem);

		return issueDAO.save(issue);
	}
	
	@POST
	@Path("issue")
	public Response createIssue(Issue issue) {
		issue = issueDAO.save(issue);
		URI location = uriInfo.getAbsolutePathBuilder().path(issue.getId().toString()).build();
		return Response.created(location).build();
	}

	@PUT
	@Path("user/{id}")
	public User updateUser(@PathParam("id") Long id, User user) {
		User temp = userDAO.findById(id);

		temp.setFirstName(user.getFirstName());
		temp.setLastName(user.getLastName());
		temp.setUsername(user.getUsername());
		temp.setTeams(user.getTeams());
		temp.setWorkItems(user.getWorkItems());
		temp.setStatus(user.getStatus());
		userDAO.save(temp);

		return userDAO.findById(id);
	}

	@PUT
	@Path("issue/{id}")
	public Issue updateIssue(@PathParam("id") Long id, Issue issue) {
		Issue temp = issueDAO.findById(id);
		temp.setTitle(issue.getTitle());
		temp.setWorkItem(issue.getWorkItem());
		temp.setStatus(issue.getStatus());

		return issueDAO.save(temp);
	}

	@PUT
	@Path("team/{id}")
	public Team updateTeam(@PathParam("id") Long id, Team team) {
		Team temp = teamDAO.findById(id);
		temp.setName(team.getName());
		temp.setItemList(team.getWorkItems());
		temp.setUserList(team.getUsers());
		temp.setStatus(team.getStatus());
		teamDAO.save(temp);

		return teamDAO.findById(id);
	}

	@PUT
	@Path("workitem/{id}")
	public WorkItem updateWorkItem(@PathParam("id") Long id, WorkItem workItem) {
		WorkItem temp = workItemDAO.findById(id);
		temp.setTitle(workItem.getTitle());
		temp.setDescription(workItem.getDescription());
		temp.setPriority(workItem.getPriority());
		temp.setIssues(workItem.getIssues());
		temp.setUser(workItem.getUser());
		temp.setTeam(workItem.getTeam());
		temp.setStatus(workItem.getStatus());
		return workItemDAO.save(temp);
	}

	@PUT
	@Path("workitem/{workitemid}/user/{userid}")
	public WorkItem addWorkItemToUser(@PathParam("workitemid") Long workItemId, @PathParam("userid") Long userId) {
		WorkItem workItem = workItemDAO.findById(workItemId);
		User user = userDAO.findById(userId);
		user = userDAO.save(user.addWorkItem(workItem));
		return workItemDAO.save(workItem.setUser(user));
	}

	@GET
	@Path("workitem/{id}/bystatus/{status}")
	public WorkItem getWorkItemsByStatus(@PathParam("id") Long id, @PathParam("status") WorkItem.Status status) {
		return workItemDAO.getWorkItemByStatus(status);
	}

	@GET
	@Path("workitem/{id}/byteam/{team}")
	public Response getWorkItemsByTeam(@PathParam("id") Long id, @PathParam("team") Team team) {
		GenericEntity<Collection<WorkItem>> result = new GenericEntity<Collection<WorkItem>>(
				workItemDAO.getWorkItemsByTeam(team)) {
		};
		return Response.ok(result).build();
	}

	@GET
	@Path("workitem/getbyuser/{id}")
	public Response getWorkItemsByUser(@PathParam("id") Long id) {
		GenericEntity<Collection<WorkItem>> result = new GenericEntity<Collection<WorkItem>>(
				workItemDAO.getWorkItemsByUser(userDAO.findById(id))) {
		};
		return Response.ok(result).build();
	}

	@DELETE
	@Path("user/{id}")
	public Response deactivateUser(@PathParam("id") Long id) {
		User temp = userDAO.findById(id);
		temp.setStatus(User.Status.REMOVED);
		userDAO.save(temp);
		
		if (userDAO.findById(id).getStatus().equals(User.Status.REMOVED)) {
			return Response.ok(temp).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	@DELETE
	@Path("team/{id}")
	public Response deactivateTeam(@PathParam("id") Long id) {
		Team temp = teamDAO.findById(id);
		temp.setStatus(Team.Status.REMOVED);
		teamDAO.save(temp);
		
		if (teamDAO.findById(id).getStatus().equals(Team.Status.REMOVED)) {
			return Response.ok(temp).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	@DELETE
	@Path("workitem/{id}")
	public Response deactivateWorkItem(@PathParam("id") Long id) {
		WorkItem temp = workItemDAO.findById(id);
		temp.setStatus(WorkItem.Status.REMOVED);
		workItemDAO.save(temp);
		
		if (workItemDAO.findById(id).getStatus().equals(WorkItem.Status.REMOVED)) {
			return Response.ok(temp).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
}
