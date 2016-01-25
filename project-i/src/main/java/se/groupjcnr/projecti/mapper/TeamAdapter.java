package se.groupjcnr.projecti.mapper;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import se.groupjcnr.projecti.model.Team;

public final class TeamAdapter implements JsonSerializer<Team>, JsonDeserializer<Team> {

	@Override
	public JsonElement serialize(Team team, Type typeOfSrc, JsonSerializationContext context) {

		JsonObject json = MakeJson.teamToJson(team);
		return json;
	}

	@Override
	public Team deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		
		return MakeJson.jsonToTeam(json.getAsJsonObject());
	}

}
