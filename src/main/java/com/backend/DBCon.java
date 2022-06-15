package com.backend;

import com.config_accessor.ConfigAccessor;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;

public class DBCon {

    public static void createConnection () throws IOException {

        MongoClient client = MongoClients.create(ConfigAccessor.getValue("DB_HOST&PASS"));
        MongoDatabase db = client.getDatabase("hangmanDB");
        MongoCollection<Document> collection = db.getCollection("hangmanCollection");
        Document sampleDoc = new Document("_id", "1").append("name", "john smith");

        collection.insertOne(sampleDoc);
    }

}
