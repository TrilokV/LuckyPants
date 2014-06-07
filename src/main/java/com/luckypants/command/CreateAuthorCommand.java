package com.luckypants.command;

import org.codehaus.jackson.map.ObjectMapper;

import com.luckypants.model.Authors;
import com.luckypants.mongo.AuthorsConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class CreateAuthorCommand {

	public String execute(Authors author) {
		AuthorsConnectionProvider conn = new AuthorsConnectionProvider();
		DBCollection collection = conn.getCollection("authors");

		ObjectMapper mapper = new ObjectMapper();
		try {
			DBObject dbObject = (DBObject) JSON.parse(mapper
					.writeValueAsString(author));
			collection.insert(dbObject);
			return dbObject.get("_id").toString();
			
		} catch (Exception e) {
			System.out.println("ERROR during mapping author to Mongo Object");
			return null;
		}
	}

	public static void main(String[] args) {
		CreateAuthorCommand create = new CreateAuthorCommand();
		Authors author = new Authors();
		author.setFname("Gula");
		author.setLname("Nurmatova");
		Object id = create.execute(author);
		if ( id!=null) {
			System.out.println("SUCCESS:Author Created:"+id);
		} else {
			System.out.println("ERROR:Failed to create author");
		}

	}
}
