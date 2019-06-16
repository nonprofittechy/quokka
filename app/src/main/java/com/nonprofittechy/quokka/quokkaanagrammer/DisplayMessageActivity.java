package com.nonprofittechy.quokka.quokkaanagrammer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);

        // Permutation permutation = new Permutation();
        // ArrayList<String> results = permutation.permute(message);

        BufferedReader reader;
        GADDAG g = new GADDAG();

        try {
            // get input stream for text
            InputStream is = getAssets().open("ospd.txt");

            BufferedReader input = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = input.readLine()) != null) {
                g.add(line);
            }
        }
        catch (Exception e) {
            e.getMessage();
        }

        int length = message.length();

        HashSet<String> results = g.findWordsFromString(message, length, length);

        StringBuilder sb = new StringBuilder();

        for (String s: results) {
            sb.append(s);
            sb.append("\r\n");
        }

         textView.setText(sb.toString());

    }
}
