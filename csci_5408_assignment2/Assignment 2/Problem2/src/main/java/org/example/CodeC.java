package org.example;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CodeC {

    /**
     *
     * @throws IOException
     */
    public void transformData() throws IOException {
        String encodedUserName = URLEncoder.encode("sarthak3136", StandardCharsets.UTF_8);
        String encodedUserPassword = URLEncoder.encode("S@rthak3136", StandardCharsets.UTF_8);

        ConnectionString connectionString = new ConnectionString("mongodb+srv://" + encodedUserName + ":" + encodedUserPassword + "@cluster0.vzovazu.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("myMongoNews");

        List<List<String>> dataToBeTransformed = readFilesAndCleanData(System.getProperty("user.dir") + "/src/main/java/APIData");

        List<Document> documents = new ArrayList<>();
        for (List<String> record : dataToBeTransformed) {
            Document doc = new Document("Title", record.get(0))
                    .append("Content", record.get(1));
            documents.add(doc);
        }

        MongoCollection<Document> collection = database.getCollection("DMWANews");
        collection.insertMany(documents);

    }

    /**
     *
     * @param folderPath
     * @return
     * @throws IOException
     */
    public List<List<String>> readFilesAndCleanData(String folderPath) throws IOException {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        List<List<String>> finalDataForDB = new ArrayList<>();

        for(File file: files){
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";

            List<String> fileCleanData = new ArrayList<>();
            String title = "";
            String content = "";

            while ((line = reader.readLine()) != null) {
                if (line.contains("Title: ")) {
                    title = cleanData(line);
                    fileCleanData = new ArrayList<>(); // create new ArrayList for each article
                    fileCleanData.add(title);
                }
                if (line.contains("Content: ")) {
                    content = cleanData(line);
                    fileCleanData.add(content);
                    finalDataForDB.add(fileCleanData);
                    title = "";
                    content = "";
                }
            }

        }
        return finalDataForDB;
    }

    /**
     *
     * @param data
     * @return
     */
    public String cleanData(String data){
        if(data.contains("Title: ")){
            data = data.replace("Title: ", "");
        }
        else if(data.contains("Content: ")){
            data = data.replace("Content: ", "");
        }
        // Replace any character in the title string that is not a letter, digit or whitespace with an empty string.
        // Basically it will remove emoticons because they don't belong to above types.
        data = data.replaceAll("[^a-zA-Z0-9\\s+]", "").trim();

        // Removes URL if any
        data = data.replaceAll("(http|https)://\\S+\\s?", "");
        return data;
    }
}
