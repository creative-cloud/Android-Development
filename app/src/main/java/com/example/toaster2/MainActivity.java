package com.example.toaster2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);

        setContentView(R.layout.activity_main);
        final View view = findViewById(android.R.id.content);
        Uri uri = Uri.parse("https://homepages.cae.wisc.edu/~ece533/images/serrano.png");

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        Button Clicked = (Button) findViewById(R.id.button1);

        if (!ensureExternalStoragePermissionGranted()) {
            Toast.makeText(getBaseContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
            return;
        }


        //start card
        final ArrayList<String> web = new ArrayList<String>();
        Cursor cursor = null;
        try {
            web.add("https://picsum.photos/id/249/400/200");
            web.add("https://picsum.photos/id/225/400/400");
            web.add("https://picsum.photos/id/82/40/100");
            web.add("https://picsum.photos/id/960/400/50");
            web.add("https://picsum.photos/id/151/500/200");
            web.add("https://picsum.photos/id/507/400/200");
            web.add("https://picsum.photos/id/1055/40/100");
            web.add("https://picsum.photos/id/468/200/200");
            web.add("https://picsum.photos/id/887/300/200");
            web.add("https://picsum.photos/id/618/500/200");
            web.add("https://picsum.photos/id/800/400/200");
            web.add("https://picsum.photos/id/452/400/200");
            web.add("https://picsum.photos/id/159/400/200");
            web.add("https://picsum.photos/id/500/400/20");
            web.add("https://picsum.photos/id/836/500/200");
            web.add("https://picsum.photos/id/350/400/200");
            web.add("https://picsum.photos/id/235/400/400");
            web.add("https://picsum.photos/id/344/40/100");
            web.add("https://picsum.photos/id/157/400/50");
            web.add("https://picsum.photos/id/200/500/200");
            web.add("https://simgbb.com/images/logo.png");
            web.add("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");


        } catch (
                Throwable e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        Clicked.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                RecycleAdapterImages recycleAdapterImages = new RecycleAdapterImages(MainActivity.this, web);
                recyclerView.setAdapter(recycleAdapterImages);
                recyclerView.setItemAnimator(null);

                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        ((StaggeredGridLayoutManager) recyclerView.getLayoutManager()).invalidateSpanAssignments();
                    }
                });
            }
        });


        Clicked = (Button) findViewById(R.id.button2);

        Clicked.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Log.e("testing click", "clicked");
                RecycleAdapterCards recycleAdapterCards = new RecycleAdapterCards(MainActivity.this, web);
                recyclerView.setAdapter(recycleAdapterCards);
                recyclerView.setItemAnimator(null);
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        ((StaggeredGridLayoutManager) recyclerView.getLayoutManager()).invalidateSpanAssignments();
                    }
                });
            }
        });

        Clicked = (Button) findViewById(R.id.button3);

        Clicked.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!ensureExternalStoragePermissionGranted()) {
                    Toast.makeText(getBaseContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getBaseContext(), Swiper.class);

                intent.putExtra("url", web);
                startActivity(intent);

            }

        });


        Clicked = (Button) findViewById(R.id.button4);

        Clicked.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!ensureExternalStoragePermissionGranted()) {
                    Toast.makeText(getBaseContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getBaseContext(), Carousel.class);

                intent.putExtra("url", web);
                startActivity(intent);

            }

        });

    }

    private boolean ensureExternalStoragePermissionGranted() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET)        //TODO:add read storage permission for internal file
                != PackageManager.PERMISSION_GRANTED) {         //if doesn't already have permission
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.INTERNET},
                    0);
            return false;
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //TODO: add method call to grid view method

            Toast.makeText(getBaseContext(), "Permission Granted", Toast.LENGTH_SHORT).show();

        }
    }

    public static int calculateNoOfColumns(Context context, float columnWidthDp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
        return noOfColumns;
    }

}