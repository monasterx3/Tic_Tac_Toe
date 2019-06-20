package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Declare variables
        final Button reset = findViewById(R.id.reset);
        final TextView player1= findViewById(R.id.textView_1);
        final TextView player2= findViewById(R.id.textView2);

        final int score1;
        final int score2;

        //when reset button is clicked, the game will restart
        //and the stores will restart to 0.
        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int score1=0;
                int score2=0;
                player1.setText("Player 1: "+score1);
                player2.setText("PLayer 2: "+score2);
            }
        });    }
}
