package sg.edu.np.WhackAMole;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 2.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The function doCheck() also decides if the user qualifies for the advance level and triggers for a dialog box to ask for user to decide.
        - The function nextLevelQuery() builds the dialog box and shows. It also triggers the nextLevel() if user selects Yes or return to normal state if user select No.
        - The function nextLevel() launches the new advanced page.
        - Feel free to modify the function to suit your program.
    */
    final String TAG = "Whack-A-Mole!";
    Button btn1;
    Button btn2;
    Button btn3;
    int randomLocation, Score = 0;
    TextView textScore;


    List<Button> buttonList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        textScore = findViewById(R.id.textScore);
        buttonList.add(btn1);
        buttonList.add(btn2);
        buttonList.add(btn3);


        Log.v(TAG, "Finished Pre-Initialisation!");
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.v(TAG, "Button Left clicked!");
                doCheck(btn1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.v(TAG, "Button Middle clicked!");
                doCheck(btn2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.v(TAG, "Button Right clicked!");
                doCheck(btn3);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        setNewMole();
        Log.v(TAG, "Starting GUI!");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TAG, "Paused Whack-A-Mole!");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TAG, "Stopped Whack-A-Mole!");
        finish();
    }

    private void doCheck(Button checkButton) {
        /* Checks for hit or miss and if user qualify for advanced page.
            Triggers nextLevelQuery().
         */
                if (checkButton.getText() == "*"){
                    Score++;
                    Log.v(TAG, "Hit Score added!");
                }
                else{
                    Score--;
                    Log.v(TAG, "Missed, score deducted!");
                }
                textScore.setText(Integer.toString(Score));
                setNewMole();

                if (Score % 10 == 0 && Score > 0){

                    nextLevelQuery();

                }
            }


    private void nextLevelQuery(){
        /*
        Builds dialog box here.
        Log.v(TAG, "User accepts!");
        Log.v(TAG, "User decline!");
        Log.v(TAG, "Advance option given to user!");
        belongs here*/
        AlertDialog.Builder dialogbox = new AlertDialog.Builder(this);

        dialogbox.setTitle("Warning! Insane Whack-A-Mole incoming!");
        dialogbox.setMessage("Would you like to advance to advanced mode?");
        dialogbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                nextLevel();
                Log.v(TAG, "User accepts!");
            }
        });
        dialogbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.v(TAG, "User decline!");
            }
        });
        AlertDialog alert = dialogbox.create();
        alert.show();
        Log.v(TAG, "Advance option given to user!");
    }

    private void nextLevel(){
        /* Launch advanced page */
        Intent activityName = new Intent(MainActivity.this, Main2Activity.class);
        activityName.putExtra("score", Score);
        startActivity(activityName);

    }

    public void setNewMole()
    {
        Random ran = new Random();
        int randomLocation = ran.nextInt(3);
        btn1.setText("O");
        btn2.setText("O");
        btn3.setText("O");
        buttonList.get(randomLocation).setText("*");
    }
}