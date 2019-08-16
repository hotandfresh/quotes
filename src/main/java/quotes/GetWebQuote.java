package quotes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

public class GetWebQuote {
    private String url;

    public GetWebQuote(String url){
        this.url = url;
    }

    public boolean makeCall(){
        try{
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while(line != null){
                sb.append(line);
                line = reader.readLine();
            }
            reader.close();
            //cache it to resources
            addToCache(sb.toString());
            makeAndPrintQuote(sb.toString());
            return true;
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public Quote makeAndPrintQuote(String quote){
        Gson g = new Gson();
        String[] result = g.fromJson(quote, String[].class);
        String testJSON = g.toJson(result);
        Quote webQuote = new Quote("Ron Swanson", result[0]);
        System.out.println(webQuote.toString());
        return webQuote;
    }

    public boolean addToCache(String quote){
        convertToJSONObject(quote);

//        JsonParser parser = new JsonParser();
//        JsonArray array = parser.parse(json).getAsJsonArray();
//        Quote text = gson.fromJson(array.get(0), Quote.class);
//        System.out.printf("Using Gson.fromJson() to get: %s", text);


        return true;
    }

    public boolean convertToJSONObject(String quote){
        //make the quote into a JSON object
        Gson gson = new Gson();
        String[] result = gson.fromJson(quote, String[].class);
        Collection collection = new ArrayList();
        collection.add(new Quote("Ron Swanson", result[0]));
        String json = gson.toJson(collection);
        System.out.println("Using Gson.toJson() on a raw collection: " + json);

        //read in file and add to it
        readFile();
        writeFile();
        return true;
    }

    public boolean readFile(){
        try{
            FileReader file = new FileReader("src/main/resources/recentquotes.JSON");
            BufferedReader br = new BufferedReader(file);
            String line = null;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
