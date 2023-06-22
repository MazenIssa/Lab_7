package com.example.lab_7;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {

    private TextView nameTextView;
    private TextView heightTextView;
    private TextView massTextView;

    private String characterName;

    public static DetailsFragment newInstance(String characterName) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString("characterName", characterName);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        nameTextView = view.findViewById(R.id.nameTextView);
        heightTextView = view.findViewById(R.id.heightTextView);
        massTextView = view.findViewById(R.id.massTextView);

        Bundle args = getArguments();
        if (args != null) {
            characterName = args.getString("characterName");
        }

        updateViews();

        return view;
    }

    @SuppressLint("SetTextI18n")
    private void updateViews() {
        if (characterName != null) {
            nameTextView.setText(characterName);
            heightTextView.setText("Height: fill_me");
            massTextView.setText("Mass: fill_me");
        }
    }
}
