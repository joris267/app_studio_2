package com.example.joris.test;

//public void rockButtonOnClick(View v){
//        // do something when the button is clicked
//        Button button = (Button) v;
//        button.setText("I've Been Clicked!");
//        TextView myTextView=(TextView)
//        findViewById(R.id.computer);
//        myTextView.setText("You Clicked My Button!");
//        }

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class MainActivity1 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity1);
    }

    public void rockButtonOnClick(View v){
    resolveGame(0);
}

    public void paperButtonOnClick(View v){
    resolveGame(1);
    }

    public void scissorsButtonOnClick(View v){
    resolveGame(2);
    }

    public void resolveGame(int choice){
//        Get current number of games, wins etc
        TextView myTextView=(TextView)
                findViewById(R.id.game_tracker);

        TextView result_window=(TextView)
                findViewById(R.id.computer);

        String str = myTextView.getText().toString();
        str = str.replaceAll("[^0-9]+", " ");
        List numbers = Arrays.asList(str.trim().split(" "));
        int game_number = Integer.parseInt((String) numbers.get(0));
        int wins = Integer.parseInt((String) numbers.get(1));
        int losses = Integer.parseInt((String) numbers.get(2));
        int draws = Integer.parseInt((String) numbers.get(3));
        game_number += 1;


//        A little easter egg, you can play more then 100 games.
        if (game_number == 100) {
            result_window.setText("CONGRATULATIONS, YOU HAVE NO LIfE!!!!");
            myTextView.setText(String.format("you have played %d games \n %d wins, %d losses and %d draws", game_number, wins, losses, draws));
            try {
                Thread.sleep(500);                 //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
            }
        }else if(game_number == 101){
            result_window.setText("I will shut down now, you go do something");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }
            }, 1000);
        }else {

            //     Let the computer make a random choice
            Random rand = new Random();
            int computer_choice = rand.nextInt(3);
            String result;

            //      Determine the result
            if (computer_choice == choice) {
                draws += 1;
                result = "Ah we chose the same, it is a draw.";
            } else if ((choice == 0 & computer_choice == 1) | choice == 1 & computer_choice == 2 | choice == 2 & computer_choice == 0) {
                losses += 1;
                result = "Ha ha, you lose!";
            } else {
                wins += 1;
                result = "Congratulations, you win!";
            }

            //      Create hashtable to be able to convert numeric data to chosen input/
            //      I realize I could have done without this but it is a good exercise.
            Hashtable<Integer, String> result_converter
                    = new Hashtable<Integer, String>();
            result_converter.put(0, "Rock");
            result_converter.put(1, "Paper");
            result_converter.put(2, "Siccors");

            result_window.setText("You chose: " + result_converter.get(choice) + "\n and I chose: " + result_converter.get(computer_choice) + "\n" + result);

            //        Update the number of games played and score
            myTextView.setText(String.format("you have played %d games \n %d wins, %d losses and %d draws", game_number, wins, losses, draws));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
