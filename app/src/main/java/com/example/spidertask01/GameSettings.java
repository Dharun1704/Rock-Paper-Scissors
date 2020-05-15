package com.example.spidertask01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class GameSettings extends AppCompatActivity {

    EditText playerA,playerB,rounds;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_settings_layout);

        playerA = findViewById(R.id.editTextA);
        playerB = findViewById(R.id.editTextB);
        rounds = findViewById(R.id.editTextR);
        start = findViewById(R.id.bt_start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerAName = playerA.getText().toString();
                String playerBName = playerB.getText().toString();
                String Round = rounds.getText().toString();

                Intent i = new Intent(GameSettings.this, Game.class);
                i.putExtra("playerA",playerAName);
                i.putExtra("playerB",playerBName);
                i.putExtra("rounds",Round);
                startActivity(i);
            }
        });
    }
}
