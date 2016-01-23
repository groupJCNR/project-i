package se.groupjcnr.projecti.mapper;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import se.groupjcnr.projecti.model.Issue;
import se.groupjcnr.projecti.model.Team;
import se.groupjcnr.projecti.model.User;
import se.groupjcnr.projecti.model.WorkItem;

public final class UserAdapter implements JsonSerializer<User>, JsonDeserializer<User> {

	public JsonObject userToJson(User user) {
		
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
	
	public JsonObject teamToJson(Team team) {
		
		JsonObject json = new JsonObject();
		json.addProperty("id", team.getId());
		json.addProperty("name"	, team.getName());
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
	
	public JsonObject workItemToJson(WorkItem workItem) {
		
		JsonObject json = new JsonObject();
		json.addProperty("id", workItem.getId());
		json.addProperty("title", workItem.getTitle());
		json.addProperty("description", workItem.getDescription());
		json.addProperty("priority", workItem.getPriority());
		
		JsonArray issues = new JsonArray();
		workItem.getIssues().forEach(i -> {
			JsonObject issue = issueToJson(i);
			issues.add(issue);
		});
		
		json.add("issues", issues);
		
		JsonObject user = userToJson(workItem.getUser());
		json.add("user", user);
		
		JsonObject team = teamToJson(workItem.getTeam());
		json.add("team", team);
		
		json.addProperty("status", workItem.getStatus().toString());
		
		return json;
	}
	
	public JsonObject issueToJson(Issue issue) {
		
		JsonObject json = new JsonObject();
		json.addProperty("id", issue.getId());
		json.addProperty("title", issue.getTitle());
		json.addProperty("status", issue.getStatus().toString());
		
		return json;
	}
	
	@Override
	public JsonElement serialize(User user, Type typeOfSrc, 
			JsonSerializationContext context) {
		
		JsonObject json = userToJson(user);
		
		return json;
	}

	@Override
	public User deserialize(JsonElement json, Type typeOfT, 
			JsonDeserializationContext context) throws JsonParseException {
		
		JsonObject userJson = json.getAsJsonObject();
		
		return new User("Username", "Firstname", "Lastname");
	}

}
