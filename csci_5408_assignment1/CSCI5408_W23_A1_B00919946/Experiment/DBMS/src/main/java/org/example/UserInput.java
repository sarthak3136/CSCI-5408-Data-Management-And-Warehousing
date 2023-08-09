package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class UserInput {
    /**
     *
     * @throws IOException
     */
    static void takingUserInput() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("####################### Welcome To Your Database #######################");
        System.out.println();
        System.out.println("Press 1 to register with the DBMS system");
        System.out.println("Press 2 to login into the DBMS system");
        System.out.println("Write exit to terminate the program");

        String oneOrTwo = sc.next();

        // That is user want to register with database
        if(oneOrTwo.equals("1")){
            // Registering User
            System.out.println("Enter ID: ");
            String id = sc.next();
            System.out.println();
            System.out.println("Enter Password: ");
            String password = sc.next();
            System.out.println("What is your birth month?");
            String answer = sc.next();
            Authentication auth = new Authentication();
            auth.addUser(id, password, answer);

            // Making Directory/DBMS of the User
            String DBMSName = id + "DBMS";
            String currentDirectory = System.getProperty("user.dir");
            String path = currentDirectory + "/src/main/java/" + DBMSName;

            File fileChecker = new File(path);
            if(fileChecker.exists() == false)
            {
                File f1 = new File(path);
                boolean bool = f1.mkdir();

                if(bool == true){
                    System.out.println("Database Created Successfully");
                }
                else{
                    System.out.println("Something went wrong!");
                }
            }
        }
        else if(oneOrTwo.equals("2")){
            System.out.println("Enter ID: ");
            String id = sc.next();
            System.out.println();
            System.out.println("Enter Password: ");
            String password = sc.next();
            System.out.println("What is your birth month?");
            String answer = sc.next();

            Authentication auth = new Authentication();
            boolean loginUser = auth.loginUser(id, password, answer);

            if(loginUser == true){
                System.out.println("Hi, " + id + " you can access your database");

                String exit = "exit";
                String str = "";
                System.out.println("Write your query!");
                String DBMSName = id + "DBMS";
                String currentDirectory = System.getProperty("user.dir");
                String path = currentDirectory + "/src/main/java/" + DBMSName;

                while(!str.equals(exit)){
                    Scanner scanner = new Scanner(System.in).useDelimiter("\n");
                    String userQuery = scanner.next();
                    QueryChecker qc = new QueryChecker();
                    Query query = new Query(DBMSName, path, id);

                    boolean isValid = qc.sqlQuery(userQuery);
                    if(isValid == true && (userQuery.contains("CREATE") || userQuery.contains("create"))){
                        query.CREATE(userQuery);
                    }
                    else if(isValid == true && (userQuery.contains("INSERT") || userQuery.contains("insert"))){
                        query.INSERT(userQuery);
                    }
                    else if(isValid == true && (userQuery.contains("SELECT") || userQuery.contains("select"))){
                        query.SELECT(userQuery);
                    }
                    else if(isValid == true && (userQuery.contains("UPDATE") || userQuery.contains("update"))){
                        query.UPDATE(userQuery);
                    }
                    else if(isValid == true && (userQuery.contains("DELETE") || userQuery.contains("delete"))){
                        query.DELETE(userQuery);
                    }
                    else if(isValid == false){
                        System.out.println("Wrong Query!");
                    }
                }
            }
            else{
                System.out.println("Wrong User Credentials");
                return;
            }
        }
        else if(oneOrTwo.equals("exit")){
            System.out.println("Program has been terminated!");
        }
    }
}
