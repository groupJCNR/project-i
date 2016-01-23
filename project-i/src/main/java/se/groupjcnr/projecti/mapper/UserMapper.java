package se.groupjcnr.projecti.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import se.groupjcnr.projecti.model.User;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class UserMapper implements MessageBodyReader<User>, MessageBodyWriter<User> {

//	private static final Gson gson = new GsonBuilder().r
	
	@Override
	public boolean isReadable(Class<?> type, Type genericType, 
			Annotation[] annotations, MediaType mediaType) {
		return false;
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, 
			Annotation[] annotations, MediaType mediaType) {
		return false;
	}

	@Override
	public User readFrom(Class<User> type, Type genericType, 
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, 
			InputStream entityStream)
					throws IOException, WebApplicationException {
		return null;
	}

	@Override
	public void writeTo(User t, Class<?> type, Type genericType, 
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, 
			OutputStream entityStream)
					throws IOException, WebApplicationException {
		
	}

	@Override
	public long getSize(User t, Class<?> type, Type genericType, 
			Annotation[] annotations, MediaType mediaType) {
		return 0;
	}

}
