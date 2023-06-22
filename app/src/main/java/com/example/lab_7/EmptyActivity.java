package com.example.lab_7;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        // Create the DetailsFragment and replace the FrameLayout
        if (savedInstanceState == null) {
            String characterName = getIntent().getStringExtra("name");
            DetailsFragment detailsFragment = DetailsFragment.newInstance(characterName);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, detailsFragment)
                    .commit();
        }
    }
}
