package com.luckypants.command;

import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;

import com.luckypants.model.Authors;
import com.luckypants.mongo.AuthorsConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;


public class ListAllAuthorsCommand {
	
	public ArrayList<Authors> execute() {
		AuthorsConnectionProvider authorsConn = new AuthorsConnectionProvider();
		DBCollection authorsCollection = authorsConn.getCollection("authors");

		DBCursor cursor = authorsCollection.find();

		ArrayList<Authors> authors = new ArrayList<Authors>();
		GetAuthorCommand getAuthor = new GetAuthorCommand();
		try {
			while (cursor.hasNext()) {
				Authors aut = getAuthor.execute("_id", cursor.next().get("_id").toString());
				authors.add(aut);
			}
		} finally {
			cursor.close();
		}
		return authors;
	}
	public static void main(String[] args) {
		ListAllAuthorsCommand listAuthors = new ListAllAuthorsCommand();
		ArrayList<Authors> list = listAuthors.execute();
		ObjectMapper mapper = new ObjectMapper();
		try{
			System.out.println(mapper.writeValueAsString(list));	
		}catch(Exception t){
			t.printStackTrace();
		}
		

	}

}
