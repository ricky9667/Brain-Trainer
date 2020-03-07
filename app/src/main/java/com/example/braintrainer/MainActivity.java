package com.example.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView countDownTextView, problemTextView, scoreTextView, messageTextView;
    Button option0Button, option1Button, option2Button, option3Button, playAgainButton;

    ArrayList<Integer> options = new ArrayList<Integer>();
    boolean gameOnProgress;

    public void onGoButtonPressed(View view) {
        Button goButton = (Button) view;
        goButton.setVisibility(View.GONE);

        initViews();
        startGame();
    }

    public void initViews() {

        countDownTextView = findViewById(R.id.countDownTextView);
        problemTextView = findViewById(R.id.problemTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        messageTextView = findViewById(R.id.messageTextView);

        countDownTextView.setVisibility(View.VISIBLE);
        problemTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);

        option0Button = findViewById(R.id.option1Button);
        option1Button = findViewById(R.id.option2Button);
        option2Button = findViewById(R.id.option3Button);
        option3Button = findViewById(R.id.option4Button);
        playAgainButton = findViewById(R.id.playAgainButton);

        option0Button.setVisibility(View.VISIBLE);
        option1Button.setVisibility(View.VISIBLE);
        option2Button.setVisibility(View.VISIBLE);
        option3Button.setVisibility(View.VISIBLE);

    }

    public void startGame() {

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisLeft) {
                gameOnProgress = true;
                String countDownString = Long.toString(millisLeft/1000) + "s";
                countDownTextView.setText(countDownString);
            }

            public void onFinish() {
                gameOnProgress = false;
                countDownTextView.setText("0s");
                endGame();
            }
        }.start();

        generateProblem();
        playAgainButton.setVisibility(View.INVISIBLE);
    }

    public void generateProblem() {

        Random rand = new Random();

        int answerIndex = rand.nextInt(4);
        int a = rand.nextInt(20) + 1;
        int b = rand.nextInt(20) + 1;

        String problemText = a + " + " + b + " = ?";
        problemTextView.setText(problemText);

        for (int i=0; i<4; i++) {

            if (i == answerIndex) {
                options.add(a+b);
            } else {
                options.add(rand.nextInt(40) + 1);
                while (options.get(i) == a+b) {
                    options.set(i, rand.nextInt(40) + 1);
                }
            }
        }

        option0Button.setText(String.valueOf(options.get(0)));
        option1Button.setText(String.valueOf(options.get(1)));
        option2Button.setText(String.valueOf(options.get(2)));
        option3Button.setText(String.valueOf(options.get(3)));
    }


    public void endGame() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
