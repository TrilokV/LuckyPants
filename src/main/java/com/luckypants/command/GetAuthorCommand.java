package com.luckypants.command;

import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

import com.luckypants.model.Authors;
import com.luckypants.mongo.AuthorsConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class GetAuthorCommand {
	
	ObjectMapper mapper = new ObjectMapper();

	public Authors execute(String key, String value) {
		AuthorsConnectionProvider authorsConn = new AuthorsConnectionProvider();
		DBCollection authorsCollection = authorsConn.getCollection("authors");

		BasicDBObject searchQuery = new BasicDBObject();
		if(key.equals("_id")){
			searchQuery.put(key, new ObjectId(value));
		}
		else
			searchQuery.put(key, value);

		DBCursor cursor = authorsCollection.find(searchQuery);
		DBObject authors = cursor.next();
		
		try{
			return mapper.readValue(authors.toString(), Authors.class);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		GetAuthorCommand command = new GetAuthorCommand();
		ObjectMapper mapper = new ObjectMapper();
		Authors test = command.execute("_id", "5388f078cf6bc34ab020cd83");
		try{
			System.out.print(mapper.writeValueAsString(test));
		}catch(Exception t){
			t.printStackTrace();
		}
		
	}

}
