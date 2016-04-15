package com.example.ghopkins.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startGame;
    TextView timer;
    TextView score;
    TextView question;
    TextView answer0;
    TextView answer1;
    TextView answer2;
    TextView answer3;

    int totalQuestions;
    int rightQuestions;

    String right;
    String total;

    int locationOfCorrectAnswer;
    Random random;
    int intAnswer;

    CountDownTimer countDownTimer;

    public void start(View view) {
        timer.setText("30sec");
        startGame.setVisibility(View.GONE);
        question.setVisibility(View.VISIBLE);
        totalQuestions=0;
        rightQuestions=0;
        score.setText("0/0");
            countDownTimer=new CountDownTimer(30100, 1000) { //starts the time at 30 seocnds.added the +100 to compensate for the dealy in execution. 1000 is countdown increment (1 second)
                @Override
                public void onTick(long millisUntilFinished) {
                    timer.setText((int) millisUntilFinished / 1000 + "s");
                }

                @Override
                public void onFinish() {
                startGame.setVisibility(View.VISIBLE);
                    startGame.setText("Start a new game");
                    checkScore();
                    countDownTimer.cancel();
                    timer.setText("0s");
                    question.setVisibility(View.INVISIBLE);
                }
            }.start();
        }

public void checkScore(){
    String results;
    if(rightQuestions ==totalQuestions){
        results= "Perfect score!";
    }
    else if(totalQuestions-rightQuestions<2){
        results="Good job but you can do better";
    }
    else{
       results="ALERT ALERT You may have the downs";
    }
    Toast.makeText(getApplicationContext(), results, Toast.LENGTH_LONG).show();
}

    public void generateQuestion(){
        random=new Random();
        int y=random.nextInt(51);
        int x=random.nextInt(51);
         intAnswer=x+y;
        question.setText(x + "+" + y);
    };

  public void checkAnswer(View view) {
      TextView counter = (TextView) view;
      String tappedCounter = (counter.getTag().toString());
      int tappedCounterInt = Integer.parseInt(tappedCounter);

      if (tappedCounterInt == locationOfCorrectAnswer) {
          Toast.makeText(getApplicationContext(), "RIGHT!", Toast.LENGTH_SHORT).show();
          rightQuestions ++;
      }
      else{
          Toast.makeText(getApplicationContext(), "WRONG!", Toast.LENGTH_SHORT).show();
      }
      generateQuestion();
      generateAnswers();
        totalQuestions ++;
      right=String.valueOf(rightQuestions);
      total=String.valueOf(totalQuestions);
      score.setText(right + "/" + total);
  }

    public void generateAnswers(){
        locationOfCorrectAnswer =random.nextInt(4);
        ArrayList<Integer> answers = new ArrayList<Integer>(); //must be reinstaniated or cleared for fresh answers
        int incorrectAnswer;

        for(int i =0; i<=3; i++){
            if (i == locationOfCorrectAnswer) {
                answers.add(intAnswer);
            }
            else{
                incorrectAnswer=random.nextInt(101);

                while(incorrectAnswer ==intAnswer){
                    incorrectAnswer=random.nextInt();
                }
                answers.add(incorrectAnswer);
            }
        }
        answer0.setText(Integer.toString(answers.get(0)));
        answer1.setText(Integer.toString(answers.get(1)));
        answer2.setText(Integer.toString(answers.get(2)));
        answer3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGame=(Button)findViewById(R.id.startGame);
        timer=(TextView)findViewById(R.id.timer);
        score=(TextView)findViewById(R.id.score);
        question=(TextView)findViewById(R.id.question);
        answer0=(TextView)findViewById(R.id.answer0);
        answer1=(TextView)findViewById(R.id.answer1);
        answer2=(TextView)findViewById(R.id.answer2);
        answer3=(TextView)findViewById(R.id.answer3);

        question.setVisibility(View.INVISIBLE);

        generateQuestion();
        generateAnswers();
    }

}
