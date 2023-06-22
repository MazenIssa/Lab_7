package com.example.lab_7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CharacterListAdapter extends BaseAdapter {

    private final ArrayList<String> characterNames;

    public CharacterListAdapter(ArrayList<String> characterNames) {
        this.characterNames = characterNames;
    }

    @Override
    public int getCount() {
        return characterNames.size();
    }

    @Override
    public Object getItem(int position) {
        return characterNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_character, parent, false);
        }

        String characterName = characterNames.get(position);

        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        nameTextView.setText(characterName);

        return convertView;
    }
}
