import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionConfiguration {


    public static void getAPIConnection(URL nbpUrl) throws IOException {

        HttpURLConnection conn = (HttpURLConnection) nbpUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responsecode = conn.getResponseCode();
        if (responsecode != 200) {
            throw new RuntimeException("Response code: " + responsecode);
        }

    }


}
