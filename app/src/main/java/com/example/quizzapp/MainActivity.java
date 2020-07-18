package com.example.quizzapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //START SAVE INSTANCE STEP 1
    private final String  SCORE_KEY = "SCORE";
    private final String  INDEX_KEY = "INDEX";
    //END SAVE INSTANCE STEP 1
    //NAME OBJECT FROM LAYOUT
    private TextView mTxtQuestion;
    private Button btnTrue;
    private  Button btnWrong;
    private ProgressBar mProgressBar;
    private  TextView mQuizStatsTextView;
    //END NAME OBJECT FROM LAYOUT

    //CREATE VARIABLE
    private int mQuestionIndex;
    private int mQuizQuestion;
    private int mUserScore;
    //END CREATE VARIABLE

    private QuizModel[] questionCollection = new QuizModel[]{
            new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,false),
            new QuizModel(R.string.q3,true),
            new QuizModel(R.string.q4,false),
            new QuizModel(R.string.q5,true),
            new QuizModel(R.string.q6,false),
            new QuizModel(R.string.q7,true),
            new QuizModel(R.string.q8,false),
            new QuizModel(R.string.q9,true),
            new QuizModel(R.string.q10,false),
    };
    //INITIALISE progress bar value
    final int USER_PROGRESS = (int) Math.ceil(100.0 / questionCollection.length); //Maximum value of progress bar is 100
    //Math.ceil = return type double value
    //(int) Math.ceil -> round up value int
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //START SAVE INSTANCE  STEP 2
        if(savedInstanceState != null){
            mUserScore = savedInstanceState.getInt(SCORE_KEY);
            mQuestionIndex = savedInstanceState.getInt(INDEX_KEY);

        }else{
            mUserScore = 0;
            mQuestionIndex =0;
        }

        // END SAVE INSTANCE STEP 2

        //First Lifecycle Method

        Toast.makeText(getApplicationContext(),"OnCreate Method is called",Toast.LENGTH_LONG).show();

        //INITIALIZED OBJECT FROM LAYOUT
        mTxtQuestion = findViewById(R.id.txtQuestion);
        btnTrue = findViewById(R.id.btnTrue);
        btnWrong = findViewById(R.id.btnWrong);
        mProgressBar = findViewById(R.id.pbQuiz);
        mQuizStatsTextView = findViewById(R.id.txtQuizStats);
        //END INITIALIZED OBJECT FROM LAYOUT

        //START SAVE INSTANCE STEP 3
        mQuizStatsTextView.setText(mUserScore+"");
        //END SAVE INSTANCE STEP 3


        //mTxtQuestion Code

        QuizModel q1 = questionCollection[mQuestionIndex];
        mQuizQuestion = q1.getQuestion();
        mTxtQuestion.setText(mQuizQuestion);



        //END mTxtQuestion Code


        //START Button code

//        View.OnClickListener myClickListener = new View.OnClickListener() { //OnClickListener for many objects
//            @Override
//            public void onClick(View v) {
//                if(v.getId()==R.id.btnTrue){
//                    Log.i("MyApp","Button True is tapped now !");
//
//                }
//                //START TOAST
////                Toast myToastObject = Toast.makeText(getApplicationContext(),"Button True is tapped now !",Toast.LENGTH_LONG);
////                myToastObject.show();
//                //END TOAST
//
//            }
//        };
//
//        btnTrue.setOnClickListener(myClickListener);
////      btnWrong.setOnClickListener(myClickListener);

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("MyApp","Button True is tapped now !");
                evaluateUserAnswer(true);
                changeQuestionOnButtonClick();
            }
        });

        btnWrong.setOnClickListener(new View.OnClickListener() {  //CREATE ON CLICK LISTENER ON THE SPOT FOR ONE OBJECT
            @Override
            public void onClick(View v) {
                Log.i("MyApp","Button Wrong is tapped now !");
                evaluateUserAnswer(false);
                changeQuestionOnButtonClick();
                //START TOAST
//                Toast.makeText(getApplicationContext(),"Button Wrong is tapped now !",Toast.LENGTH_SHORT).show(); //TOAST ON THE SPOT
                //END TOAST
            }
        });

        //END Button Code


    }
    private void changeQuestionOnButtonClick(){

        //% - 20%15 = 5 - 30%27 = 3
        // 5%10 = 5 - 1%4 = 1

        mQuestionIndex= (mQuestionIndex+1)%questionCollection.length;

        if(mQuestionIndex == 0){
            AlertDialog.Builder quizAlert = new AlertDialog.Builder(this);
            quizAlert.setCancelable(false);
            //this = getApplicationContext()
            quizAlert.setTitle("The Quiz is finished");
            quizAlert.setMessage("Your score is "+mUserScore);
            quizAlert.setPositiveButton("Finished the quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            quizAlert.show();
        }

        mQuizQuestion = questionCollection[mQuestionIndex].getQuestion();
        mTxtQuestion.setText(mQuizQuestion);
        mProgressBar.incrementProgressBy(USER_PROGRESS);
        mQuizStatsTextView.setText(mUserScore+"");

    }

    private void evaluateUserAnswer(boolean userGuess){
        boolean currentQuestionAnswer = questionCollection[mQuestionIndex].isAnswer();
        if (currentQuestionAnswer == userGuess){
            Toast.makeText(getApplicationContext(),R.string.correct_toast_message, Toast.LENGTH_SHORT).show();
            mUserScore++;
        }else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast_message, Toast.LENGTH_SHORT).show();
        }
    }

    //LifeCycle Methods

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),"OnStart method is called",Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(),"OnResume method is called",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(),"OnPause method is called",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(),"OnStop method is called",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"OnDestroy method is called",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCORE_KEY, mUserScore);
        outState.putInt(INDEX_KEY, mQuestionIndex);
    }
}
