package se.groupjcnr.projecti.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import se.groupjcnr.projecti.model.WorkItem;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class WorkItemMapper implements MessageBodyReader<WorkItem>, MessageBodyWriter<WorkItem> {

	private static final Gson gson = new GsonBuilder().registerTypeAdapter(WorkItem.class, new WorkItemAdapter()).create();
	
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type.isAssignableFrom(WorkItem.class);
	}

	@Override
	public long getSize(WorkItem workItem, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return 0;
	}

	@Override
	public void writeTo(WorkItem workItem, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream out)
					throws IOException, WebApplicationException {
		try(JsonWriter writer = new JsonWriter(new OutputStreamWriter(out))) {
			gson.toJson(workItem, WorkItem.class, writer);
		}
	}

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type.isAssignableFrom(WorkItem.class);
	}

	@Override
	public WorkItem readFrom(Class<WorkItem> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream in)
					throws IOException, WebApplicationException {
		return gson.fromJson(new InputStreamReader(in), type);
	}

}
