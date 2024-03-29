package com.example.android.quakereport;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Utility class with methods to help perform the HTTP request and
 * parse the response.
 */
public final class Utils {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = Utils.class.getSimpleName();

    /**
     * Query the USGS dataset and return an {@link ArrayList<Earthquake>} object to represent a single earthquake.
     */
    public static ArrayList<Earthquake> fetchEarthquakeData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        ArrayList<Earthquake> earthquakes = extractFeatureFromJson(jsonResponse);
        // Return the {@link Event}
        return earthquakes;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }


    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return an {@link ArrayList<Earthquake>} object by parsing out information
     * about the first earthquake from the input earthquakeJSON string.
     */

    private static ArrayList<Earthquake> extractFeatureFromJson(String earthquakeJSON) {
        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        // Extract relevant fields from the JSON response and create an {@link Event} object
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(earthquakeJSON)) {
            return null;
        }

        try {
            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

            JSONObject jsonObject = new JSONObject(earthquakeJSON);
            JSONArray features = jsonObject.getJSONArray("features");
            for (int i = 0; i < features.length(); i++) {
                JSONObject object = features.getJSONObject(i);
                JSONObject properties = object.getJSONObject("properties");

                double magnitude = properties.getDouble("mag");
                String place = properties.getString("place");
//                String distance = properties.getString("distance");
                long time = properties.getLong("time");

                String url1 = properties.getString("url");
                Earthquake earthquake = new Earthquake(magnitude, place, null, time, url1);
                earthquakes.add(earthquake);

            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }
        return earthquakes;
    }

    /**
     * Returns new URL object from the given string URL.
     */



    /**
     * Return an {@link ArrayList<Earthquake>} object by parsing out information
     * about the first earthquake from the input earthquakeJSON string.
     */

}
