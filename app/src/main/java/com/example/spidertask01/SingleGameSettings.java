package com.example.spidertask01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SingleGameSettings extends AppCompatActivity {

    EditText name,rounds;
    Button btn1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singlegame_settings_layout);

        name = findViewById(R.id.editTextAS);
        rounds = findViewById(R.id.editTextRS);
        btn1 = findViewById(R.id.bt_startS);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameA = name.getText().toString();
                String roundRemain = rounds.getText().toString();

                Intent i = new Intent(SingleGameSettings.this,SingleGame.class);
                i.putExtra("NameSS",nameA);
                i.putExtra("SSRounds",roundRemain);
                startActivity(i);
            }
        });
    }
}
