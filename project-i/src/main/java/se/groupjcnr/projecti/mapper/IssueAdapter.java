package se.groupjcnr.projecti.mapper;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import se.groupjcnr.projecti.model.Issue;

public final class IssueAdapter implements JsonSerializer<Issue>, JsonDeserializer<Issue>  {

	@Override
	public JsonElement serialize(Issue issue, Type type, JsonSerializationContext context) {
		
		JsonObject json = MakeJson.issueToJson(issue);
		MakeJson.emptyIds();
		
		return json;
	}
	
	@Override
	public Issue deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
		return MakeJson.jsonToIssue(json.getAsJsonObject());
	}
}
