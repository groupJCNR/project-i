package se.groupjcnr.projecti.mapper;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import se.groupjcnr.projecti.model.Issue;
import se.groupjcnr.projecti.model.Team;
import se.groupjcnr.projecti.model.User;
import se.groupjcnr.projecti.model.WorkItem;

public final class MakeJson {

	public static JsonObject userToJson(User user) {

		JsonObject json = new JsonObject();
		json.addProperty("id", user.getId());
		json.addProperty("firstname", user.getFirstName());
		json.addProperty("lastname", user.getLastName());
		json.addProperty("status", user.getStatus().toString());
		json.addProperty("username", user.getUsername());
		json.addProperty("userid", user.getUserId());

		JsonArray teams = new JsonArray();
		user.getTeams().forEach(t -> {
			JsonObject team = teamToJson(t);
			teams.add(team);
		});

		json.add("teams", teams);

		JsonArray workItems = new JsonArray();
		user.getWorkItems().forEach(wi -> {
			JsonObject workItem = workItemToJson(wi);
			workItems.add(workItem);
		});

		json.add("workitems", workItems);

		return json;
	}

	public static JsonObject teamToJson(Team team) {

		JsonObject json = new JsonObject();
		json.addProperty("id", team.getId());
		json.addProperty("name", team.getName());
		json.addProperty("status", team.getStatus().toString());

		JsonArray workItems = new JsonArray();
		team.getWorkItems().forEach(wi -> {
			JsonObject workItem = workItemToJson(wi);
			workItems.add(workItem);
		});

		json.add("workitems", workItems);

		JsonArray users = new JsonArray();
		team.getUsers().forEach(u -> {
			JsonObject user = userToJson(u);
			users.add(user);
		});

		json.add("users", users);

		return json;
	}

	public static JsonObject workItemToJson(WorkItem workItem) {

		JsonObject json = new JsonObject();

			json.addProperty("id", workItem.getId());
			json.addProperty("title", workItem.getTitle());
			json.addProperty("description", workItem.getDescription());
			json.addProperty("priority", workItem.getPriority());

		if (workItem.getIssues().size() >= 1) {
			JsonArray issues = new JsonArray();
			workItem.getIssues().forEach(i -> {
				JsonObject issue = issueToJson(i);
				issues.add(issue);
			});
			json.add("issues", issues);
		}

		if(workItem.getUser() != null) {
		JsonObject user = userToJson(workItem.getUser());
		json.add("user", user);
		}
		
		if(workItem.getTeam() != null) {
		JsonObject team = teamToJson(workItem.getTeam());
		json.add("team", team);
		}
		
		json.addProperty("status", workItem.getStatus().toString());

		return json;
	}

	public static JsonObject issueToJson(Issue issue) {

		JsonObject json = new JsonObject();
		json.addProperty("id", issue.getId());
		json.addProperty("title", issue.getTitle());
		json.addProperty("status", issue.getStatus().toString());

		return json;
	}

	public static User jsonToUser(JsonObject json) throws JsonParseException {

		if (json.get("id") == null) {
			String username = json.get("username").getAsString();
			String firstName = json.get("firstname").getAsString();
			String lastName = json.get("lastname").getAsString();

			return new User(username, firstName, lastName);
		}

		Long id = json.get("id").getAsLong();
		String firstName = json.get("firstname").getAsString();
		String lastName = json.get("lastname").getAsString();
		String status = json.get("status").getAsString();
		String username = json.get("username").getAsString();
		String userId = json.get("userid").getAsString();

		List<Team> teams = new ArrayList<>();
		JsonArray teamsJson = json.get("teams").getAsJsonArray();
		teamsJson.forEach(t -> {
			teams.add(jsonToTeam(t.getAsJsonObject()));
		});

		List<WorkItem> workItems = new ArrayList<>();
		JsonArray workItemsJson = json.get("workitems").getAsJsonArray();
		workItemsJson.forEach(wi -> {
			workItems.add(jsonToWorkItem(wi.getAsJsonObject()));
		});

		return new User(id, firstName, lastName, User.Status.valueOf(status), username, userId, teams, workItems);
	}

	public static Team jsonToTeam(JsonObject json) throws JsonParseException {

		Long id = json.get("id").getAsLong();
		String name = json.get("name").getAsString();
		String status = json.get("status").getAsString();

		List<WorkItem> workItems = new ArrayList<>();
		JsonArray workItemsJson = json.get("workitems").getAsJsonArray();
		workItemsJson.forEach(wi -> {
			workItems.add(jsonToWorkItem(wi.getAsJsonObject()));
		});

		List<User> users = new ArrayList<>();
		JsonArray usersJson = json.get("users").getAsJsonArray();
		usersJson.forEach(u -> {
			users.add(jsonToUser(u.getAsJsonObject()));
		});

		return new Team(id, name, Team.Status.valueOf(status), workItems, users);
	}

	public static WorkItem jsonToWorkItem(JsonObject json) throws JsonParseException {

		if (json.get("id") == null) {
			String title = json.get("title").getAsString();
			String description = json.get("description").getAsString();

			return new WorkItem(title, description);
		}

		Long id = json.get("id").getAsLong();
		String title = json.get("title").getAsString();
		String descrition = json.get("description").getAsString();
		int priority = json.get("priority").getAsInt();

		List<Issue> issues = new ArrayList<>();
		JsonArray issuesJson = json.get("issues").getAsJsonArray();
		issuesJson.forEach(i -> {
			issues.add(jsonToIssue(i.getAsJsonObject()));
		});

		User user = jsonToUser(json);
		Team team = jsonToTeam(json);
		String status = json.get("status").getAsString();

		return new WorkItem(id, title, descrition, priority, issues, user, team, WorkItem.Status.valueOf(status));
	}

	public static Issue jsonToIssue(JsonObject json) throws JsonParseException {

		Long id = json.get("id").getAsLong();
		String title = json.get("title").getAsString();
		WorkItem workItem = jsonToWorkItem(json.get("workitem").getAsJsonObject());
		String status = json.get("status").getAsString();

		return new Issue(id, title, workItem, Issue.Status.valueOf(status));
	}

}
