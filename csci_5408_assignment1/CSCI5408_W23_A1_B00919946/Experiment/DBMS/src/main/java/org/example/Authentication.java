package org.example;

import java.io.*;
import java.util.Arrays;

public class Authentication {

    /**
     *
     * @param id
     * @param password
     * @param answer
     * @throws IOException
     */
    public void addUser(String id, String password, String answer) throws IOException {
        // Adding ID and password of users
        try{
            String currentDirectory = System.getProperty("user.dir");
            String path = currentDirectory + "/src/main/java/";
            System.out.println(path);
            File file = new File(path + "AuthenticatedUser.txt");
            BufferedReader reader;

            try{
                FileWriter writer = new FileWriter(path + "AuthenticatedUser.txt", true);
                if(file.length() == 0){
                    writer.write("id" + "  ");
                    writer.write("password" + "  ");
                    writer.write("Answer" + "  ");
                    writer.write("\r\n");
                    writer.write(id + "  ");
                    writer.write(password + "  ");
                    writer.write(answer + "  ");
                    writer.write("\r\n");
                    writer.flush();
                    System.out.println("File Created and User Added!");
                    return;
                }
                else if(file.length() != 0)
                {
                    reader = new BufferedReader(new FileReader(file));
                    String st = "";
                    while((st = reader.readLine()) != null){
                        writer.write(id + "  ");
                        writer.write(password + "  ");
                        writer.write(answer + "  ");
                        writer.write("\r\n");
                        writer.flush();
                        System.out.println("File Created and User Added!");
                        return;
                    }
                    return;
                }
                System.out.println("Something went wrong!");
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param id
     * @param password
     * @param answer
     * @return
     * @throws IOException
     */
    public boolean loginUser(String id, String password, String answer) throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        String path = currentDirectory + "/src/main/java/";
        File file = new File(path + "AuthenticatedUser.txt");
        if(file.exists() && !file.isDirectory()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String st = "";
            while ((st = reader.readLine()) != null){
                if(st.contains(id) && st.contains(password) && st.contains(answer)){
                    return true;
                }
            }
        }

        return false;
    }
}
