package com.luckypants.command;

import com.luckypants.mongo.BooksConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
//import com.mongodb.DBObject;
import com.mongodb.DBObject;

public class SearchBookCommand {

	public DBObject executeSearchIsbn(String isbn) {
		BooksConnectionProvider booksConn = new BooksConnectionProvider();
		DBCollection booksCollection = booksConn.getCollection();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("isbn", isbn);
		DBCursor cursor = (DBCursor) booksCollection.findOne(searchQuery);
		DBObject book = cursor.next();
		return book;
		
		//booksCollection.find(searchQuery);
	}
/*
	public boolean executeSearchTitle(String title) {
		BooksConnectionProvider booksConn = new BooksConnectionProvider();
		DBCollection booksCollection = booksConn.getCollection();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("title", title);
		DBCursor cursor = (DBCursor) booksCollection.findOne(searchQuery);
		if (cursor != null){
			return false;
		}
		return true;
	}
	
	public boolean executeSearchAuthor(String author) {
		BooksConnectionProvider booksConn = new BooksConnectionProvider();
		DBCollection booksCollection = booksConn.getCollection();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("author", author);
		DBCursor cursor = (DBCursor) booksCollection.findOne(searchQuery);
		if (cursor != null){
			return false;
		}
		return true;
		//booksCollection.find(searchQuery);
	}*/
	public static void main(String[] args) {
		/*
		 * SearchBookCommand command = new SearchBookCommand(); boolean result =
		 * command.executeSearch("h"); System.out.println(result);
		 */
	}

}
