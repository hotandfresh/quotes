package quotes;

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

            System.out.println("sending request");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println("request came back");

            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while(line != null){
                sb.append(line);
                line = reader.readLine();
            }

            System.out.println(sb.toString());

        } catch(IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
