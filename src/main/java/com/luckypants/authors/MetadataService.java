package com.luckypants.authors;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.luckypants.model.Authors;

@Path("/metadata")
public class MetadataService {
	ObjectMapper mapper = new ObjectMapper();

	@GET
	@Path("/authors")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAuthorsMetadata() {
		Authors author = new Authors();
		String authorJSON;
		try {
			authorJSON = mapper.writeValueAsString(author);
			return Response.status(200).entity(authorJSON).build();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(500).build();
	}

}
