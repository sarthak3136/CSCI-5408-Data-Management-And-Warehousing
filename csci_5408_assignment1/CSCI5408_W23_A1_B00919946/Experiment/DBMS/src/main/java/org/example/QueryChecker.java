package org.example;
import java.util.regex.*;
public class QueryChecker {

    /**
     *
     * @param command
     * @return
     */
    static boolean sqlQuery(String command){
        // Regex for CREATE Query
        if(Pattern.matches("^CREATE TABLE [A-Za-z_][A-Za-z0-9_]* \\((([A-Za-z_][A-Za-z0-9_]*)(, )?)*\\);$", command) == true)
        {
            return true;
        }
        // Regex for INSERT Query
        else if(Pattern.matches("^INSERT INTO \\w+ \\((\\w+(, )?)+\\) VALUES \\((('[^']*'|\\d+)(, )?)+\\);$", command) == true){
            return true;
        }
        // Regex for SELECT Query
        else if(Pattern.matches("^SELECT (\\w+(, )?)+ FROM \\w+;$", command) == true){
            return true;
        }
        else if(Pattern.matches("^SELECT\\s+([\\w,\\s*]+)\\s+FROM\\s+\\w+;$", command) == true){
            return true;
        }
        // Regex for UPDATE Query
        else if(Pattern.matches("^UPDATE\\s+[A-Za-z_]+\\s+SET\\s+[A-Za-z_]+\\s*=\\s*[^;]+\\s+WHERE\\s+[A-Za-z_]+\\s*=\\s*[^;]+;$", command) == true){
            return true;
        }
        // Regex for DELETE Query
        else if(Pattern.matches("^DELETE\\s+FROM\\s+\\w+\\s+WHERE\\s+.+;$", command) == true){
            return true;
        }
        return false;
    }
}
