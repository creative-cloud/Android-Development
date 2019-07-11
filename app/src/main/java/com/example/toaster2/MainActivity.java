package com.example.toaster2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;



import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        final View view = findViewById(android.R.id.content);
        Uri uri = Uri.parse("https://homepages.cae.wisc.edu/~ece533/images/serrano.png");

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);


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

                    //local images
//
//                    File fileDirectory = new File("/storage/4DD2-986A/Scenery/");
//                    File[] dirFiles = fileDirectory.listFiles();
//                    if (dirFiles.length != 0) {
//                        for (File file : dirFiles) {
//                            web.add("file://"+file.toString());
//                        }
//                    }


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


                    RecycleAdapter recycleAdapter = new RecycleAdapter(MainActivity.this, web);
                    recyclerView.setAdapter(recycleAdapter);

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

    private boolean ensureExternalStoragePermissionGranted() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED ) {         //if doesn't already have permission
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.INTERNET},
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

    public static int calculateNoOfColumns(Context context, float columnWidthDp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
        return noOfColumns;
    }


}






