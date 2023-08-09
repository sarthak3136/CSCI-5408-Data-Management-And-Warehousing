package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

// API Key: 784ed0d997f843a6b4404fef27299e67
// Data Extraction
public class CodeA{
    // OR Dalhousie OR Halifax OR 'Canada Education' OR Moncton OR hockey OR Fredricton OR celebration
    public void fetchAPI() throws Exception{
        String keywords = "Canada University Dalhousie Halifax Canada Education Moncton hockey Fredericton celebration";
        String apiKey = "784ed0d997f843a6b4404fef27299e67";
        String httpsUrl = "https://newsapi.org/v2/everything?q=Canada%20OR%20University%20OR%20Dalhousie%20OR%20Halifax%20OR%20%22Canada%20Education%22%20OR%20Moncton%20OR%20hockey%20OR%20Fredricton%20OR%20celebration&from=2023-03-24&apiKey=" + apiKey;

        URL myurl = new URL(httpsUrl);
        BufferedReader in = new BufferedReader(new InputStreamReader(myurl.openStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();

        CodeB codeB = new CodeB();
        codeB.dataProcessing(response);

        CodeC codeC = new CodeC();
        codeC.transformData();
    }
}

