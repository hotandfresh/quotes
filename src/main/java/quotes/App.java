/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package quotes;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class App {

    public static void main(String[] args){
        try{
            String quoteFromWeb = makeCall("https://ron-swanson-quotes.herokuapp.com/v2/quotes");

            //start process to add the quoteFromWeb to the file
            //read in the file and store everything as a string
            String allQuotesAsString = readFile();

            //deserialize the JSON to a format that can be used throughout
            //the format used is ArrayList<Quote>
            ArrayList<Quote> allQuotes = storeQuotes(allQuotesAsString);

            //this happens after makeCall is called,
            //but placed here so that it could also be added to the
            //list of quotes
            allQuotes.add(printWebQuote(quoteFromWeb));

            //writes the arrayList of quotes back to the file
            writeFile(allQuotes);

        } catch (Exception e){
            String allQuotesAsString = readFile();
            ArrayList<Quote> allQuotes = storeQuotes(allQuotesAsString);
            int randomNumber = generateRandomNumber(allQuotes);
            printAuthorAndQuote(allQuotes, randomNumber);
        }
    }

    //makes a call
    public static String makeCall(String url) throws IOException{
        URL webUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) webUrl.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while(line != null){
            sb.append(line);
            line = reader.readLine();
        }
        reader.close();

        return sb.toString();
    }

    public static boolean writeFile(ArrayList<Quote> allQuotes) throws IOException{
        Gson gson = new Gson();
        FileWriter fileWriter = new FileWriter(new File("src/main/resources/recentquotes.JSON"));
        //serialize the string of quotes, with the newly added quote back to a JSON file
        fileWriter.write(gson.toJson(allQuotes));
        fileWriter.close();

        return true;
    }

    //prints the quote from the API to the console
    public static Quote printWebQuote(String quote){
        Gson g = new Gson();
        //convert the quote into a string array
        String[] result = g.fromJson(quote, String[].class);

        //convert the string array into a type Quote,
        //so that it will be displayed in the format of Author: yyy Quote: yyy
        Quote webQuote = new Quote("Ron Swanson", result[0]);
        System.out.println(webQuote.toString());
        return webQuote;
    }

    //Reads in a Json file and returns it as a string
    public static String readFile(){
        try{
        Scanner reader = new Scanner(new File("src/main/resources/recentquotes.JSON"));
        StringBuilder sb = new StringBuilder();
        //Ensures all quotes are read in
            while(reader.hasNextLine()){
                sb.append(reader.nextLine());
            }
            return sb.toString();
        } catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }

    //Stores quotes & Authors as a type Quote into an arrayList of type Quote
    //This is how Gson deserializes JSON. It converts it into the requested type
    //The collection definition concept was taken from code review
    public static ArrayList<Quote> storeQuotes(String quotes){
        return new Gson().fromJson(quotes, new TypeToken<ArrayList<Quote>>(){}.getType());
    }

    //generates a random number that will be used to display a random quote
    public static int generateRandomNumber(ArrayList<Quote> allQuotes){
        Random randomNumber = new Random();
        int n = randomNumber.nextInt(allQuotes.size());
        return n;
    }

    //prints a quote based on what n is. n represents the quote's index
    private static void printAuthorAndQuote(ArrayList<Quote> allQuotes, int n){
        System.out.println(allQuotes.get(n));
    }

}
