package com.flashz.scarnesdice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class DiceActivity extends Activity implements View.OnClickListener {

    private int yourOverallScore;
    private int yourTurnScore;
    private int computerOverallScore;
    private int computerTurnScore;
    TextView overallScore,turnScore;
    Button hold, reset, roll;
    ImageView dice;
    Random randomNumber = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        overallScore = (TextView) findViewById(R.id.overallscore);
        turnScore = (TextView) findViewById(R.id.turnscore);
        hold = (Button) findViewById(R.id.hold);
        reset = (Button) findViewById(R.id.reset);
        roll = (Button) findViewById(R.id.roll);
        dice = (ImageView) findViewById(R.id.diceview);
        roll.setOnClickListener(this);
        hold.setOnClickListener(this);
        reset.setOnClickListener(this);

    }

    public void changeDiceImage(int n){

        int id;

        switch(n){
            case 0:
                id = R.drawable.dice1;
                break;
            case 1:
                id = R.drawable.dice2;
                break;
            case 2:
                id = R.drawable.dice3;
                break;
            case 3:
                id = R.drawable.dice4;
                break;
            case 4:
                id = R.drawable.dice5;
                break;
            case 5:
                id = R.drawable.dice6;
                break;
            default:
                id = R.drawable.dice1;
        }
        dice.setImageDrawable(getResources().getDrawable(id,getTheme()));
    }

    @Override
    public void onClick(View view) {
        if( view == roll )
        {
            int rn = randomNumber.nextInt(6);
            changeDiceImage(rn);
            if(rn == 0){
                yourTurnScore = 0;
                String str = "Your Score : " + yourOverallScore + " , Computer Score : " + computerOverallScore;
                overallScore.setText(str);
                compTurn();
//                roll.setEnabled(false);
//                hold.setEnabled(false);
            }
            else{
                yourTurnScore += ( rn +1 );
                turnScore.setText("Your Score : " + yourTurnScore);
            }
        }
        else if( view == hold )
        {
            yourOverallScore += yourTurnScore;
            String str = "Your Score : " + yourOverallScore + " , Computer Score : " + computerOverallScore;
            overallScore.setText(str);
            compTurn();
//            hold.setEnabled(false);
//            roll.setEnabled(false);
        }
        else
        {
            yourOverallScore = 0;
            yourTurnScore = 0;
            computerOverallScore = 0;
            computerTurnScore = 0;
            String str = "Your Score : " + yourOverallScore + " , Computer Score : " + computerOverallScore;
            overallScore.setText(str);
            turnScore.setText("Your Score : " + yourTurnScore);
            roll.setEnabled(true);
            hold.setEnabled(true);
        }
    }

    public void compTurn()
    {
        int rn,flag = 0; String str;
        hold.setEnabled(false);
        roll.setEnabled(false);

        while(true){
            rn = randomNumber.nextInt(6);
            changeDiceImage(rn);
            turnScore.setText("Computer Score : " + computerTurnScore);

            if( (computerTurnScore + computerOverallScore) > 100 )
            {
                Toast.makeText(DiceActivity.this, "Computer Wins", Toast.LENGTH_SHORT).show();
//                hold.setEnabled(false);
//                roll.setEnabled(false);
                flag = 1;
            }

            if( computerTurnScore > 20 ) {
                computerOverallScore += computerTurnScore;
                str = "Your Score : " + yourOverallScore + " , Computer Score : " + computerOverallScore;
                overallScore.setText(str);
                break;
            }
            else {

                if (rn == 0) {
                    computerTurnScore = 0;
                    str = "Your Score : " + yourOverallScore + " , Computer Score : " + computerOverallScore;
                    overallScore.setText(str);
                    break;
                } else {
                    computerTurnScore += (rn + 1);
                }
            }
        }
        if(flag == 0) {
            roll.setEnabled(true);
            hold.setEnabled(true);
        }
        else{
            str = "Your Score : " + yourOverallScore + " , Computer Score : " + computerOverallScore;
            overallScore.setText(str);
        }

        yourTurnScore = 0;
        computerTurnScore = 0;

    }
}

