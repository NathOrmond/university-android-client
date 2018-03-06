package networking;

import android.os.AsyncTask;

import com.example.user.trainclientapp.NearestStationActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Nathan on 05/03/2018.
 */

public class URLASyncTask extends AsyncTask<String, Integer, String> {

    NearestStationActivity _parent;
    String urlString = "http://zebedee.kriswelsh.com:8080/stations";
    String lat, lng;

    public URLASyncTask(NearestStationActivity parent, String lat, String lng) {
        _parent = parent;
        this.lat = lat;
        this.lng = lng;
        urlString = urlString + "?lat="+ lat + "&lng=" + lng;
    }

    @Override
    protected String doInBackground(String... strings) {

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {

            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));


            StringBuffer buffer = new StringBuffer();
            String line = "";
            while((line = reader.readLine()) != null){
                buffer.append(line);
            }
            return buffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
            try {
                if(reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public void onPostExecute(String foo) {

    }

}
