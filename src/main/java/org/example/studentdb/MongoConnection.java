package org.example.studentdb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DB_NAME = "student_course_db";

    private static final MongoClient client = MongoClients.create(CONNECTION_STRING);

    public static MongoDatabase getDatabase() {
        return client.getDatabase(DB_NAME);
    }
}
