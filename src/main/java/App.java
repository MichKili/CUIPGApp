

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.Scanner;

public class App {

    private static StringBuilder nbpAddress =new StringBuilder("http://api.nbp.pl/api/exchangerates/rates/c/usd/");

    public static void setNbpAddress(StringBuilder nbpAddress) {
        App.nbpAddress = nbpAddress;
    }

    public static StringBuilder getNbpAddress() {
        return nbpAddress;
    }


    public static void main(String[] args) throws IOException {

        URL nbpUrl = new URL(App.getNbpAddress().toString());
        ConnectionConfiguration.getAPIConnection(nbpUrl);

        String inline = "";
        Scanner sc = new Scanner(nbpUrl.openStream());
        while(sc.hasNext())
        {
            inline+=sc.nextLine();
        }
        System.out.println("\nJSON data in string format");
        System.out.println(inline);
        sc.close();

        JSONObject object2 = new JSONObject(inline);
        System.out.println(object2.get("rates").toString());


    }

}
