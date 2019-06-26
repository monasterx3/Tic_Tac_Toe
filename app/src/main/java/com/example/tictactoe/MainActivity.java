package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
            } else {
                Toast.makeText(getApplicationContext(), "Player 2 Wins!", Toast.LENGTH_SHORT).show();
                score2++;
                player2.setText("Player2: " + score2);

            }
        }
    }

    private boolean check3InRow() {
        String[][] cell = new String[3][3];
        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
                case 0: //top row
                    line = cell[0][0] + cell[0][1] + cell[0][2];
                    break;
                case 1: //middle row
                    line = cell[1][0] + cell[1][1] + cell[1][2];
                    break;
                case 2: //bottom row
                    line = cell[2][0] + cell[2][1] + cell[2][2];
                    break;
                case 3: //first column
                    line = cell[0][0] + cell[1][0] + cell[2][0];
                    break;
                case 4: //middle column
                    line = cell[0][1] + cell[1][1] + cell[2][1];
                    break;
                case 5: //last column
                    line = cell[0][2] + cell[1][2] + cell[2][2];
                    break;
                case 6: //forward diagonal
                    line = cell[0][0] + cell[1][1] + cell[2][2];
                    break;
                case 7: //backward diagonal
                    line = cell[2][0] + cell[1][1] + cell[0][2];
                    break;
            }
            if (line.equals("XXX")) {
                return true;
            } else if (line.equals("OOO")) {
                return false;
            }

        }
    return true; //I'm not sure if it should be true, i just had to return something
    }
}
