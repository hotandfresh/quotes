package quotes;

import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetWebQuoteTest {

    @Test
    public void canMakeAPICall(){
        GetWebQuote testGetWebQuote = new GetWebQuote("https://ron-swanson-quotes.herokuapp.com/v2/quotes");
        assertTrue(testGetWebQuote.makeCall());
    }

    @Test
    public void canMakeAndPrintQuote(){
        GetWebQuote testGetWebQuote = new GetWebQuote("https://ron-swanson-quotes.herokuapp.com/v2/quotes");
        testGetWebQuote.makeCall();
        String testStr = "[Ron]";
        String[] testArr = new Gson().fromJson(testStr, String[].class);
        Quote expected = new Quote("Ron Swanson", testArr[0]);
        Quote result = testGetWebQuote.makeAndPrintQuote(testStr);

        assertEquals(expected.toString().trim(), result.toString().trim());
    }

}