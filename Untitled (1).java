package cliq;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Cliq {

    public static void main(String[] args) {

        try {
            // Replace with actual channel name and valid ZAPI key
            URL url = new URL("https://cliq.zoho.com/api/v2/channelsbyname/channel_unique_name/message?zapikey=1001.f7XXXXXXXXXXXXXXXXXXX");

            // Proper JSON string with quotes and emoji support
            String postData = "{"
                    + "\"text\": \"I'm the king\","
                    + "\"slides\": ["
                    + "  {"
                    + "    \"type\": \"table\","
                    + "    \"data\": {"
                    + "      \"headers\": [\"Sno\", \"Symbol\"],"
                    + "      \"rows\": ["
                    + "        {\"Sno\": 1, \"Symbol\": \"✅\"},"
                    + "        {\"Sno\": 2, \"Symbol\": \"❌\"}"
                    + "      ]"
                    + "    }"
                    + "  }"
                    + "]"
                    + "}";

            // Open connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setUseCaches(false);

            // Send POST data
            try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
                dos.write(postData.getBytes("UTF-8"));
            }

            // Read response
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "UTF-8"))) {

                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
