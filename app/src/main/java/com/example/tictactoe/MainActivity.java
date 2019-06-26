package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //create 2d array of 3 rows and 3 column as the layout
    private Button [][] buttons=new Button[3][3];
    
    private boolean player1Turn;
    private int score1;
    private int score2;

    private Button reset;
    private TextView player1;
    private TextView player2;    

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        reset=(Button)findViewById(R.id.reset);
        player1=(TextView)findViewById(R.id.textView_1);
        player2=(TextView)findViewById(R.id.textView2);
        
        player1Turn=true;
        
        //assigning buttons to array
        buttons[0][0]=findViewById(R.id.btn_00);
        buttons[0][1]=findViewById(R.id.btn_01);
        buttons[0][2]=findViewById(R.id.btn_02);
        buttons[1][0]=findViewById(R.id.btn_10);
        buttons[1][1]=findViewById(R.id.btn_11);
        buttons[1][2]=findViewById(R.id.btn_12);
        buttons[2][0]=findViewById(R.id.btn_20);
        buttons[2][1]=findViewById(R.id.btn_21);
        buttons[2][2]=findViewById(R.id.btn_22);
        
        for (int i=0; i<3; i++){
            for (int j=0; j<3;j++){
                buttons[i][j].setOnClickListener(this); //sets an click listener for each button
            }
        }        
        //when reset button is clicked, the game will restart and scores will restart to 0.
        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                score1=0;
                score2=0;
                player1.setText("Player1: "+score1);
                player2.setText("Player2: "+score2);
                
                //clears out board
                for(int i=0; i<3; i++){
                    for(int j=0; j<3; j++){
                        buttons[i][j].setText("");
                    }
                }
            }
        });    
    }
    @Override
    public void onClick(View v) {
        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        //call check3InRow method to check if a row has been created and who won
        if (check3InRow()) {
            if (player1Turn) {
                Toast.makeText(getApplicationContext(), "Player 1 Wins!", Toast.LENGTH_SHORT).show();
                score1++; //increase player 1 score
                player1.setText("Player1: " + score1);
                
                //clears out board after 3 in row 
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setText("");
                    }
                }
            } else {
                Toast.makeText(getApplicationContext(), "Player 2 Wins!", Toast.LENGTH_SHORT).show();
                score2++;
                player2.setText("Player2: " + score2);
                
                //clears out board
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setText("");
                    }
                }
            }
        }
    }

    private boolean check3InRow() {
        String[][] cell = new String[3][3];
        //for loop to check each button
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cell[i][j] = buttons[i][j].getText().toString();
            }
        }//check for rows
        for (int i = 0; i < 3; i++) {
            if (cell[i][0].equals(cell[i][1]) && cell[i][0].equals(cell[i][2]) && !cell[i][0].equals("")) {
                return true;
            }
        }//check for columns
        for (int i = 0; i < 3; i++) {
            if (cell[0][i].equals(cell[1][i]) && cell[0][i].equals(cell[2][i]) && !cell[0][i].equals("")) {
                return true;
            }
        }//check diagonal
        if (cell[0][0].equals(cell[1][1]) && cell[0][0].equals(cell[2][2])&& !cell[0][0].equals("")) {
            return true;
        }
        //check diagonal
        if (cell[0][2].equals(cell[1][1]) && cell[0][2].equals(cell[2][0])&& !cell[0][2].equals("")) {
            return true;
        }
        return false;
    }
}
