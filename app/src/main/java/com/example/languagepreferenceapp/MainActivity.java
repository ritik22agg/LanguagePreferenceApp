package com.example.languagepreferenceapp;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    SharedPreferences sharedPreferences;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.english:
                store("English");
                textView.setText(sharedPreferences.getString("answer", ""));
                return true;
            case R.id.spanish:
                store("Spanish");
                textView.setText(sharedPreferences.getString("answer", ""));
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void store(String ans){
        sharedPreferences.edit().putString("answer", ans).apply();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("com.example.languagepreferenceapp", MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
        textView = (TextView) findViewById(R.id.textView);
        if(sharedPreferences.getString("answer", "") == "") {
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Choose a language")
                    .setIcon(R.drawable.ic_launcher_foreground)

                    .setMessage("Which language would you like?")
                    .setPositiveButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            textView = (TextView) findViewById(R.id.textView);
                            store("Spanish");
                            textView.setText(sharedPreferences.getString("answer", ""));
                            Log.i("Positive", "Hello");
                        }
                    })
                    .setNegativeButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            textView = (TextView) findViewById(R.id.textView);
                            store("English");
                            textView.setText(sharedPreferences.getString("answer", ""));
                            Log.i("Negative", "Bye");
                        }
                    }).show();
        }else{
            textView.setText(sharedPreferences.getString("answer", ""));
        }


    }
}
