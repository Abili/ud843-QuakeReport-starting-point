/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    EarthquakeAdapter adapter;
    RecyclerView earthquakeListView;

    private static final String SAMPLE_JSON_RESPONSE =

            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        // Create a fake list of earthquake locations.
        adapter = new EarthquakeAdapter(new ArrayList<Earthquake>(), this);
        earthquakeListView = findViewById(R.id.list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        earthquakeListView.setLayoutManager(manager);
        earthquakeListView.setAdapter(adapter);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(earthquakeListView.getContext(),
                manager.getOrientation());
        earthquakeListView.addItemDecoration(dividerItemDecoration);


        RequestAsyncTask requestAsyncTask = new RequestAsyncTask();
        requestAsyncTask.execute(SAMPLE_JSON_RESPONSE);

    }


    private class RequestAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {

        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {
            // Perform the HTTP request for earthquake data and process the response.

            // Don't perform the request if there are no URLs, or the first URL is null
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            ArrayList<Earthquake> result = Utils.fetchEarthquakeData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> data) {
            data = new ArrayList<>();
            // Clear the adapter of previous earthquake data
            data.clear();
            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                adapter = new EarthquakeAdapter(data, EarthquakeActivity.this);
                earthquakeListView.setAdapter(adapter);
            }


        }
    }
}
