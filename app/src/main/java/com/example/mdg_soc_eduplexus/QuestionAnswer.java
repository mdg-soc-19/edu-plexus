package com.example.mdg_soc_eduplexus;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuestionAnswer extends AppCompatActivity {
    public static final String EXTRA_SCORE = "Extra Score";

    private static final long COUNTDOWN_IN_MILLIS = 30000*20;
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private TextView textViewTestNO_1;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;
    private Button buttonEndTest;
    MemberForQuiz member;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    public static int testNumber;



    private List<QuestionGenerator> questionList;
    private int questionCounter;
    private int questionCountTotal;
    FirebaseAuth mAuth;
    private QuestionGenerator currentQuestion;

    private int score;
    private boolean answered;
    private View view1,view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer);

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        member = new MemberForQuiz();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef =  database.getReference("Member");
        buttonEndTest = findViewById(R.id.end_test);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);
        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();
        view1 = findViewById(R.id.cardViewTest1);
        view2 = findViewById(R.id.cardViewTest2);
        textViewTestNO_1 = findViewById(R.id.text_view_test_no);

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        if (testNumber == 1){
            questionList = dbHelper.getAllQuestions(1);
        }
        else if(testNumber == 2){
            questionList = dbHelper.getAllQuestions(2);
        }
        else if(testNumber == 3){
            questionList = dbHelper.getAllQuestions(3);
        }
        else {
            questionList = dbHelper.getAllQuestions(4);
        }
        questionCountTotal = 4;
        Collections.shuffle(questionList);
        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();
        textViewTestNO_1.setText("Test Number :"+ testNumber);
        showNextQuestion();
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuestionAnswer.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
        buttonEndTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                Intent i = new Intent(QuestionAnswer.this,OnlineTests.class);
                startActivity(i);
                member.setScore(score);
                member.setEmailId(user.getEmail());
                member.setTestno(testNumber);
                myRef.push().setValue(member);
            }
        });
    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();


        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");

        } else {
            finishQuiz();
        }
    }

    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }
    private  void updateCountDownText(){
        int minutes = (int) (timeLeftInMillis/1000)/60;
        int seconds = (int) (timeLeftInMillis/1000)%60;

        String timeFormatted = String.format(Locale.getDefault(),"%01d:%01d",minutes,seconds);

        textViewCountDown.setText(timeFormatted);

        if(timeLeftInMillis<10000 && timeLeftInMillis>0 ){
            textViewCountDown.setTextColor(Color.RED);
        }
        else if(timeLeftInMillis == 0){
            Intent i = new Intent(QuestionAnswer.this,OnlineTests.class);
            startActivity(i);
        }
        else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }
    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()) {
            score = score + 4;
        }
        else {
            score--;
        }
        textViewScore.setText(score+"/"+16);

        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Answer 3 is correct");
                break;
        }

        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Next");
        } else {
            buttonConfirmNext.setVisibility(View.GONE);
        }
    }
    public int SetTestNo(int t1){
        return this.testNumber = t1;
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
