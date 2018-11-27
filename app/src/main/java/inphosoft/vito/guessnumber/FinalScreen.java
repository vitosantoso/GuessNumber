package inphosoft.vito.guessnumber;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FinalScreen extends AppCompatActivity {

    private TextView answer;
    private TextView score;
    private SharedPreferences savedNumber;
    private SharedPreferences scoreNumber;
    private Button goBack;

    public static final String myPreference = "myPref";
    public static final String scorePrererence = "scorePref";
    public static final String answerBuffer = "0";
    public static final String scoreString = "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.final_screen);
        super.onCreate(savedInstanceState);

        answer = findViewById(R.id.answer);
        score = findViewById(R.id.score);
        goBack = findViewById(R.id.backButton);

        savedNumber = getSharedPreferences(myPreference, MODE_PRIVATE);
        scoreNumber= getSharedPreferences(scorePrererence, MODE_PRIVATE);

        Integer answerInt = savedNumber.getInt(answerBuffer,0);

        Integer scoreInt = scoreNumber.getInt(scoreString,0);

        scoreInt +=1;

        SharedPreferences.Editor editor = scoreNumber.edit();
        editor.putInt(scoreString, scoreInt);
        editor.commit();

        if(savedNumber.contains(answerBuffer)){
            answer.setText(String.valueOf(answerInt));
        }

        if(scoreNumber.contains(scoreString)){
            score.setText(String.valueOf(scoreInt));
        }

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });




    }
}
