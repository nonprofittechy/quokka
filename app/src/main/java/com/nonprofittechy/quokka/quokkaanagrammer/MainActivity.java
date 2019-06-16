package com.nonprofittechy.quokka.quokkaanagrammer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private static final String[] dictionaries = {"test.txt","ospd.txt","WJ3.txt"};

    GADDAG wordlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordlist = new GADDAG();
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void loadAnagrams(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String rack = editText.getText().toString();

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);

        // Permutation permutation = new Permutation();
        // ArrayList<String> results = permutation.permute(message);

        BufferedReader reader;
        //GADDAG g = new GADDAG();

        try {
            // get input stream for text
            InputStream is = getAssets().open("ospd.txt");

            BufferedReader input = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = input.readLine()) != null) {
                wordlist.add(line);
            }
        }
        catch (Exception e) {
            e.getMessage();
        }

        int length = rack.length();

        HashSet<String> results = wordlist.findWordsFromString(rack, length, length);

        StringBuilder sb = new StringBuilder();

        for (String s: results) {
            sb.append(s);
            sb.append("\r\n");
        }

        textView.setText(sb.toString());

    }

    public void loadWordlist(View view) {

    }

}
