//TASK-2 SATURDAY

//1st question- Create an employee collection with 6 or more documents having name,salary and age.

import java.util.ArrayList;
import org.bson.Document;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;

public class TASK1_SAT {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("Company");

        MongoCollection<Document> collection = database.getCollection("employee");
        collection.drop();

        Document document1 = new Document("name", "John")
            .append("salary", 50000)
            .append("age", 25);

        Document document2 = new Document("name", "Jane")
            .append("salary", 55000)
            .append("age", 30);

        Document document3 = new Document("name", "Tom")
            .append("salary", 60000)
            .append("age", 28);

        Document document4 = new Document("name", "Lucy")
            .append("salary", 65000)
            .append("age", 35);

        Document document5 = new Document("name", "Mark")
            .append("salary", 70000)
            .append("age", 32);

        Document document6 = new Document("name", "Nina")
            .append("salary", 75000)
            .append("age", 40);

        ArrayList<Document> documents = new ArrayList<Document>();
        documents.add(document1);
        documents.add(document2);
        documents.add(document3);
        documents.add(document4);
        documents.add(document5);
        documents.add(document6);

        collection.insertMany(documents);

        Document highestSalaryDoc = collection.find().sort(new Document("salary", -1)).first();
        if (highestSalaryDoc != null) {
            int highestSalary = highestSalaryDoc.getInteger("salary");
            FindIterable<Document> highestSalaryEmployees = collection.find(Filters.eq("salary", highestSalary));

            System.out.println("Employees with the highest salary:");
            for (Document doc : highestSalaryEmployees) {
                System.out.println(doc.toJson());
            }
        } else {
            System.out.println("No employees found");
        }

        mongoClient.close();
    }
}
