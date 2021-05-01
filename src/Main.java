
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.sendGet();
    }

    private void sendGet() throws Exception {

        String url = "https://jsonplaceholder.typicode.com/posts";

        HttpURLConnection httpClient =
                (HttpURLConnection) new URL(url).openConnection();

        // optional default is GET
        httpClient.setRequestMethod("GET");

        //add request header
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = httpClient.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line = "";

            while ((line = in.readLine()) != null) {
//                    if (line.trim().equals("Кыргыз Республикасынын Улуттук банкы")) {
//                        System.out.println(line.trim());
//                        break;
                response.append(line);
//                    }

                //response.append(line.trim() + "\n");
            }
            Gson gson = new Gson();
            String str = String.valueOf(response);
            List<Person> objectJson = gson.fromJson(str, new TypeToken<List<Person>>(){}.getType());
            for(Person p : objectJson){
                System.out.println(p+"\n");
            }

            //print result
            //System.out.println(response.toString());

        }

    }
}


