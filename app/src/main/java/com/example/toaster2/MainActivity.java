package com.example.toaster2;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
//
//import com.facebook.drawee.view.SimpleDraweeView;
//import com.facebook.fresco.samples.showcase.BaseShowcaseFragment;
//import com.facebook.fresco.samples.showcase.R;
//import com.facebook.fresco.samples.showcase.misc.ImageUriProvider;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fresco.initialize(this);

        setContentView(R.layout.activity_main);
        //View view=getApplicationContext(this)
        View view=findViewById(android.R.id.content);
        //Uri uri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/master/docs/static/logo.png");
        Uri uri=Uri.parse("https://homepages.cae.wisc.edu/~ece533/images/serrano.png");
        Fresco.getImagePipeline().clearCaches();
        SimpleDraweeView draweeView = (SimpleDraweeView)view.findViewById(R.id.drawee_view);
        draweeView.getHierarchy().setRetryImage(R.color.colorPrimary);
        draweeView.setController(
                Fresco.newDraweeControllerBuilder()
                        .setTapToRetryEnabled(true)
                        .setUri(uri)
                        .build());
        //draweeView.showContextMenu();


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

                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, 1);

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

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        System.out.println(data.getData());
        View view=findViewById(android.R.id.content);
        SimpleDraweeView draweeView = (SimpleDraweeView)view.findViewById(R.id.drawee_view);
        draweeView.getHierarchy().setRetryImage(R.color.colorPrimary);
        //draweeView.setImageURI(data.getData());
        //draweeView.setText(data.getDataString());
        draweeView.setController(
                Fresco.newDraweeControllerBuilder()
                        .setTapToRetryEnabled(true)
                        .setUri(data.getData())
                        .build());


    }


    public void SimpleToast(View view) {
        Toast.makeText(this, "Works", Toast.LENGTH_SHORT).show();
    }

}

