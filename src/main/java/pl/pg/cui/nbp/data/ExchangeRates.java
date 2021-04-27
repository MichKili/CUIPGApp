package pl.pg.cui.nbp.data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Scanner;

public class ExchangeRates {



    private static Optional<Float> previousBid = Optional.empty();
    private static Optional<Float> previousAsk = Optional.empty();
    private static JSONObject currentObj;

    public static void displayExchangeRates() {

        if (previousAsk.isPresent() && previousBid.isPresent()) {
            System.out.println("##############################################");
            System.out.println("Różnica kupna: " + (currentObj.getFloat("bid") - previousBid.get()));
            System.out.println("Różnica sprzedaży: " + (currentObj.getFloat("ask") - previousAsk.get()));
            System.out.println("##############################################");
        }

        System.out.println("Stan na dzień: " + currentObj.get("effectiveDate"));
        System.out.println("Kurs kupna: " + currentObj.get("bid"));
        System.out.println("Kurs sprzedaży: " + currentObj.get("ask"));
    }

    public static void operateExchangeRates(URL nbpUrl) throws IOException {

        //Get data from given URL to string
        StringBuilder inline = new StringBuilder();
        Scanner sc = new Scanner(nbpUrl.openStream());
        while(sc.hasNext())
        {
            inline.append(sc.nextLine());
        }
        sc.close();

        //Convert to JsonArray to operate with multiple records from field "rates"
        JSONArray arrayResult = (JSONArray) new JSONObject(inline.toString()).get("rates");

        arrayResult.forEach(x -> {
            currentObj = (JSONObject)x;
            displayExchangeRates();
            previousBid = Optional.of(currentObj.getFloat("bid"));
            previousAsk = Optional.of(currentObj.getFloat("ask"));
        });

        previousBid = Optional.empty();
        previousAsk = Optional.empty();
    }
}
