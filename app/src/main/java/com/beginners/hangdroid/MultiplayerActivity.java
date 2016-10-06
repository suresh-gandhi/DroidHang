package com.beginners.hangdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MultiplayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
    }

    public void startMultiGame(View view){
        EditText editText = (EditText) findViewById(R.id.editTextWord);

        String wordToGuess = editText.getText().toString();

        editText.setText("");

        Intent intent = new Intent(this, GameMultiActivity.class);
        intent.putExtra("WORD_IDENTIFIER", wordToGuess);
        startActivity(intent);
    }
}
