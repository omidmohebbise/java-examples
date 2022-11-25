package mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoConnection {
//    public Object getConnection() {
//        String connectionString = System.getProperty("mongodb.uri");
//        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
//            return mongoClient;
//            List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
//            databases.forEach(db -> System.out.println(db.toJson()));
//        }
//        throw new RuntimeException("Connection failed");
//
//
//    }
}
