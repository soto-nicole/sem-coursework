package com.napier.sem;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

// Entry point for the application
public class App
{
    public static void main(String[] args)
    {
        // Test db setup in preparation for connecting WorldDb

        // Create a MongoDB server
        MongoClient mongoClient = new MongoClient("mongo-dbserver");
        // Get a database - will create when we use it
        MongoDatabase database = mongoClient.getDatabase("WorldDB");
        // Get a collection from the database
        MongoCollection<Document> collection = database.getCollection("TestCollection");
        // Create a document to store
        Document doc = new Document("name", "Nicole Soto")
                .append("class", "Software Engineering Methods")
                .append("year", "2024")
                .append("result", new Document("CW", 95).append("EX", 85));
        // Add document to collection
        collection.insertOne(doc);

        // Check document in collection
        Document myDoc = collection.find().first();
        System.out.println(myDoc.toJson());
    }
}