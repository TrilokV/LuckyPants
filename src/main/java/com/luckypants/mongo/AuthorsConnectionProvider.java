package com.luckypants.mongo;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.luckypants.properties.*;

public class AuthorsConnectionProvider extends PropertiesLookup {

	public DBCollection getCollection() {
		PropertiesLookup p = new PropertiesLookup();
		try {
			String mongoDbUrl = p.getProperty("mongodbURL");
			//int mongoDbPort = Integer.parseInt(p.getProperty("mongodbPORT"));
			MongoClient mongo = new MongoClient(mongoDbUrl, 10070);
			String dbName = p.getProperty("mongoDbName");
			DB db = mongo.getDB(dbName);
			if (db == null) {
				System.out.println("Could not connect to Database");
			}
			//System.out.print("trilokv1".toCharArray());
			boolean auth = db.authenticate("loki", "loki".toCharArray());
	
			if (auth == false) {
				System.out.println("Could not authenticate");
			}
			//String collName = p.getProperty("mongoDbColl");
			DBCollection authorColl = db.getCollection("authors");
			return authorColl;

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return null;

	}
 
	public static void main(String[] args) {
		AuthorsConnectionProvider authors = new AuthorsConnectionProvider();
		DBCollection authorsCollection = authors.getCollection();
		if(authorsCollection == null){
			System.out.println("ERROR:No Connection");
		}
		else{
			System.out.println("SUCCESS:Connected");
		}

	}
}
