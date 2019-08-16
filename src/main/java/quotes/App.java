/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package quotes;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class App {
    //http://swquotesapi.digitaljedi.dk/api/SWQuote/RandomStarWarsQuote
    //https://ron-swanson-quotes.herokuapp.com/v2/quotes
    //http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en

    public static void main(String[] args){
        try{
            GetWebQuote newWebQuote = new GetWebQuote("https://ron-swanson-quotes.herokuapp.com/v2/quotes");
            newWebQuote.makeCall();
        } catch (Exception e){
            String allQuotesAsString = readFile();
            Quote[] allQuotes = storeQuotes(allQuotesAsString);
            int randomNumber = generateRandomNumber(allQuotes);
            printAuthorAndQuote(allQuotes, randomNumber);
        }
    }

    public static String readFile(){
        try{
//      Reads in Json File
        Scanner reader = new Scanner(new File("src/main/resources/recentquotes.JSON"));
        StringBuilder sb = new StringBuilder();
//      Ensures all quotes are printed
            while(reader.hasNextLine()){
                sb.append(reader.nextLine());
            }
            System.out.println(sb.toString());
            return sb.toString();
        } catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }

    public static Quote[] storeQuotes(String quotes){
//      Stores our quotes & Authors
        return new Gson().fromJson(quotes, Quote[].class);
    }

    public static int generateRandomNumber(Quote[] allQuotes){
        Random randomNumber = new Random();
        int n = randomNumber.nextInt(allQuotes.length);
        return n;
    }

    private static void printAuthorAndQuote(Quote[] allQuotes, int n){
        System.out.println(allQuotes[n]);
    }

}
