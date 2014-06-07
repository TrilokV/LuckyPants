package com.luckypants.authors;


import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.luckypants.command.ListAllAuthorsCommand;
import com.luckypants.model.Authors;



@Path("/authors")
public class AuthorsService {

	ObjectMapper mapper = new ObjectMapper();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAuthors() {
		ListAllAuthorsCommand listAuthors = new ListAllAuthorsCommand();
		ArrayList<Authors> listA = listAuthors.execute();
		System.out.print(listA);
		return Response.status(200).entity(listA).build();
	}

}
