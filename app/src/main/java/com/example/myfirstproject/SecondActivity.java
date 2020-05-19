package com.example.myfirstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.*;
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

     final EditText editText=findViewById(R.id.editText);                 //getting references to view
     editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
         @Override
         public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {      //Checking if "Enter" key is pressed by the user
             if(i== EditorInfo.IME_ACTION_SEARCH || i== EditorInfo.IME_ACTION_GO ||
                     i== EditorInfo.IME_ACTION_DONE ||i== EditorInfo.IME_ACTION_NEXT ||
                     (keyEvent.getAction()==KeyEvent.ACTION_DOWN) && (keyEvent.getKeyCode()== KeyEvent.KEYCODE_ENTER)){

                 Intent intent = new Intent();                         //creating object for the Intent
                 String legal_name= editText.getText().toString();     //taking the input value in a string
                 intent.putExtra("legal_name", legal_name);      //putting the name entered by the user in Intent extra
                 String pattern = "[A-Za-z]\\s+?[ A-Za-z]";               //Validating if the name entered by the user is legal or not
                 Pattern p= Pattern.compile(pattern);
                 Matcher m=p.matcher(legal_name);

                 if(legal_name.isEmpty()){                             //If the user does not provide any input
                     Toast.makeText(SecondActivity.this, "Please enter your legal name", Toast.LENGTH_LONG).show();
                 }
                 else if(m.find()){                                    //If the name entered is legal
                      setResult(RESULT_OK, intent);                    //setting result code as RESULT_OK
                      finish();                                        //finish second activity
                 }
                 else {                                                //If the name entered is not legal
                     setResult(RESULT_CANCELED, intent);               //setting result code as RESULT_CANCELLED
                     finish();                                         //finish second activity
                      }
                 return true;
             }
             return  false;

         }
     });

    }
}
