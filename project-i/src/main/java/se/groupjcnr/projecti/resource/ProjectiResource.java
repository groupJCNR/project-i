package se.groupjcnr.projecti.resource;

import java.net.URI;
import java.util.Collection;
import java.util.List;

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

	@POST
	@Path("user")
	public Response createUser(User user) {

		user = userDAO.save(user);
		URI location = uriInfo.getAbsolutePathBuilder().path(user.getId().toString()).build();

		return Response.created(location).build();
	}

	@GET
	@Path("user")
	public Response getUsers() {

		GenericEntity<Collection<User>> result = new GenericEntity<Collection<User>>(userDAO.getAll()) {
		};

		if (!result.getEntity().isEmpty()) {
			return Response.ok(result).build();
		}

		return Response.status(Status.BAD_REQUEST).build();
	}

	@GET
	@Path("user/{id}")
	public Response getUserById(@PathParam("id") Long id) {

		User user = userDAO.findById(id);

		if ((user != null) && (user.getStatus() != User.Status.REMOVED)) {
			return Response.ok(user).build();
		}

		return Response.status(Status.BAD_REQUEST).build();
	}

	@GET
	@Path("user/byuserid/{userid}")
	public Response getUserByUserId(@PathParam("userid") String userId) {

		User user = userDAO.getUserByUserID(userId);
		if ((user != null) && (user.getStatus() != User.Status.REMOVED)) {
			return Response.ok(user).build();
		}

		return Response.status(Status.BAD_REQUEST).build();
	}

	@GET
	@Path("user/byname/{name}")
	public Response getUserByName(@PathParam("name") String name) {

		User user = userDAO.getUserByFirstName(name);
		if (user != null && user.getFirstName().equals(name)) {
			return Response.ok(userDAO.getUserByFirstName(name)).build();
		}

		user = userDAO.getUserByLastName(name);
		if (user != null && userDAO.getUserByLastName(name).getLastName().equals(name)) {
			return Response.ok(userDAO.getUserByLastName(name)).build();
		}

		user = userDAO.getUserByUsername(name);
		if (user != null && userDAO.getUserByUsername(name).getUsername().equals(name)) {
			return Response.ok(userDAO.getUserByUsername(name)).build();
		}

		return Response.status(Status.BAD_REQUEST).build();
	}

	@PUT
	@Path("user/{id}")
	public Response updateUser(@PathParam("id") Long id, User userChange) {

		if (userDAO.findById(id) == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		User user = userDAO.findById(id);
		user.setId(id);
		user = user.setFirstName(userChange.getFirstName()).setLastName(userChange.getLastName())
				.setStatus(userChange.getStatus()).setUsername(userChange.getUsername())
				.setUserId(id + userChange.getUsername()).setTeams(userChange.getTeams())
				.setWorkItems(userChange.getWorkItems());

		user = userDAO.save(user);

		return Response.ok(user).build();
	}

	@PUT
	@Path("user/{userid}/team/{teamid}")
	public Response addUserToTeam(@PathParam("userid") Long userId, @PathParam("teamid") Long teamId) {

		if ((userDAO.findById(userId) == null) && (teamDAO.findById(teamId) == null)) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		User user = userDAO.findById(userId);
		Team team = teamDAO.findById(teamId);

		user = userDAO.save(user.addTeam(team));
		team = teamDAO.save(team.addUser(user));

		return Response.ok(user).build();
	}

	@GET
	@Path("user/byteam/{teamid}")
	public Response getUserByTeam(@PathParam("teamid") Long teamId) {
		Team team = teamDAO.findById(teamId);

		GenericEntity<Collection<User>> result = new GenericEntity<Collection<User>>(team.getUsers()) {
		};

		if (!result.getEntity().isEmpty()) {
			return Response.ok(result).build();
		}

		return Response.status(Status.BAD_REQUEST).build();
	}

	@DELETE
	@Path("user/{id}")
	public Response deactivateUser(@PathParam("id") Long id) {

		User user = userDAO.findById(id);
		user = user.setStatus(User.Status.REMOVED);
		user = userDAO.save(user);

		if (user.getStatus().equals(User.Status.REMOVED)) {
			return Response.ok(user).build();
		}

		return Response.status(Status.BAD_REQUEST).build();
	}

	@POST
	@Path("team")
	public Response createTeam(Team team) {

		team = teamDAO.save(team);
		URI location = uriInfo.getAbsolutePathBuilder().path(team.getId().toString()).build();

		return Response.created(location).build();
	}

	@GET
	@Path("team")
	public Response getTeams() {

		GenericEntity<Collection<Team>> result = new GenericEntity<Collection<Team>>(teamDAO.getAll()) {
		};

		if (!result.getEntity().isEmpty()) {
			return Response.ok(result).build();
		}

		return Response.status(Status.BAD_REQUEST).build();
	}

	@PUT
	@Path("team/{id}")
	public Response updateTeam(@PathParam("id") Long id, Team teamChange) {

		if (teamDAO.findById(id) == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		Team team = teamDAO.findById(id);
		team.setId(id);
		team = team.setName(teamChange.getName()).setStatus(teamChange.getStatus())
				.setWorkItems(teamChange.getWorkItems()).setUsers(teamChange.getUsers());

		team = teamDAO.save(team);

		return Response.ok(team).build();
	}

	@DELETE
	@Path("team/{id}")
	public Response deactivateTeam(@PathParam("id") Long id) {

		Team team = teamDAO.findById(id);
		team = team.setStatus(Team.Status.REMOVED);
		team = teamDAO.save(team);

		if (team.getStatus().equals(Team.Status.REMOVED)) {
			return Response.ok(team).build();
		}

		return Response.status(Status.BAD_REQUEST).build();
	}

	@GET
	@Path("workitem")
	public Response getWorkItems() {

		GenericEntity<Collection<WorkItem>> result = new GenericEntity<Collection<WorkItem>>(workItemDAO.getAll()) {
		};

		if (!result.getEntity().isEmpty()) {
			return Response.ok(result).build();
		}

		return Response.ok(result).build();
	}

	@GET
	@Path("workitem/{id}")
	public Response getWorkItem(@PathParam("id") Long id) {

		WorkItem workItem = workItemDAO.findById(id);

		if ((workItem != null) && (workItem.getStatus() != WorkItem.Status.REMOVED)) {
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

		if ((issue != null) && (issue.getStatus() != Issue.Status.REMOVED)) {
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
	@Path("issue/{id}")
	public Response updateIssue(@PathParam("id") Long id, Issue issueChange) {

		if (issueDAO.findById(id) == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		Issue issue = issueDAO.findById(id);
		issue.setId(id);
		issue = issue.setTitle(issueChange.getTitle()).setWorkItem(issueChange.getWorkItem())
				.setStatus(issueChange.getStatus());

		issue = issueDAO.save(issue);

		return Response.ok(issue).build();
	}

	@PUT
	@Path("workitem/{id}")
	public Response updateWorkItem(@PathParam("id") Long id, WorkItem workItemChange) {

		if (workItemDAO.findById(id) == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		WorkItem workItem = workItemDAO.findById(id);
		workItem.setId(id);
		workItem = workItem.setTitle(workItemChange.getTitle()).setDescription(workItemChange.getDescription())
				.setPriority(workItemChange.getPriority()).setIssues(workItemChange.getIssues())
				.setUser(workItemChange.getUser()).setTeam(workItemChange.getTeam())
				.setStatus(workItemChange.getStatus());

		workItem = workItemDAO.save(workItem);

		return Response.ok(workItem).build();
	}

	@PUT
	@Path("workitem/{workitemid}/user/{userid}")
	public Response addWorkItemToUser(@PathParam("workitemid") Long workItemId, @PathParam("userid") Long userId) {

		WorkItem workItem = workItemDAO.findById(workItemId);
		User user = userDAO.findById(userId);

		user = userDAO.save(user.addWorkItem(workItem));

		return Response.ok(workItemDAO.save(workItem.setUser(user))).build();
	}

	@GET
	@Path("workitem/bystatus/{status}")
	public Response getWorkItemsByStatus(@PathParam("status") WorkItem.Status status) {

		List<WorkItem> workItems = workItemDAO.getWorkItemByStatus(status);
		
		if(workItems != null) {
			return Response.ok(workItems).build();
		}
		
		return Response.status(Status.BAD_REQUEST).build();
	}

	@GET
	@Path("workitem/byteam/{id}")
	public Response getWorkItemsByTeam(@PathParam("team") Long id) {

		GenericEntity<Collection<WorkItem>> result = new GenericEntity<Collection<WorkItem>>(
				workItemDAO.getWorkItemsByTeam(id)) {
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
