package quotes;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
            makeAndPrintQuote(sb.toString());
            return true;
        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public Quote makeAndPrintQuote(String quote){
        String[] result = new Gson().fromJson(quote, String[].class);
        Quote webQuote = new Quote("Ron Swanson", result[0]);
        System.out.println(webQuote.toString());
        return webQuote;
    }

}
