package inphosoft.vito.guessnumber;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int answer = new Random().nextInt(11) + 1;
    String guess;
    int guessNumber;
    int count = 0;
    private EditText guessText;
    private Button guessButton;
    private SharedPreferences savedNumber;
    private TextView header;

    public static final String myPreference = "myPref";
    public static final String answerBuffer = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        guessText = findViewById(R.id.guessNumber);
        guessButton = findViewById(R.id.guessButton);
        header = findViewById(R.id.header);
        //header.setText(String.valueOf(answer));

        savedNumber = getSharedPreferences(myPreference, MODE_PRIVATE);


        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guess = guessText.getText().toString();
                guessNumber = Integer.parseInt(guess);

                SharedPreferences.Editor editor = savedNumber.edit();
                editor.putInt(answerBuffer, guessNumber);
                editor.commit();

                if(guessNumber == answer){
                    Intent intent = new Intent(MainActivity.this, FinalScreen.class);
                    startActivity(intent);
                }else if(guessNumber != answer){
                    if(guessNumber > 12 || guessNumber == 0) {
                        Toast toast = Toast.makeText(MainActivity.this, "Pls insert between 1-12", Toast.LENGTH_SHORT);
                        toast.show();
                    } else{
                        Toast toast = Toast.makeText(MainActivity.this, "Wrong Answer", Toast.LENGTH_SHORT);
                        toast.show();
                        count += 1;
                    }
                    if(count == 3){
                        Toast gameOver = Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_SHORT);
                        gameOver.show();
                        count = 0;
                        header.setText("The Answer is " + String.valueOf(answer));
                    }
                }
            }
        });


    }
}
