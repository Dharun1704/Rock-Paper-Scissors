package com.example.spidertask01;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;
import java.util.Random;

public class SingleGame extends AppCompatActivity {

    int tempS = 0;
    int roundS = 0, winAS = 0, compWin = 0, scoreAS = 0, ScoreComp = 0;
    int resultAS = 0, resultComp = 0;
    String winner;

    LinearLayout lGame;
    TextView player, score, roundRem, resultS;
    Button rockS, paperS, scissorS, nextS;
    ImageView iv_playerS, iv_comp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_game_layout);

        lGame = findViewById(R.id.layoutGame);

        player = findViewById(R.id.tv_playerAS);
        score = findViewById(R.id.tv_scoreAS);
        roundRem = findViewById(R.id.tv_roundRemainingS);
        resultS = findViewById(R.id.tv_resultS);

        rockS = findViewById(R.id.bt_rockS);
        paperS = findViewById(R.id.bt_paperS);
        scissorS = findViewById(R.id.bt_scissorS);
        nextS = findViewById(R.id.bt_nextS);
        nextS.setVisibility(View.INVISIBLE);

        iv_playerS = findViewById(R.id.imageViewAS);
        iv_comp = findViewById(R.id.imageViewBS);

        Bundle detail = getIntent().getExtras();

        assert detail != null;
        String playerName = detail.getString("NameSS");
        player.setText(playerName);

        SharedPreferences TEMPS = this.getSharedPreferences("temp_S",Context.MODE_PRIVATE);
        tempS = TEMPS.getInt("tempS",0);

        SharedPreferences ROUND_S = this.getSharedPreferences("Round_S",Context.MODE_PRIVATE);
        roundS = ROUND_S.getInt("roundS",0);

        if(tempS == 0){
            roundS = Integer.parseInt(Objects.requireNonNull(detail.getString("SSRounds")));

            ROUND_S = getSharedPreferences("Round_S",MODE_PRIVATE);
            SharedPreferences.Editor editor3 = ROUND_S.edit();
            editor3.putInt("roundS",roundS);
            editor3.apply();
        }

        SharedPreferences PLAYER = this.getSharedPreferences("playerWins", Context.MODE_PRIVATE);
        winAS = PLAYER.getInt("winAS",0);

        SharedPreferences COMPWin = this.getSharedPreferences("ComputerWins",Context.MODE_PRIVATE);
        compWin = COMPWin.getInt("compWin",0);

        roundRem.setText("Rounds Remaining :"+roundS);
        score.setText("Score: "+winAS);
        resultS.setText(playerName+" Chose your options!");

        rockS.setOnClickListener(btn_Clicked);
        paperS.setOnClickListener(btn_Clicked);
        scissorS.setOnClickListener(btn_Clicked);
    }

    View.OnClickListener btn_Clicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button btn = (Button) v;
            String str = btn.getText().toString();
            if(str.equals("rock")){
                paperS.setEnabled(false);
                scissorS.setEnabled(false);
                iv_playerS.setImageResource(R.drawable.stone);
                resultAS = 1;
            }
            if(str.equals("paper")){
                rockS.setEnabled(false);
                scissorS.setEnabled(false);
                iv_playerS.setImageResource(R.drawable.paper);
                resultAS = 2;
            }
            if(str.equals("scissor")){
                paperS.setEnabled(false);
                rockS.setEnabled(false);
                iv_playerS.setImageResource(R.drawable.scissor);
                resultAS = 3;
            }

            Random random = new Random();
            int x = random.nextInt(3);
            if(x == 0){
                iv_comp.setImageResource(R.drawable.stone);
                resultComp = 1;
            }
            if(x == 1){
                iv_comp.setImageResource(R.drawable.paper);
                resultComp = 2;
            }
            if(x == 2){
                iv_comp.setImageResource(R.drawable.scissor);
                resultComp = 3;
            }

            if(resultAS > 0 && resultComp > 0){
                resultS.setVisibility(View.VISIBLE);
                winner = calculate(resultAS,resultComp);
                resultS.setText(winner);
            }

            nextS.setVisibility(View.VISIBLE);
            tempS = 1;

            SharedPreferences TEMPS = getSharedPreferences("temp_S",MODE_PRIVATE);
            SharedPreferences.Editor editor4 = TEMPS.edit();
            editor4.putInt("tempS",tempS);
            editor4.apply();

            if(roundS > 1){

                roundS--;

                SharedPreferences ROUND_S = getSharedPreferences("Round_S",MODE_PRIVATE);
                SharedPreferences.Editor editor3 = ROUND_S.edit();
                editor3.putInt("roundS",roundS);
                editor3.apply();

                roundRem.setText("Rounds Remaining: "+roundS);
                nextS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        restartGame();
                    }
                });
            }

            else {

                roundRem.setText("Rounds Remaining: 0");
                String strA;
                nextS.setText("Exit Game");
                if(winAS > compWin) {
                    strA = player.getText().toString() + " is the Champion!";
                    strA = strA + "\nScore \t"+winAS+"\t\t:\t\t"+compWin;
                    resultS.setText(strA);
                }
                else if(compWin > winAS) {
                    strA = "Computer is the Champion!";
                    strA = strA + "\nScore \t"+winAS+"\t\t:\t\t"+compWin;
                    resultS.setText(strA);
                }
                else {
                    strA = "Tournament draw!";
                    strA = strA + "\nScore \t"+winAS+"\t\t:\t\t"+compWin;
                    resultS.setText(strA);
                }
                nextS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resetGame();
                        Intent i = new Intent(SingleGame.this,MainActivity.class);
                        startActivity(i);
                    }
                });
            }
        }
    };

    public String calculate(int a,int b){
        String str = null;
        if(a == 1){
            if(b == 1){
                str = "draw";
                score.setText("Score: "+winAS);
            }
            else if(b == 2){
                str = player.getText().toString()+" Lost";
                compWin++;

                SharedPreferences COMPWin = getSharedPreferences("ComputerWins",MODE_PRIVATE);
                SharedPreferences.Editor editor2 = COMPWin.edit();
                editor2.putInt("compWin",compWin);
                editor2.apply();

                lGame.setBackgroundColor(Color.RED);
                score.setText("Score: "+winAS);
            }
            else if(b == 3){
                str = player.getText().toString()+" wins";
                winAS++;

                SharedPreferences PLAYER = getSharedPreferences("playerWins",MODE_PRIVATE);
                SharedPreferences.Editor editor = PLAYER.edit();
                editor.putInt("winAS",winAS);
                editor.apply();

                lGame.setBackgroundColor(Color.GREEN);
                score.setText("Score: "+winAS);
            }
        }
        else if(a == 2){
            if(b == 1){
                str = player.getText().toString()+" Wins";
                winAS++;

                SharedPreferences PLAYER = getSharedPreferences("playerWins",MODE_PRIVATE);
                SharedPreferences.Editor editor = PLAYER.edit();
                editor.putInt("winAS",winAS);
                editor.apply();

                lGame.setBackgroundColor(Color.GREEN);
                score.setText("Score: "+winAS);
            }
            else if(b == 2){
                str = "draw";
                score.setText("Score: "+winAS);
            }
            else if(b == 3){
                str = player.getText().toString()+" lost";
                compWin++;

                SharedPreferences COMPWin = getSharedPreferences("ComputerWins",MODE_PRIVATE);
                SharedPreferences.Editor editor2 = COMPWin.edit();
                editor2.putInt("compWin",compWin);
                editor2.apply();

                lGame.setBackgroundColor(Color.RED);
                score.setText("Score: "+winAS);
            }
        }
        else if(a == 3){
            if(b == 1){
                str = player.getText().toString()+" lost";
                compWin++;

                SharedPreferences COMPWin = getSharedPreferences("ComputerWins",MODE_PRIVATE);
                SharedPreferences.Editor editor2 = COMPWin.edit();
                editor2.putInt("compWin",compWin);
                editor2.apply();

                lGame.setBackgroundColor(Color.RED);
                score.setText("Score: "+winAS);
            }
            else if(b == 2){
                str = player.getText().toString()+" Wins";
                winAS++;

                SharedPreferences PLAYER = getSharedPreferences("playerWins",MODE_PRIVATE);
                SharedPreferences.Editor editor = PLAYER.edit();
                editor.putInt("winAS",winAS);
                editor.apply();

                lGame.setBackgroundColor(Color.GREEN);
                score.setText("Score: "+winAS);
            }
            else if(b == 3){
                str = "draw";
                score.setText("Score: "+winAS);
            }
        }
        return str;
    }

    public void restartGame(){
        finish();
        startActivity(getIntent());
    }

    public void resetGame(){
        winAS = 0;
        compWin = 0;
        roundS = 0;
        tempS = 0;

        SharedPreferences PLAYER = getSharedPreferences("playerWins",MODE_PRIVATE);
        SharedPreferences.Editor editor = PLAYER.edit();
        editor.putInt("winAS",winAS);
        editor.apply();

        SharedPreferences COMPWin = getSharedPreferences("ComputerWins",MODE_PRIVATE);
        SharedPreferences.Editor editor2 = COMPWin.edit();
        editor2.putInt("compWin",compWin);
        editor2.apply();

        SharedPreferences ROUND_S = getSharedPreferences("Round_S",MODE_PRIVATE);
        SharedPreferences.Editor editor3 = ROUND_S.edit();
        editor3.putInt("roundS",roundS);
        editor3.apply();

        SharedPreferences TEMPS = getSharedPreferences("temp_S",MODE_PRIVATE);
        SharedPreferences.Editor editor4 = TEMPS.edit();
        editor4.putInt("tempS",tempS);
        editor4.apply();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        resetGame();
    }
}
