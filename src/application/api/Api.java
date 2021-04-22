/*LOGIN SUBSYSTEM*/

package sample.Api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class Api {
    //Attributes
    private static Api INSTANCE;

    //Base URL
    private String baseURL = "https://mail.adainforma.tk/";

    //return API instance
    public static Api getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Api();
        }
        return INSTANCE;
    }


    /*---------------------------------------------------------*/
    //Post Api methods
    public Boolean sendMail(String email, String pass, Map<String,Object> settings) throws IOException, ParseException{
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("email", email);
        params.put("pass", pass);

        settings.forEach(params::put);

        HttpURLConnection conn = getConnection("send/", paramsToBytes(params));
        InputStream inputStream = getInputStream(conn);

        JSONParser jsonParser = new JSONParser();
        JSONObject response = (JSONObject) jsonParser.parse(getResponse(inputStream));

        System.out.println(response);

        return !(boolean) response.get("error");
    }


    /*-----------------------------------------------------------------*/


    private String getResponse(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder response = new StringBuilder();
        String currentLine;

        while ((currentLine = in.readLine()) != null)
            response.append(currentLine);

        in.close();

        return response.toString();
    }

    /**
     * Method for parsing POST data headers to byte array
     * @param params POST data map
     * @return byte array containing POST data
     * @throws UnsupportedEncodingException
     */
    private byte[] paramsToBytes(Map<String,Object> params) throws UnsupportedEncodingException {
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }

        return postData.toString().getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Method for generating HttpURLConnection
     * @param path String URL path
     * @param postDataBytes POST data byte array
     * @return url connection with outputstream
     * @throws IOException
     */
    private HttpURLConnection getConnection(String path, byte[] postDataBytes) throws IOException {
        URL url = new URL(baseURL + path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        return conn;
    }

    /**
     * Method for returning an inputstream of any HttpURLConnection
     * @param conn url connection with outputstream
     * @return result inputstream of URL connection
     * @throws IOException
     */
    private InputStream getInputStream(HttpURLConnection conn) throws IOException {
        int responseCode = conn.getResponseCode();

        if(200 <= responseCode && responseCode <= 299)
            return conn.getInputStream();

        return conn.getErrorStream();
    }
}
