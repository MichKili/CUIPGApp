package pl.pg.cui.nbp.config;

import pl.pg.cui.nbp.App;
import pl.pg.cui.nbp.data.ExchangeRates;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ConnectionConfiguration {
    private final static int BEGIN_OFFSET = 48;

    private static StringBuilder nbpAddress =new StringBuilder("http://api.nbp.pl/api/exchangerates/rates/c/usd/");

    public static void setNbpAddress(String nbpAddress) {
        ConnectionConfiguration.nbpAddress.append(nbpAddress);
    }

    public static StringBuilder getNbpAddress() {
        return nbpAddress;
    }

    public static void getAPIConnection(URL nbpUrl) throws IOException {

        HttpURLConnection conn = (HttpURLConnection) nbpUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        conn.getResponseMessage();
        int responsecode = conn.getResponseCode();
        if (responsecode != 200) {
            throw new RuntimeException("Response code: " + responsecode + " - "+ conn.getResponseMessage());
        }

    }


    public static  void initialization() throws IOException {

        Scanner in = new Scanner(System.in);
        String tmp;
        while (!(tmp = in.nextLine()).equals("e")){

            try {
                //Check Format of date
                LocalDate ldt = LocalDate.parse(tmp, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            catch (Exception e){
                System.out.println("Wpisałeś zły format, spróbuj ponownie");
                continue;
            }

            setNbpAddress(tmp + "/" + LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            //Prepare connection to NBP API
            URL nbpUrl = new URL(getNbpAddress().toString());
            ConnectionConfiguration.getAPIConnection(nbpUrl);

            //Operate and display chosen date to console
            ExchangeRates.operateExchangeRates(nbpUrl);

            nbpAddress.delete(BEGIN_OFFSET,nbpAddress.length());
            System.out.println("\nPodaj datę w formacie YYYY-MM-DD:");
        }
    }

}
