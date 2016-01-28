package se.groupjcnr.projecti.mapper;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import se.groupjcnr.projecti.model.User;

public final class UserAdapter implements JsonSerializer<User>, JsonDeserializer<User> {

	@Override
	public JsonElement serialize(User user, Type typeOfSrc, 
			JsonSerializationContext context) {
		
		JsonObject json = MakeJson.userToJson(user);
		MakeJson.emptyIds();

		return json;
	}

	@Override
	public User deserialize(JsonElement json, Type typeOfT, 
			JsonDeserializationContext context)
			throws JsonParseException {
		
		return MakeJson.jsonToUser(json.getAsJsonObject());
	}

}
