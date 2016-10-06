package com.beginners.hangdroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameMultiActivity extends AppCompatActivity {

    String mWord;
    int mFailCounter = 0;
    int mGuessedLetters = 0;
    int mPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_game);
        String wordSent= getIntent().getStringExtra("WORD_IDENTIFIER");

        Log.d("MYLOG", wordSent);

        mWord = wordSent;

        createTextView(wordSent);
    }

    /**
     * Retreiving the letter introduced on the editText
     *
     * @param view (button clicked)
     */
    public void introduceLetter(View view){
        EditText editText = (EditText) findViewById(R.id.editTextLetter);

        String myLetter = editText.getText().toString();

        editText.setText("");

        Log.d("MYLOG", "The letter introduced is " + myLetter);

        if(myLetter.length() > 0){

            checkLetter(myLetter);
        }
        else {
            Toast.makeText(this, "Please Introduce a letter", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Checking if the letter introduced matches any letter in the word to guess
     *
     * @param introducedletter, letter introduced by the user
     */
    public void checkLetter(String introducedletter){
        char charIntroduced = introducedletter.charAt(0);
        boolean letterGuessed = false;
        for(int i = 0; i < mWord.length(); i++){
            char charFromTheWord = mWord.charAt(i);
            Log.d("MYLOG", "The letter we are checking is " + charFromTheWord);
            if(charFromTheWord == charIntroduced){
                Log.d("MYLOG", "There was one match");
                letterGuessed = true;
                showLettersAtIndex(i, charIntroduced);
                mGuessedLetters++;
            }
        }
        if(letterGuessed == false){
            letterFailed(Character.toString(charIntroduced));
        }
        if(mGuessedLetters == mWord.length()){
            finish();
        }
    }

    public void createTextView(String word){
        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);

        for(int i = 0; i < word.length(); i++){
            TextView newTextView = (TextView) getLayoutInflater().inflate(R.layout.textview, null);
            layoutLetters.addView(newTextView);
        }
    }

    public void clearScreen(){
        TextView textViewFailed = (TextView) findViewById(R.id.textView7);
        textViewFailed.setText("");
        mGuessedLetters = 0;
        mFailCounter = 0;
        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);
        for(int i = 0; i < layoutLetters.getChildCount(); i++){
            TextView currentTextView = (TextView) layoutLetters.getChildAt(i);
            currentTextView.setText("_");
        }
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hangdroid_0);
    }

    public void letterFailed(String letterFailed){
        TextView textViewFailed = (TextView) findViewById(R.id.textView7);
        String previousFail = textViewFailed.getText().toString();
        textViewFailed.setText(previousFail + letterFailed);
        mFailCounter++;
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        if(mFailCounter == 1){
            imageView.setImageResource(R.drawable.hangdroid_1);
        }
        else if(mFailCounter == 2){
            imageView.setImageResource(R.drawable.hangdroid_2);
        }
        else if(mFailCounter == 3){
            imageView.setImageResource(R.drawable.hangdroid_3);
        }
        else if(mFailCounter == 4){
            imageView.setImageResource(R.drawable.hangdroid_4);
        }
        else if(mFailCounter == 5){
            imageView.setImageResource(R.drawable.hangdroid_5);
        }else if(mFailCounter == 6){
//            Intent gameOverIntent = new Intent(this, GameOverActivity.class);
//            gameOverIntent.putExtra("POINTS_IDENTIFIER", mPoints);
//            startActivity(gameOverIntent);
            finish();   
        }
    }

    /**
     * Displaying a letter guessed by the user
     *
     * @param position of the letter
     * @param letterGuessed
     */
    public void showLettersAtIndex(int position, char letterGuessed){
        LinearLayout layoutLetter = (LinearLayout) findViewById(R.id.layoutLetters);
        TextView textView = (TextView)layoutLetter.getChildAt(position);
        textView.setText(Character.toString(letterGuessed));
    }

}
