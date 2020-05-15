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

public class Game extends AppCompatActivity {

    int temp = 0;
    int round = 0, resultA = 0, resultB = 0;
    int winA = 0, winB = 0;
    String winner;
    Bundle detail;

    LinearLayout lA,lB, picA, picB;
    Button stone,paper,scissor,stone2,paper2,scissor2,bt_next;
    ImageView iv_playerA,iv_playerB;
    TextView rounds,scoreA,scoreB,playerA,playerB,result;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        lA = findViewById(R.id.layoutA);
        lB = findViewById(R.id.layoutB);
        picA = findViewById(R.id.picLayoutA);
        picB = findViewById(R.id.picLayoutB);

        stone = findViewById(R.id.bt_rock);
        paper = findViewById(R.id.bt_paper);
        scissor = findViewById(R.id.bt_scissor);
        stone2 = findViewById(R.id.bt_rock2);
        paper2 = findViewById(R.id.bt_paper2);
        scissor2 = findViewById(R.id.bt_scissor2);
        bt_next = findViewById(R.id.bt_next);
        bt_next.setVisibility(View.INVISIBLE);

        rounds = findViewById(R.id.tv_roundRemaining);
        scoreA = findViewById(R.id.tv_scoreA);
        scoreB = findViewById(R.id.tv_scoreB);
        playerA = findViewById(R.id.tv_playerA);
        playerB = findViewById(R.id.tv_playerB);
        result = findViewById(R.id.tv_result);

        iv_playerA = findViewById(R.id.imageViewA);
        iv_playerB = findViewById(R.id.imageViewB);

        detail = getIntent().getExtras();

        assert detail != null;
        String playerAName = detail.getString("playerA");
        String playerBName = detail.getString("playerB");

        SharedPreferences TEMP = this.getSharedPreferences("Temp",Context.MODE_PRIVATE);
        temp = TEMP.getInt("temp",0);

        SharedPreferences ROUND =this.getSharedPreferences("Rounds",Context.MODE_PRIVATE);
        round = ROUND.getInt("round",0);

        if(temp == 0) {
            round = Integer.parseInt(Objects.requireNonNull(detail.getString("rounds")));

            ROUND = getSharedPreferences("Rounds",MODE_PRIVATE);
            SharedPreferences.Editor editor3 = ROUND.edit();
            editor3.putInt("round",round);
            editor3.apply();
        }

        playerA.setText(playerAName);
        playerB.setText(playerBName);

        SharedPreferences PLAYERA =this.getSharedPreferences("PlayerAWins", Context.MODE_PRIVATE);
        winA = PLAYERA.getInt("winA",0);

        SharedPreferences PLAYERB =this.getSharedPreferences("PlayerBWins", Context.MODE_PRIVATE);
        winB = PLAYERB.getInt("winB",0);

        rounds.setText("Rounds Remaining: "+round);
        scoreA.setText("Score: "+winA);
        scoreB.setText("Score: "+winB);

        result.setText(playerA.getText().toString()+": Choose your option!");

        lB.setVisibility(View.INVISIBLE);

        stone.setOnClickListener(btnClickedA);
        paper.setOnClickListener(btnClickedA);
        scissor.setOnClickListener(btnClickedA);

        stone2.setOnClickListener(btnClickedB);
        paper2.setOnClickListener(btnClickedB) ;
        scissor2.setOnClickListener(btnClickedB);

    }

    public String calculate(int a,int b){
        String str = null;
        if(a == 1){
            if(b == 1){
                str = "draw";
                scoreA.setText("Score: "+winA);
                scoreB.setText("Score: "+winB);
            }
            else if(b == 2){
                str = playerB.getText().toString()+" Wins";
                winB++;

                SharedPreferences PLAYERB = getSharedPreferences("PlayerBWins",MODE_PRIVATE);
                SharedPreferences.Editor editor2 = PLAYERB.edit();
                editor2.putInt("winB",winB);
                editor2.apply();

                lB.setBackgroundColor(Color.GREEN);
                picB.setBackgroundColor(Color.GREEN);
                lA.setBackgroundColor(Color.RED);
                picA.setBackgroundColor(Color.RED);
                scoreA.setText("Score: "+winA);
                scoreB.setText("Score: "+winB);
            }
            else if(b == 3){
                str = playerA.getText().toString()+" Wins";
                winA++;

                SharedPreferences PLAYERA = getSharedPreferences("PlayerAWins",MODE_PRIVATE);
                SharedPreferences.Editor editor = PLAYERA.edit();
                editor.putInt("winA",winA);
                editor.apply();

                lB.setBackgroundColor(Color.RED);
                picB.setBackgroundColor(Color.RED);
                lA.setBackgroundColor(Color.GREEN);
                picA.setBackgroundColor(Color.GREEN);
                scoreA.setText("Score: "+winA);
                scoreB.setText("Score: "+winB);
            }
        }
        else if(a == 2){
            if(b == 1){
                str = playerA.getText().toString()+" Wins";
                winA++;

                SharedPreferences PLAYERA = getSharedPreferences("PlayerAWins",MODE_PRIVATE);
                SharedPreferences.Editor editor = PLAYERA.edit();
                editor.putInt("winA",winA);
                editor.apply();

                lB.setBackgroundColor(Color.RED);
                picB.setBackgroundColor(Color.RED);
                lA.setBackgroundColor(Color.GREEN);
                picA.setBackgroundColor(Color.GREEN);
                scoreA.setText("Score: "+winA);
                scoreB.setText("Score: "+winB);
            }
            else if(b == 2){
                str = "draw";
                scoreA.setText("Score: "+winA);
                scoreB.setText("Score: "+winB);
            }
            else if(b == 3){
                str = playerB.getText().toString()+" Wins";
                winB++;

                SharedPreferences PLAYERB = getSharedPreferences("PlayerBWins",MODE_PRIVATE);
                SharedPreferences.Editor editor2 = PLAYERB.edit();
                editor2.putInt("winB",winB);
                editor2.apply();

                lB.setBackgroundColor(Color.GREEN);
                picB.setBackgroundColor(Color.GREEN);
                lA.setBackgroundColor(Color.RED);
                picA.setBackgroundColor(Color.RED);
                scoreA.setText("Score: "+winA);
                scoreB.setText("Score: "+winB);
            }
        }
        else if(a == 3){
            if(b == 1){
                str = playerB.getText().toString()+" Wins";
                winB++;

                SharedPreferences PLAYERB = getSharedPreferences("PlayerBWins",MODE_PRIVATE);
                SharedPreferences.Editor editor2 = PLAYERB.edit();
                editor2.putInt("winB",winB);
                editor2.apply();

                lB.setBackgroundColor(Color.GREEN);
                picB.setBackgroundColor(Color.GREEN);
                lA.setBackgroundColor(Color.RED);
                picA.setBackgroundColor(Color.RED);
                scoreA.setText("Score: "+winA);
                scoreB.setText("Score: "+winB);
            }
            else if(b == 2){
                str = playerA.getText().toString()+" Wins";
                winA++;

                SharedPreferences PLAYERA = getSharedPreferences("PlayerAWins",MODE_PRIVATE);
                SharedPreferences.Editor editor = PLAYERA.edit();
                editor.putInt("winA",winA);
                editor.apply();

                lB.setBackgroundColor(Color.RED);
                picB.setBackgroundColor(Color.RED);
                lA.setBackgroundColor(Color.GREEN);
                picA.setBackgroundColor(Color.GREEN);
                scoreA.setText("Score: "+winA);
                scoreB.setText("Score: "+winB);
            }
            else if(b == 3){
                str = "draw";
                scoreA.setText("Score: "+winA);
                scoreB.setText("Score: "+winB);
            }
        }
        return str;
    }

    View.OnClickListener btnClickedA = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button btn1 = (Button) v;
            String str2 = btn1.getText().toString();
            if(str2.equals("rock")){
                paper.setEnabled(false);
                stone.setEnabled(false);
                scissor.setEnabled(false);
                iv_playerA.setImageResource(R.drawable.stone);
                resultA = 1;
                lA.setVisibility(View.INVISIBLE);
                iv_playerA.setVisibility(View.INVISIBLE);
            }
            if(str2.equals("paper")){
                paper.setEnabled(false);
                stone.setEnabled(false);
                scissor.setEnabled(false);
                iv_playerA.setImageResource(R.drawable.paper);
                resultA = 2;
                lA.setVisibility(View.INVISIBLE);
                iv_playerA.setVisibility(View.INVISIBLE);
            }
            if(str2.equals("scissor")){
                paper.setEnabled(false);
                stone.setEnabled(false);
                scissor.setEnabled(false);
                iv_playerA.setImageResource(R.drawable.scissor);
                resultA = 3;
                lA.setVisibility(View.INVISIBLE);
                iv_playerA.setVisibility(View.INVISIBLE);
            }
            lB.setVisibility(View.VISIBLE);
            result.setText(playerB.getText().toString()+": Choose your option!");
        }
    };

    View.OnClickListener btnClickedB = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button btn = (Button) v;
            String str1 = btn.getText().toString();
            if(str1.equals("rock")){
                paper2.setEnabled(false);
                stone2.setEnabled(false);
                scissor2.setEnabled(false);
                iv_playerB.setImageResource(R.drawable.stone);
                resultB = 1;
            }
            if(str1.equals("paper")){
                paper2.setEnabled(false);
                stone2.setEnabled(false);
                scissor2.setEnabled(false);
                iv_playerB.setImageResource(R.drawable.paper);
                resultB = 2;
            }
            if(str1.equals("scissor")){
                paper2.setEnabled(false);
                stone2.setEnabled(false);
                scissor2.setEnabled(false);
                iv_playerB.setImageResource(R.drawable.scissor);
                resultB = 3;
            }
            lA.setVisibility(View.VISIBLE);
            iv_playerA.setVisibility(View.VISIBLE);

            if(resultA > 0 && resultB > 0){
                winner = calculate(resultA,resultB);
                result.setText(winner);
            }

            bt_next.setVisibility(View.VISIBLE);
            temp = 1;

            SharedPreferences TEMP = getSharedPreferences("Temp",MODE_PRIVATE);
            SharedPreferences.Editor editor4 = TEMP.edit();
            editor4.putInt("temp",temp);
            editor4.apply();

            if(round > 1) {

                round--;

                SharedPreferences ROUND = getSharedPreferences("Rounds",MODE_PRIVATE);
                SharedPreferences.Editor editor3 = ROUND.edit();
                editor3.putInt("round",round);
                editor3.apply();

                rounds.setText("Rounds Remaining: "+round);

                bt_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        restartGame();
                    }
                });
            }
            else {

                rounds.setText("Rounds Remaining: 0");
                String strA;
                bt_next.setText("Exit Game");
                if(winA>winB) {
                    strA = playerA.getText().toString() + " is the Champion!";
                    strA = strA + "\nScore "+winA+"\t\t:\t\t"+winB;
                    result.setText(strA);
                }
                else if(winB>winA) {
                    strA = playerB.getText().toString() + " is the Champion!";
                    strA = strA + "\nScore "+winA+"\t\t:\t\t"+winB;
                    result.setText(strA);
                }
                else if(winA==winB) {
                    strA = "Tournament draw!";
                    strA = strA + "\nScore "+winA+"\t\t:\t\t"+winB;
                    result.setText(strA);
                }
                bt_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resetScore();
                        Intent i = new Intent(Game.this,MainActivity.class);
                        startActivity(i);
                    }
                });
            }
        }
    };

    public void restartGame(){
        finish();
        startActivity(getIntent());
    }

    public void resetScore(){

        winA = 0;
        winB = 0;
        round = 0;
        temp = 0;

        SharedPreferences PLAYERA = getSharedPreferences("PlayerAWins",MODE_PRIVATE);
        SharedPreferences.Editor editor = PLAYERA.edit();
        editor.putInt("winA",winA);
        editor.apply();

        SharedPreferences PLAYERB = getSharedPreferences("PlayerBWins",MODE_PRIVATE);
        SharedPreferences.Editor editor2 = PLAYERB.edit();
        editor2.putInt("winB",winB);
        editor2.apply();

        SharedPreferences ROUND = getSharedPreferences("rounds",MODE_PRIVATE);
        SharedPreferences.Editor editor3 = ROUND.edit();
        editor3.putInt("round",round);
        editor3.apply();

        SharedPreferences TEMP = getSharedPreferences("Temp",MODE_PRIVATE);
        SharedPreferences.Editor editor4 = TEMP.edit();
        editor4.putInt("temp",temp);
        editor4.apply();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        resetScore();
    }

}
