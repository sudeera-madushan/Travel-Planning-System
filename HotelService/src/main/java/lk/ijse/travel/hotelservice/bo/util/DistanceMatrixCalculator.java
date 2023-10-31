package lk.ijse.travel.hotelservice.bo.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author : Sudeera Madushan
 * @Date : 10/31/2023
 * @Project : Next Travel Pvt. Ltd
 */
public class DistanceMatrixCalculator {
    public static double getDistance(String src1,String src2) throws IOException {

        String origin=getOrigin(src1);
        String destination = getOrigin(src2);
        String apiKey = "AhTs1NKD6MM19FxIHxv3kseOeji1BrzSQfcutMcPm1xxa5delVftdkNjkOQRkZ0O";

        URL url = new URL("https://dev.virtualearth.net/REST/v1/Routes/DistanceMatrix?origins=" + origin + "&destinations=" + destination + "&travelMode=driving&key=" + apiKey);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        double distanceInMiles = 0;
        try {
            distanceInMiles = parseDistanceFromJson(response.toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    return distanceInMiles;
    }

    private static double parseDistanceFromJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.getJSONArray("resourceSets")
                .getJSONObject(0)
                .getJSONArray("resources")
                .getJSONObject(0)
                .getJSONArray("results")
                .getJSONObject(0)
                .getDouble("travelDistance");
    }
    private static String getOrigin(String src){
        String longitude = src.split("!2d")[1].split("!3d")[0];
        String latitude = src.split("!2d")[1].split("!3d")[1].split("!")[0];

        return latitude+","+longitude;
    }
}
