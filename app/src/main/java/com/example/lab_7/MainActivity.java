package com.example.lab_7;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CharacterListAdapter adapter;
    private ArrayList<String> characterNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        characterNames = new ArrayList<>();
        adapter = new CharacterListAdapter(characterNames);

        listView.setAdapter(adapter);

        FetchCharactersTask fetchCharactersTask = new FetchCharactersTask();
        fetchCharactersTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCharacter = characterNames.get(position);

            FrameLayout frameLayout = findViewById(R.id.frameLayout);
            if (frameLayout != null) {
                DetailsFragment detailsFragment = DetailsFragment.newInstance(selectedCharacter);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, detailsFragment)
                        .commit();
            } else {
                Intent intent = new Intent(MainActivity.this, EmptyActivity.class);
                intent.putExtra("name", selectedCharacter);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private class FetchCharactersTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://swapi.dev/api/people/?format=json");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                reader.close();

                return stringBuilder.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            if (response != null) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray results = jsonObject.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject character = results.getJSONObject(i);
                        String name = character.getString("name");
                        characterNames.add(name);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
