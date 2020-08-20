package com.example.quizapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private final String SCORE_KEY = "SCORE";
    private final String INDEX_KEY = "INDEX";

    private TextView mtxt;
    private Button btnTrue;
    private Button btnWrong;
    private int mQuestionIndex;
    private int mQuizQuestion;
    private  TextView mtxtQuizStats;
    private ProgressBar mProgressBar;
    private int mUserScore;


    private QuizModel[] allQustions=new QuizModel[]{
            new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,false),
            new QuizModel(R.string.q3,true),
            new QuizModel(R.string.q4,false),
            new QuizModel(R.string.q5,true),
            new QuizModel(R.string.q6,false),
            new QuizModel(R.string.q7,true),
            new QuizModel(R.string.q8,true),
            new QuizModel(R.string.q9,false),
            new QuizModel(R.string.q10,true)
    };

    final int USER_PROGRESS=(int)Math.ceil(100.0/allQustions.length);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {

            mUserScore = savedInstanceState.getInt(SCORE_KEY);
            mQuestionIndex = savedInstanceState.getInt(INDEX_KEY);


        } else {

            mUserScore = 0;
            mQuestionIndex = 0;


        }

        // first lifecycle method
        Toast.makeText(getApplicationContext(), "OnCreate method is called", Toast.LENGTH_SHORT).show();

        mtxt=findViewById(R.id.txtQuestion);
        mProgressBar=findViewById(R.id.quizPB);
        mtxtQuizStats=findViewById(R.id.txtQuizStats);
        mtxtQuizStats.setText(mUserScore+"");

        QuizModel q1=allQustions[mQuestionIndex];

        mQuizQuestion =q1.getQuestion();

        mtxt.setText(mQuizQuestion);


        btnTrue=findViewById(R.id.btnTrue);
        View.OnClickListener myClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluateUsersAnswers(true);

                ChangeQuestionOnButtonClick();

            }
        };


        btnTrue.setOnClickListener(myClickListener);

        btnWrong=findViewById(R.id.btnWrong);

        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluateUsersAnswers(false);

                ChangeQuestionOnButtonClick();
            }
        });



    }

    private void ChangeQuestionOnButtonClick(){
        /*
        0 = 1 % 10 = 1
        1 = 2 % 10 = 2
        */
        mQuestionIndex=(mQuestionIndex+1)%10;

        if(mQuestionIndex==0){
            AlertDialog.Builder quizAlert =new AlertDialog.Builder(this);
            quizAlert.setCancelable(false);
            quizAlert.setTitle("The Quiz is finished");
            quizAlert.setMessage("Your Score is "+mUserScore);
            quizAlert.setPositiveButton("Finish the Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            quizAlert.show();
        }

        mQuizQuestion=allQustions[mQuestionIndex].getQuestion();

        mtxt.setText(mQuizQuestion);

        mProgressBar.incrementProgressBy(USER_PROGRESS);
        mtxtQuizStats.setText(mUserScore+"");

    }

    private void evaluateUsersAnswers(boolean userGuess){

        boolean currentQuestionAnswer=allQustions[mQuestionIndex].isAnswer();

        if(currentQuestionAnswer==userGuess){
            Toast.makeText(getApplicationContext(), R.string.correct_toast_message,Toast.LENGTH_SHORT).show();
            mUserScore=mUserScore+1;
        }else{
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast_message,Toast.LENGTH_SHORT).show();
        }


    }
    // Lifecycle methods


    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(getApplicationContext(), "OnStart method is called", Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(getApplicationContext(), "OnResume method is called", Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(getApplicationContext(), "OnPause method is called", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(getApplicationContext(), "OnStop method is called", Toast.LENGTH_SHORT).show();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(getApplicationContext(), "OnDestroy method is called", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // computer - a robot
        outState.putInt(SCORE_KEY, mUserScore);
        outState.putInt(INDEX_KEY, mQuestionIndex);
    }
}