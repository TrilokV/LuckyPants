package com.luckypants.mongo;

import java.net.UnknownHostException;

//import java.util.Properties;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.luckypants.properties.*;

public class BooksConnectionProvider extends PropertiesLookup {
	/**
	 * TODO:modify this method to allow passing the collection name to it
	 * @return
	 */

	
	public DBCollection getCollection() {
		PropertiesLookup p = new PropertiesLookup();
		try {
			String mongoDbUrl = p.getProperty("mongodbURL");
			int mongoDbPort = Integer.parseInt(p.getProperty("mongodbPORT"));
			MongoClient mongo = new MongoClient(mongoDbUrl, mongoDbPort);
			String dbName = p.getProperty("mongoDbName");
			DB db = mongo.getDB(dbName);
			if (db == null) {
				System.out.println("Could not connect to Database");
			}
			
			boolean auth = db.authenticate("trilokv", "trilokv".toCharArray());
			if (auth == false) {
				System.out.println("Could not authenticate");
			}

			DBCollection booksColl = db.getCollection("books");
			return booksColl;

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return null;

	}
 
	public static void main(String[] args) {
		BooksConnectionProvider books = new BooksConnectionProvider();
		DBCollection booksCollection = books.getCollection();
		if(booksCollection == null){
			System.out.println("ERROR:No Connection");
		}
		else{
			System.out.println("SUCCESS:Connected");
		}

	}

}
