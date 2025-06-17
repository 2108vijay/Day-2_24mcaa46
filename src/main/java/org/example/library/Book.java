package org.example.library;

import com.mongodb.client.*;
import org.bson.Document;

public class Book {

    // === Main Method ===
    public static void main(String[] args) {
        // MongoDB connection
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("SDCdb");
        MongoCollection<Document> collection = database.getCollection("Library");

        // Create books
        BookBase book1 = new FictionBook("Can't Hurt Me", "David Goggins", "123224");
        BookBase book2 = new NonFictionBook("Never Finished", "David Goggins", "5332678");
        BookBase book3 = new FictionBook("In Search of Lost Time", "Marcel Proust", "124524");
        BookBase book4 = new FictionBook("The Lord of the Rings", "J.R.R. Tolkien", "0998724");
        BookBase book5 = new FictionBook("To Kill a Mockingbird", "Harper Lee", "55756424");

        // Insert into MongoDB
        collection.insertOne(book1.toDocument());
        collection.insertOne(book2.toDocument());
        collection.insertOne(book3.toDocument());
        collection.insertOne(book4.toDocument());
        collection.insertOne(book5.toDocument());

        System.out.println("Books added to MongoDB!");

        // Retrieve and display all books
        System.out.println("\nAll books in library:");
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }

        mongoClient.close();
    }

    // === Base Book Class ===
    static class BookBase {
        protected String title;
        protected String author;
        protected String isbn;

        public BookBase(String title, String author, String isbn) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
        }

        public String getType() {
            return "Generic";
        }

        public Document toDocument() {
            return new Document("title", title)
                    .append("author", author)
                    .append("isbn", isbn)
                    .append("type", getType());
        }

        @Override
        public String toString() {
            return getType() + ": " + title + " by " + author + " (ISBN: " + isbn + ")";
        }
    }

    // === FictionBook Subclass ===
    static class FictionBook extends BookBase {
        public FictionBook(String title, String author, String isbn) {
            super(title, author, isbn);
        }

        @Override
        public String getType() {
            return "Fiction";
        }
    }

    // === NonFictionBook Subclass ===
    static class NonFictionBook extends BookBase {
        public NonFictionBook(String title, String author, String isbn) {
            super(title, author, isbn);
        }

        @Override
        public String getType() {
            return "Non-Fiction";
        }
    }
}
