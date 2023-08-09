package org.example;

import java.io.*;
import java.util.*;

public class Query {

    String id;
    String path;
    String DBMSName;

    /**
     *
     * @param DBMSName
     * @param path
     * @param id
     */
    Query(String DBMSName, String path, String id)
    {
        this.DBMSName = DBMSName;
        this.path = path;
        this.id = id;
    }

    /**
     *
     * @param command
     * @throws IOException
     */
    // Create Table
    public void CREATE(String command) throws IOException {
        String[] arrOfCommand = command.split("[, ; ( )]+");
        String tableName = arrOfCommand[2];
        int lastEntryPosition = arrOfCommand.length - 1;
        File file = new File(path + "/" + tableName + ".txt");
        if(file.createNewFile() == true)
        {
            FileWriter writer = new FileWriter(path + "/" + tableName + ".txt");
            writer.write("index" + "  ");
            writer.flush();
            for(int i=3; i<=lastEntryPosition; i++){
                writer.write(arrOfCommand[i] + "  ");
                writer.flush();
            }
            writer.write("\r\n");
            writer.flush();
            writer.close();
            System.out.println("Table Successfully Created!");
        }
    }

    /**
     *
     * @param command
     * @throws IOException
     */
    // Select data from table
    public void SELECT(String command) throws IOException {
        String[] arrOfCommand = command.split("[, ;''( )]+");
        String tableName = arrOfCommand[arrOfCommand.length-1];
        ArrayList<String> arrOfCommandAsList = new ArrayList<>(Arrays.asList(arrOfCommand));
        int indexOfFROM = arrOfCommandAsList.indexOf("FROM");

        if(command.contains("*")){
            BufferedReader reader = new BufferedReader(new FileReader(path + "/" + tableName + ".txt"));
            String line = "";
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
            return;
        }
        // ArrayList of Columns to show
        ArrayList<String> columnsToShow = new ArrayList<>();
        for(int i=1; i<indexOfFROM; i++){
            columnsToShow.add(arrOfCommandAsList.get(i));
        }

        // Storing All the entries Of Table in LinkedHashMap
        ArrayList<LinkedHashMap<String, String>> entries = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path + "/" + tableName + ".txt"));
        String line = "";
        String columnsOfFile = reader.readLine();
        String[] firstLine = columnsOfFile.trim().replaceAll(" +", " ").split(" ");

        while((line = reader.readLine()) != null){
            LinkedHashMap<String, String> entry = new LinkedHashMap<>();
            line = line.trim().replaceAll(" +", " ");
            String[] arrayLine = line.split(" ");

            for(int i=0; i<firstLine.length; i++){
                entry.put(firstLine[i], arrayLine[i]);
            }
            entries.add(entry);
        }

        LinkedHashMap<String, Integer> columnsToShowAndItsIndex = new LinkedHashMap<>();
        // Retrieving the values we want to display
        for(String str: columnsToShow){
            int posOfStr = new ArrayList<String>(entries.get(0).keySet()).indexOf(str);
            columnsToShowAndItsIndex.put(str, posOfStr);
        }

        BufferedReader reader1 = new BufferedReader(new FileReader(path + "/" + tableName + ".txt"));
        String line1;
        ArrayList<String> selectedColumns = new ArrayList<>();

        while((line1 = reader1.readLine()) != null)
        {
            String str = "";
            for(Map.Entry<String, Integer> entry: columnsToShowAndItsIndex.entrySet())
            {
                str = str + line1.split("  ")[entry.getValue()] + "   ";
            }
            selectedColumns.add(str);
        }

        for(String str: selectedColumns){
            System.out.println(str);
        }
    }

    /**
     *
     * @param command
     * @throws IOException
     */
    // Insert Data into table
    public void INSERT(String command) throws IOException {
        String[] arrOfCommand = command.split("[, ;''( )]+");
        String tableName = arrOfCommand[2];
        ArrayList<String> arrOfCommandAsList = new ArrayList<>(Arrays.asList(arrOfCommand));
        int indexOfVALUES = arrOfCommandAsList.indexOf("VALUES");
        int indexOfTableName = arrOfCommandAsList.indexOf(tableName);

        // Reading Only First Line of File and Storing it in ArrayList
        BufferedReader br = new BufferedReader(new FileReader(path + "/" + tableName + ".txt"));
        String firstLine = br.readLine();
        String[] firstLineAsArray = firstLine.split("  ");
        ArrayList<String> firstLineAsArrayList = new ArrayList<>(Arrays.asList(firstLineAsArray));

        // Storing Column name and values in arrayList
        ArrayList<String> columnNameValue = new ArrayList<>();
        for(int i=indexOfTableName+1; i<indexOfVALUES; i++)
        {
            columnNameValue.add(arrOfCommandAsList.get(i));
        }
        for(int i=indexOfVALUES+1; i<=arrOfCommandAsList.size()-1; i++){
            columnNameValue.add(arrOfCommandAsList.get(i));
        }

        // Storing Associated Value in ArrayList
        String[] entry = new String[firstLineAsArrayList.size()];
        for(String str: firstLineAsArrayList){
            int indexOfStr = firstLineAsArrayList.indexOf(str);
            if(columnNameValue.contains(str)){
                int indexOfStrInColumnValue = columnNameValue.indexOf(str);
                entry[indexOfStr] = columnNameValue.get(indexOfStrInColumnValue + columnNameValue.size()/2);
            }else{
                entry[indexOfStr] = "null";
            }
        }
        // Getting the first columns of table to get the Last ID number
        BufferedReader reader = new BufferedReader(new FileReader(path + "/" + tableName + ".txt"));
        String line;
        ArrayList<String> firstColumn = new ArrayList<>();

        while((line = reader.readLine()) != null)
        {
            firstColumn.add(line.split("  ")[0]);
        }
        String lastId = firstColumn.get(firstColumn.size()-1);
        if(lastId.equals("index"))
        {
            entry[0] = Integer.toString(1);
        }
        else{
            String updatedId = Integer.toString(Integer.parseInt(lastId) + 1);
            entry[0] = updatedId;
        }

        // Adding the Entry in the file
        FileWriter writer = new FileWriter(path + "/" + tableName + ".txt", true);
        for(int i=0; i<entry.length; i++){
            writer.write(entry[i] + "  ");
            writer.flush();
        }
        writer.write("\r\n");
        writer.flush();
    }

    /**
     *
     * @param command
     * @throws IOException
     */
    // Update Data in the table
    public void UPDATE(String command) throws IOException {
        String[] arrOfCommand = command.split("[, = ;''( )]+");
        String tableName = arrOfCommand[1];
        ArrayList<String> arrOfCommandAsList = new ArrayList<>(Arrays.asList(arrOfCommand));

        int indexOfSET = arrOfCommandAsList.indexOf("SET");
        int indexOfWHERE = arrOfCommandAsList.indexOf("WHERE");

        HashMap<String, String> columnValueMap = new HashMap<>();

        for(int i=indexOfSET+1; i<indexOfWHERE-1; i=i+2){
            columnValueMap.put(arrOfCommandAsList.get(i), arrOfCommandAsList.get(i+1));
        }

        String columnAfterWhere = arrOfCommandAsList.get(arrOfCommandAsList.size()-2);
        String rowAfterWhere = arrOfCommandAsList.get(arrOfCommandAsList.size()-1);

        BufferedReader reader = new BufferedReader(new FileReader(path + "/" + tableName + ".txt"));
        String line = "";
        ArrayList<LinkedHashMap<String, String>> entries = new ArrayList<>();
        String columnsOfFile = reader.readLine();
        String[] firstLine = columnsOfFile.trim().replaceAll(" +", " ").split(" ");

        while((line = reader.readLine()) != null){
            LinkedHashMap<String, String> entry = new LinkedHashMap<>();
            line = line.trim().replaceAll(" +", " ");
            String[] arrayLine = line.split(" ");

            for(int i=0; i<firstLine.length; i++){
                entry.put(firstLine[i], arrayLine[i]);
            }
            entries.add(entry);
        }

        // Updating values in entries map itself
        for(int i=0; i<entries.size(); i++){
            if(entries.get(i).get(columnAfterWhere).equals(rowAfterWhere)){
                for(Map.Entry<String, String> map: columnValueMap.entrySet()){
                    entries.get(i).put(map.getKey(), map.getValue());
                }
            }
        }

        // Creating new ArrayList of entries values to store in file
        ArrayList<String> contentOfFile = new ArrayList<>();
        for(int i=0; i<entries.size(); i++){
            HashMap<String, String> entry = entries.get(i);
            String content = "";
            for(Map.Entry<String, String> map: entry.entrySet()){
                content = content + map.getValue() + "  ";
            }
            contentOfFile.add(content);
        }
        // Deleting content of file without deleting file
        FileWriter writer1 = new FileWriter(path + "/" + tableName + ".txt");
        PrintWriter writer = new PrintWriter(path + "/" + tableName + ".txt");
        writer.print("");
        writer.close();

        // Rewriting the file
        writer1.write(columnsOfFile);
        writer1.write("\r\n");
        for(String str: contentOfFile){
            writer1.write(str);
            writer1.write("\r\n");
            writer1.flush();
        }
    }

    /**
     * 
     * @param command
     * @throws IOException
     */
    // Delete Data in the table
    public void DELETE(String command) throws IOException {
        String[] arrOfCommand = command.split("[, ;''( )]+");
        String tableName = arrOfCommand[2];
        ArrayList<String> arrOfCommandAsList = new ArrayList<>(Arrays.asList(arrOfCommand));
        System.out.println(arrOfCommandAsList);
        BufferedReader reader = new BufferedReader(new FileReader(path + "/" + tableName + ".txt"));

        // Extracting word after =
        int indexOfEqual = arrOfCommandAsList.indexOf("=");
        int indexOfAttribute = indexOfEqual + 1;
        String word = arrOfCommandAsList.get(indexOfAttribute);

        // Extracting rows to delete
        ArrayList<String> rowsToDelete = new ArrayList<>();
        String line = "";

        while((line = reader.readLine()) != null){
            if(line.contains(word)){
                rowsToDelete.add(line);
            }
        }

        // Performing delete operation
        Scanner sc = new Scanner(new File(path + "/" + tableName + ".txt"));
        ArrayList<String> contentOfFile = new ArrayList<>();
        while(sc.hasNextLine()){
            contentOfFile.add(sc.nextLine());
        }
        for(int i=0; i<rowsToDelete.size(); i++){
            contentOfFile.remove(rowsToDelete.get(i));
        }

        FileWriter writer = new FileWriter(path + "/" + tableName + ".txt", true);
        PrintWriter writer1 = new PrintWriter(path + "/" + tableName + ".txt");
        writer1.print("");
        writer1.close();

        for(int i=0; i<contentOfFile.size(); i++){
            writer.write(contentOfFile.get(i));
            writer.write("\r\n");
            writer.flush();
        }
    }
}

// CREATE TABLE MOVIES (id, movie, actor, ratings);
// INSERT INTO MOVIES (id, movie, actor, ratings) VALUES ('crjor5', 'Creed', 'Jordan', '5');
// INSERT INTO MOVIES (movie, actor, ratings) VALUES ('Matrix', 'Reeves', 5);
// DELETE FROM MOVIES WHERE actor = Jordan;
// UPDATE MOVIES SET actor = 'Timothee', movie = 'Dune' WHERE id = 2;

