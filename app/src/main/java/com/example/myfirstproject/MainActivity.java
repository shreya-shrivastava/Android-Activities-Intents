package com.example.myfirstproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static  String name="";   //to store the name entered by the user
    public int count;                //to check RESULT_OK or RESULT_CANCELLED
    private static Button button1,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);   //setting layout configuration
        button1= findViewById(R.id.button);       //getting references to view

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, SecondActivity.class);   //creation of intent object for next activity
                startActivityForResult(intent, 1);                                       //starting the activity


            }
        });

        button2= findViewById(R.id.button2);                         //getting view reference
        button2.setOnClickListener(new View.OnClickListener() {      //action to be performed when second button is pressed
            @Override
            public void onClick(View view) {
                if(count==1){                                                             //checking RESULT_OK
                    Intent intent= new Intent(ContactsContract.Intents.Insert.ACTION);    //Creating intent for the Contact app
                    intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);            //and then opening the activity
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name);          //with the legal name provided by the user
                    startActivity(intent);
                }
              else if(count==0){     //checking RESULT_CANCELLED and displaying a toast message along with the name entered
                    Toast.makeText(MainActivity.this, "You entered "+name+ ", which is not a legal name. Try Again!!", Toast.LENGTH_LONG).show();
                }
                }


            });
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {  //getting the result from the
                                                                                               //method startActivityForResult
       if(requestCode==1){
           if(resultCode==RESULT_OK){
               count=1;                                                     //setting counter value
               name = data.getStringExtra("legal_name");              //retrieving the name entered by the user
           }
           else if(resultCode==RESULT_CANCELED){
               count=0;                                                      //setting counter value
               name= data.getStringExtra("legal_name");                //retrieving the name entered by the user
              }
               }
           }
       }


