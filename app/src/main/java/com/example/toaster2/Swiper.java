package com.example.toaster2;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Swipper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel);

        if (savedInstanceState == null) {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            PhotoFragment fragment = new PhotoFragment(getIntent().getExtras().getStringArrayList("url"));
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }
    }
}
