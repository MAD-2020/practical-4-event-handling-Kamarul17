package sg.edu.np.WhackAMole;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 8.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The functions readTimer() and placeMoleTimer() are to inform the user X seconds before starting and loading new mole.
        - Feel free to modify the function to suit your program.
    */

    final String TAG = "Whack-A-Mole! 2.0";
    TextView textScore2;
    int advancedScore;
    Button btn_tl;
    Button btn_tm;
    Button btn_tr;
    Button btn_ml;
    Button btn_mm;
    Button btn_mr;
    Button btn_bl;
    Button btn_bm;
    Button btn_br;
    CountDownTimer tCountdown;
    CountDownTimer mCountdown;

    private void readyTimer(){
        /*  HINT:
            The "Get Ready" Timer.
            Log.v(TAG, "Ready CountDown!" + millisUntilFinished/ 1000);
            Toast message -"Get Ready In X seconds"
            Log.v(TAG, "Ready CountDown Complete!");
            Toast message - "GO!"
            belongs here.
            This timer countdown from 10 seconds to 0 seconds and stops after "GO!" is shown.
         */
        tCountdown = new CountDownTimer(10000,1000){
            @Override
            public void onTick(long l) {
                Toast.makeText(Main2Activity.this, "Get ready in " + l / 1000 + "seconds.", Toast.LENGTH_SHORT).show();
                Log.v(TAG,"Ready CountDown!" + l / 1000);

            }

            @Override
            public void onFinish() {
                Toast.makeText(Main2Activity.this,"Go!",Toast.LENGTH_SHORT).show();
                Log.v(TAG, "Ready CountDown Complete!");
                tCountdown.cancel();
                placeMoleTimer();

            }
        };
        tCountdown.start();
    }

    private void placeMoleTimer() {
        /* HINT:
           Creates new mole location each second.
           Log.v(TAG, "New Mole Location!");
           setNewMole();
           belongs here.
           This is an infinite countdown timer.
         */
        mCountdown = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long l) {
                setNewMole();
                Log.v(TAG, "New Mole Location!");

            }

            @Override
            public void onFinish() {
                mCountdown.start();
            }
        };
        mCountdown.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Hint:
            This starts the countdown timers one at a time and prepares the user.
            This also prepares the existing score brought over.
            It also prepares the button listeners to each button.
            You may wish to use the for loop to populate all 9 buttons with listeners.
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn_tl = findViewById(R.id.btn_tl);
        btn_tm = findViewById(R.id.btn_tm);
        btn_tr = findViewById(R.id.btn_tr);
        btn_ml = findViewById(R.id.btn_ml);
        btn_mm = findViewById(R.id.btn_mm);
        btn_mr = findViewById(R.id.btn_mr);
        btn_bl = findViewById(R.id.btn_bl);
        btn_bm = findViewById(R.id.btn_bm);
        btn_br = findViewById(R.id.btn_br);
        textScore2 = findViewById(R.id.textScore2);

        btn_tl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                doCheck(btn_tl);
            }
        });
        btn_tm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                doCheck(btn_tm);
            }
        });
        btn_tr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                doCheck(btn_tr);
            }
        });
        btn_ml.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                doCheck(btn_ml);
            }
        });
        btn_mm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                doCheck(btn_mm);
            }
        });
        btn_mr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                doCheck(btn_mr);
            }
        });
        btn_bl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                doCheck(btn_bl);
            }
        });
        btn_tl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                doCheck(btn_bm);
            }
        });
        btn_tl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                doCheck(btn_br);
            }
        });

        Log.v(TAG, "Current User Score: " + String.valueOf(advancedScore));
    }

    @Override
    protected void onStart(){
        super.onStart();

        Intent receivingEnd = getIntent();
        int message = receivingEnd.getIntExtra("score",0);
        advancedScore = message;
        textScore2.setText(String.valueOf(message));
        readyTimer();

    }

    private void doCheck(Button checkButton)
    {
        /* Hint:
            Checks for hit or miss
            Log.v(TAG, "Hit, score added!");
            Log.v(TAG, "Missed, point deducted!");
            belongs here.
        */
        if (checkButton.getText() == "*"){
            advancedScore++;
            Log.v(TAG, "Hit Score added!");
        }
        else{
            advancedScore--;
            Log.v(TAG, "Missed, score deducted!");
        }
        textScore2.setText(Integer.toString(advancedScore));

    }

    public void setNewMole()
    {
        /* Hint:
            Clears the previous mole location and gets a new random location of the next mole location.
            Sets the new location of the mole.
         */
        Random ran = new Random();
        int randomLocation = ran.nextInt(9);

        btn_tl.setText("O");
        btn_tm.setText("O");
        btn_tr.setText("O");
        btn_ml.setText("O");
        btn_mm.setText("O");
        btn_mr.setText("O");
        btn_bl.setText("O");
        btn_bm.setText("O");
        btn_br.setText("O");
        if (randomLocation == 0){
            btn_tl.setText("*");
        }
        else if (randomLocation == 1){
            btn_tm.setText("*");
        }
        else if (randomLocation == 2){
            btn_tr.setText("*");
        }
        else if (randomLocation == 3){
            btn_ml.setText("*");
        }
        else if (randomLocation == 4){
            btn_mm.setText("*");
        }
        else if (randomLocation == 5){
            btn_mr.setText("*");
        }
        else if (randomLocation == 6){
            btn_bl.setText("*");
        }
        else if (randomLocation == 7){
            btn_bm.setText("*");
        }
        else if (randomLocation == 8){
            btn_br.setText("*");
        }
    }
}

