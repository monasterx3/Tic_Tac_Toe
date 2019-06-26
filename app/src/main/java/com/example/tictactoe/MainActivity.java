package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //create 2d array of 3 rows and 3 column as the layout
    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn;
    private int score1;
    private int score2;

    private Button reset;
    private TextView player1;
    private TextView player2;
    private Button howto;
    private int boardCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        howto = (Button) findViewById(R.id.howto);
        reset = (Button) findViewById(R.id.reset);
        player1 = (TextView) findViewById(R.id.textView_1);
        player2 = (TextView) findViewById(R.id.textView2);

        player1Turn = true;

        //assigning buttons to array
        buttons[0][0] = findViewById(R.id.btn_00);
        buttons[0][1] = findViewById(R.id.btn_01);
        buttons[0][2] = findViewById(R.id.btn_02);
        buttons[1][0] = findViewById(R.id.btn_10);
        buttons[1][1] = findViewById(R.id.btn_11);
        buttons[1][2] = findViewById(R.id.btn_12);
        buttons[2][0] = findViewById(R.id.btn_20);
        buttons[2][1] = findViewById(R.id.btn_21);
        buttons[2][2] = findViewById(R.id.btn_22);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setOnClickListener(this); //sets an click listener for each button
            }
        }

        //when How to play button is clicked, it will take you to a new page
        howto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHowto();
            }
        });

        //when reset button is clicked, the game will restart and scores will restart to 0.
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                score1 = 0;
                score2 = 0;
                player1.setText("Player1: " + score1);
                player2.setText("Player2: " + score2);

                //clears out board
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setText("");
                    }
                }
            }
        });
    }
    //Opens how to play page
    public void openHowto() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        boardCount++;

        if (check3InaRow()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (boardCount == 9) {
            tie();
        } else {
            player1Turn = !player1Turn;
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

    //If X wins
    private void player1Wins() {
        score1++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        pointsUpdate();
        boardReset();
    }

    //If O wins
    private void player2Wins() {
        score2++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        pointsUpdate();
        boardReset();
    }

    //If theres a tie
    private void tie() {
        Toast.makeText(this, "It's a tie!", Toast.LENGTH_SHORT).show();
        boardReset();
    }

    //Updates the points of players
    private void pointsUpdate() {
        player1.setText("Player 1: " + score1);
        player2.setText("Player 2: " + score2);
    }

    //Resets board
    private void boardReset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        boardCount = 0;
        player1Turn = true;
    }
}
