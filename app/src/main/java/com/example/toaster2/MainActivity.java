package com.example.toaster2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Clicked = (Button)findViewById(R.id.button1);

        Clicked.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                controller obj= new controller();
                obj
                        .setContext(getApplicationContext())
                        .setDuration(0)
                        .setColor(Color.RED)
                        .setMessage("Error")
                        .setTextSize(17)
                        .setImg(android.R.drawable.star_big_off)
                        .show();
            }
        });

        Clicked=(Button)findViewById(R.id.button2);

        Clicked.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                controller obj= new controller();

                obj
                        .setContext(getApplicationContext())
                        .setDuration(1)
                        .setColor(Color.GREEN)
                        .setMessage("Success")
                        .setTextSize(35)
                        .setImg(android.R.drawable.star_big_on)
                        .show();
            }
        });

        Clicked=(Button)findViewById(R.id.button3);

        Clicked.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                controller obj= new controller();

                obj
                        .setContext(getApplicationContext())
                        .show();
            }
        });

        Clicked=(Button)findViewById(R.id.button4);

        Clicked.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                controller obj= new controller();

                obj
                        .setContext(getApplicationContext())
                        .setDuration(1)
                        .setColor(Color.MAGENTA)
                        .setMessage("Sample Text")
                        .show();
            }
        });
    }
    public void SimpleToast(View view) {
        Toast.makeText(this, "Works", Toast.LENGTH_SHORT).show();
    }

}

