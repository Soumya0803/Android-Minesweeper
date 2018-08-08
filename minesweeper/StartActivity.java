package com.example.soumya.minesweeper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    EditText edittext;
    TextView nametextview;
    Button startbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setTitle("Start game");
        edittext = (EditText) findViewById(R.id.edittext);
        startbutton = (Button) findViewById(R.id.button);
        nametextview = (TextView) findViewById(R.id.textview);

        final SharedPreferences sharedPreferences = getSharedPreferences("Minesweeper", MODE_PRIVATE);
        String name = sharedPreferences.getString("userName", null);
        if (name == null) {
            nametextview.setText("Welcome User ");
        } else {
            nametextview.setText("Welcome " + name);
        }





        startbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String name = edittext.getText().toString();

                if(name.isEmpty()){

                    Toast.makeText(StartActivity.this, "Enter name !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName", name);
                editor.commit();

                Intent i = new Intent(StartActivity.this, MainActivity.class);
        i.putExtra("user_name", name);
        startActivity(i);


    }

      });
    }

         }