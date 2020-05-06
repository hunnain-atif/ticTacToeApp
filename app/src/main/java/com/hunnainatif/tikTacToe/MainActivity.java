package com.hunnainatif.tikTacToe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 is blue player, 1 is red player
    int currentPlayer = 0;

    boolean gameIsActive = true;

    // 2 means position does not have counter in it already
    int[] status = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningConditions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                                 {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                                 {0, 4, 8}, {2, 4, 6}};

    public void dropIn (View view) {

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (status[tappedCounter] == 2 && gameIsActive) {

            status[tappedCounter] = currentPlayer;

            counter.setTranslationY(-1000f);
            if (currentPlayer == 0) {
                counter.setImageResource(R.drawable.blue);
                currentPlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                currentPlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(500);

            for (int [] winningCondition: winningConditions) {
                if(status[winningCondition[0]] == status[winningCondition[1]] &&
                   status[winningCondition[1]] == status[winningCondition[2]] &&
                   status[winningCondition[0]] != 2) {

                    gameIsActive = false;

                    String winner = "Red";
                    if (status[winningCondition[0]] == 0) {
                        winner = "Blue";
                    }

                    // message displayed to show user who won
                    TextView winnerMessage = (TextView) findViewById(R.id.message);
                    winnerMessage.setText(winner + " has won!");

                    LinearLayout endGameDisplay = (LinearLayout) findViewById(R.id.playAgainLayout);
                    endGameDisplay.setVisibility(View.VISIBLE);

                } else {

                    boolean gameOver = true;
                    for (int moves : status) {
                        if (moves == 2) gameOver = false;
                    }

                    if (gameOver) {
                        TextView winnerMessage = (TextView) findViewById(R.id.message);
                        winnerMessage.setText("It's a draw!");

                        LinearLayout endGameDisplay = (LinearLayout) findViewById(R.id.playAgainLayout);
                        endGameDisplay.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }


    public void startGameOver (View view) {

        gameIsActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        currentPlayer = 0;

        for (int i = 0; i < status.length; i++) {
            status[i] = 2;
        }

        androidx.gridlayout.widget.GridLayout gridLayout= (androidx.gridlayout.widget.GridLayout) findViewById (R.id.gridLayout);
        for (int i = 0; i< gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
