package com.backend;

import com.config_accessor.ConfigAccessor;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.io.IOException;
import static com.mongodb.client.model.Filters.eq;

public class DBCon {

    private MongoCollection<Document> col;

    public DBCon (String collection) throws IOException {
        try {
            MongoClient client = MongoClients.create(ConfigAccessor.getValue("DB_HOST&PASS"));
            MongoDatabase db = client.getDatabase("hangmanDB");
            col = db.getCollection(collection);
        }
        catch (MongoException exc){
            System.err.println("Unable to connect due to an error: " + exc);
        }
    }

    public void add(Document doc){
        try {

            InsertOneResult result = col.insertOne(doc);
            System.out.println("Success! Inserted document id: " + result.getInsertedId());
        }
        catch (MongoException exc){
            System.err.println("Unable to insert due to an error: " + exc);
        }
    }

    public void remove(Document doc){
        try{
            DeleteResult result = col.deleteOne(doc);
            System.out.println("Success! Deleted document count: " + result.getDeletedCount());
        }
        catch (MongoException exc){
            System.err.println("Unable to delete due to an error: " + exc);
        }
    }

    public void update(Document doc, String fieldName, String value){
        try {
            Bson updates = Updates.set(fieldName, value);
            UpdateOptions options = new UpdateOptions().upsert(true);

            UpdateResult result = col.updateOne(doc, updates, options);
            System.out.println("Modified document count: " + result.getModifiedCount());
        }
        catch (MongoException exc){
            System.err.println("Unable to update due to an error: " + exc);
        }
    }

    public void get(String fieldName, String value){
        Bson projectionFields = Projections.fields(
                Projections.include(fieldName, value),
                Projections.excludeId());
        Document doc = col.find(eq(fieldName, value))
                .projection(projectionFields)
                .first();

        if (doc == null) {
            System.out.println("No results found.");
        } else {
            System.out.println(doc.toJson());
        }
    }

}
