package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView countDownTextView, scoreTextView, messageTextView;
    Button option1Button, option2Button, option3Button, option4Button, playAgainButton;

    public void onGoButtonPressed(View view) {
        Button goButton = (Button) view;
        goButton.setVisibility(View.GONE);
        initViews();
        startGame();
    }

    public void initViews() {

        countDownTextView = findViewById(R.id.countDownTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        messageTextView = findViewById(R.id.messageTextView);

        option1Button = findViewById(R.id.option1Button);
        option2Button = findViewById(R.id.option2Button);
        option3Button = findViewById(R.id.option3Button);
        option4Button = findViewById(R.id.option4Button);
        playAgainButton = findViewById(R.id.playAgainButton);

        countDownTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);

        option1Button.setVisibility(View.VISIBLE);
        option2Button.setVisibility(View.VISIBLE);
        option3Button.setVisibility(View.VISIBLE);
        option4Button.setVisibility(View.VISIBLE);
    }

    public void startGame() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
