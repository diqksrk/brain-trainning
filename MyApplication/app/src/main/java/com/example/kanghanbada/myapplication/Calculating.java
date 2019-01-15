package com.example.kanghanbada.myapplication;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Calculating extends AppCompatActivity {

    private TextView timerTextView;
    private Button button;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button playAgainButton;
    private TextView resultTextView;
    private TextView pointsTextView;
    private TextView question;
    private int score;
    private int numOfQuestion;
    private boolean gameActive;
    private ArrayList<Integer> answer = new ArrayList<>();
    private int correctAnswerLocation;
    private Button gogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //make fullscreen
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculating);

        timerTextView = (TextView) findViewById(R.id.timer);
        button = (Button) findViewById(R.id.go);
        button1 = (Button) findViewById(R.id.button3);
        button2 = (Button) findViewById(R.id.button4);
        button3 = (Button) findViewById(R.id.button5);
        button4 = (Button) findViewById(R.id.button6);
        playAgainButton = (Button) findViewById(R.id.restartButton);
        resultTextView = (TextView) findViewById(R.id.result);

        pointsTextView = (TextView) findViewById(R.id.points);
        playAgainButton.setVisibility(View.INVISIBLE);

        question = (TextView) findViewById(R.id.question);
        button1.setTag("0");button2.setTag("1");button3.setTag("2");button4.setTag("3");
        playAgain((Button) findViewById(R.id.restartButton));
        generateQuestion();
    }

    public void playAgain(View view) {
        score = 0;
        numOfQuestion = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        pointsTextView.setText(score + "/" + numOfQuestion);
        playAgainButton.setVisibility(View.INVISIBLE);
        gameActive = true;

        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0");
                resultTextView.setText("score : " + score + "/" + numOfQuestion);
                resultTextView.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
                gameActive = false;
            }
        }.start();
    }

    private void generateQuestion() {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        Log.i("aaa", Integer.toString(a));
        Log.i("aaa", Integer.toString(b));
        question.setText(Integer.toString(a) + " + " + Integer.toString(b));
        answer.clear();
        correctAnswerLocation = rand.nextInt(4);

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {
            if (i == correctAnswerLocation) {
                answer.add(a + b);
            }else {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer==a+b) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answer.add(incorrectAnswer);
            }
        }
        button1.setText(String.valueOf(answer.get(0)));
        button2.setText(String.valueOf(answer.get(1)));
        button3.setText(String.valueOf(answer.get(2)));
        button4.setText(String.valueOf(answer.get(3)));
    }


    public void start(View view) {
        gogo = (Button) findViewById(R.id.go);
        gogo.setVisibility(View.INVISIBLE);
    }


    public void chooseAnswer(View view) {
        Log.i("myTag", (String)view.getTag());
        if (gameActive) {
            resultTextView.setVisibility(view.VISIBLE);
            numOfQuestion++;

            if (view.getTag().toString().equals(Integer.toString(correctAnswerLocation))){
                Log.i("check", "Correct");
                score++;
                resultTextView.setText("Correct");
            }else {
                resultTextView.setText("False");
            }
            pointsTextView.setText(score + "/" + numOfQuestion);
            generateQuestion();
        }
    }
}










