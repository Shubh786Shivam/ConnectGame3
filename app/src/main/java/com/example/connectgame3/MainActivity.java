package com.example.connectgame3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0: Yellow 1: Red 2: Empty Box
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int activePlayer = 0;
    boolean gameactive = true;
    public void dropIn(View view){
        ImageView counter  = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gamestate[tappedCounter] == 2 && gameactive) {
            gamestate[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (gamestate[winningPosition[0]] == gamestate[winningPosition[1]] && gamestate[winningPosition[1]] == gamestate[winningPosition[2]] && gamestate[winningPosition[0]] != 2) {
                    //Someone has won!
                    gameactive = false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnertextView = (TextView) findViewById(R.id.winnertextView);
                    winnertextView.setText(winner + " has Won!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnertextView.setVisibility(View.VISIBLE);
                }
            }
        }

    }
    public void playAgain(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnertextView = (TextView) findViewById(R.id.winnertextView);

        playAgainButton.setVisibility(View.INVISIBLE);
        winnertextView.setVisibility(View.INVISIBLE);
        GridLayout boardLayout = (GridLayout) findViewById(R.id.boardLayout);
        for(int i = 0; i < boardLayout.getChildCount(); i++){
            ImageView counter = (ImageView) boardLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i = 0; i < gamestate.length; i++){
            gamestate[i] = 2;
        }
       activePlayer = 0;
       gameactive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}