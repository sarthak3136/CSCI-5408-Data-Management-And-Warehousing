package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeB {
    int fileCounter = 1;

    /**
     *
     * @param data
     * @throws IOException
     */
    public void dataProcessing(StringBuffer data) throws IOException {
        Pattern pattern = Pattern.compile("\"title\":\"(.*?)\".*?\"content\":\"(.*?)\"");
        Matcher matcher = pattern.matcher(data.toString());
        ArrayList<Article> articles = new ArrayList<>();

        int entries = 1;
        boolean firstTime = true;

        while (matcher.find()) {
            String title = matcher.group(1);
            String content = matcher.group(2);

            if(fileCounter == 1 && firstTime == true){
                createFile();
                firstTime = false;
            }
            else if(entries > 5){
                createFile();
                fileCounter++;
                entries = 1;
            }
            else{
                addDataToFile(System.getProperty("user.dir") + "/src/main/java/APIData/File " + fileCounter + ".txt", title, content);
                entries++;
            }
        }
    }

    /**
     *
     * @throws IOException
     */
    public void createFile() throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        String path = currentDirectory + "/src/main/java/APIData";

        // Check if directory APIData exists
        File fileChecker = new File(path);
        if(fileChecker.exists() == false)
        {
            File f1 = new File(path);
            boolean bool = f1.mkdir();
        }
        else{
            String filePath = path + "/File " + Integer.toString(fileCounter) + ".txt";
            File file = new File(filePath);
        }

    }

    /**
     *
     * @param file
     * @param title
     * @param content
     * @throws IOException
     */
    public void addDataToFile(String file, String title, String content) throws IOException {
        FileWriter writer = new FileWriter(file, true);
        writer.write("Title: " + title + "\n");
        writer.write("Content: " + content + "\n");
        writer.write("\r\n");
        writer.flush();
        writer.close();
    }
}
