package com.example.toaster2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

//import vn.tungdx.mediapicker.MediaOptions;
//import vn.tungdx.mediapicker.activities.MediaPickerActivity;
//import vn.tungdx.mediapicker.MediaItem;
//import vn.tungdx.mediapicker.MediaOptions;
//import vn.tungdx.mediapicker.activities.MediaPickerActivity;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    //    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        final View view = findViewById(android.R.id.content);
        //Uri uri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/master/docs/static/logo.png");
        Uri uri = Uri.parse("https://homepages.cae.wisc.edu/~ece533/images/serrano.png");

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), calculateNoOfColumns(this,180));
        recyclerView.setLayoutManager(gridLayoutManager);


        Button Clicked = (Button) findViewById(R.id.button1);

        Clicked.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (!ensureExternalStoragePermissionGranted()) {
                    Toast.makeText(getBaseContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    return;
                }


                //start grid

                ArrayAdapter<String> adapter;
                ArrayList<String> web = new ArrayList<String>();


                Cursor cursor = null;

                try {

            /*cursor = MediaStore.Images.Media.query(
                    getContentResolver(),
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null,
                    null, null,
                    MediaStore.Images.Media.DATE_TAKEN + " DESC");
            if (cursor != null) {
                int dataColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

                while (cursor.moveToNext()) {
                    String path = cursor.getString(dataColumn);

                    if (path == null || path.length() == 0) {
                        continue;
                    }
                    if (path.equals("in"))
                        continue;
                    web.add(path);
*/
                    //Grid adaptor for image

                    File fileDirectory = new File("/storage/4DD2-986A/Scenery/");
                    File[] dirFiles = fileDirectory.listFiles();    
                    if (dirFiles.length != 0) {
                        for (File file : dirFiles) {
                            web.add("file://"+file.toString());
                        }
                    }


                    RecycleAdapter recycleAdapter = new RecycleAdapter(MainActivity.this, web);
                    recyclerView.setAdapter(recycleAdapter);

//                }
//            }


            } catch(
            Throwable e)

            {
                e.printStackTrace();
            } finally

            {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    });


}


/*

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println(data.getData());
        View view = findViewById(android.R.id.content);
        SimpleDraweeView draweeView = (SimpleDraweeView) view.findViewById(R.id.drawee_view);
        draweeView.getHierarchy().setRetryImage(R.color.colorPrimary);
        draweeView.setController(
                Fresco.newDraweeControllerBuilder()
                        .setTapToRetryEnabled(true)
                        .setUri(data.getData())
                        .build());


    }
*/

    private boolean ensureExternalStoragePermissionGranted() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {         //if doesn't already have permission
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    0);
            return false;
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //TODO: add method call to grid view method

            Toast.makeText(getBaseContext(), "Permission Granted", Toast.LENGTH_SHORT).show();

        }

    }

    public static int calculateNoOfColumns(Context context, float columnWidthDp) { // For example columnWidthdp=180
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
        return noOfColumns;
    }


}






