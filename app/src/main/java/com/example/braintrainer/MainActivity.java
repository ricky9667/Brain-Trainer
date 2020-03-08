package com.example.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView countDownTextView, problemTextView, scoreTextView, messageTextView;
    Button option0Button, option1Button, option2Button, option3Button, playAgainButton;
    ArrayList<Integer> options = new ArrayList<>();

    int correct, answered, answerIndex;
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

        new CountDownTimer(31000, 1000) {

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

        correct = 0;
        answered = 0;
        String scoreText = correct + "/" + answered;
        scoreTextView.setText(scoreText);

        generateProblem();
        playAgainButton.setVisibility(View.INVISIBLE);
        messageTextView.setVisibility(View.INVISIBLE);
    }

    public void generateProblem() {

        Random rand = new Random();

        options.clear();

        answerIndex = rand.nextInt(4);
        int a = rand.nextInt(20) + 1;
        int b = rand.nextInt(20) + 1;

        String problemText = a + " + " + b + " = ?";
        problemTextView.setText(problemText);

        for (int i=0; i<4; i++) {
            if (i == answerIndex) options.add(a + b);
            else {
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

    public void onOptionClicked(View view) {

        if (gameOnProgress) {
            int buttonIndex = Integer.parseInt(view.getTag().toString());

            if(answerIndex == buttonIndex) {
                correct++;
                messageTextView.setText("Correct!");
            } else {
                messageTextView.setText("Wrong :(");
            }
            answered++;

            String scoreText = correct + "/" + answered;
            scoreTextView.setText(scoreText);

            generateProblem();

            messageTextView.setVisibility(View.VISIBLE);

            new CountDownTimer(600, 600) {

                @Override
                public void onTick(long millisUntilFinished) { }

                public void onFinish() {
                    messageTextView.setVisibility(View.INVISIBLE);
                }
            }.start();
        }
    }

    public void endGame() {

        new CountDownTimer(500, 500) {

            @Override
            public void onTick(long millisUntilFinished) { }

            public void onFinish() {
                messageTextView.setText("Finished!");
                messageTextView.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void onPlayAgainButtonPressed(View view) {
        startGame();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
