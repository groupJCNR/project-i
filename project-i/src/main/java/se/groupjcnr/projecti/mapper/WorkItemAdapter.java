package se.groupjcnr.projecti.mapper;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import se.groupjcnr.projecti.model.WorkItem;

public final class WorkItemAdapter implements JsonSerializer<WorkItem>, JsonDeserializer<WorkItem>  {

	@Override
	public JsonElement serialize(WorkItem workItem, Type typeOfSrc, JsonSerializationContext context) {
		
		JsonObject json = MakeJson.workItemToJson(workItem);
		return json;
	}
	
	@Override
	public WorkItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		return MakeJson.jsonToWorkItem(json.getAsJsonObject());
	}

}
