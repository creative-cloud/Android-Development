package com.example.toaster2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        //View view=getApplicationContext(this)
        final View view=findViewById(android.R.id.content);
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

       final GridView gv=findViewById(R.id.gridView);

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

//        MediaOptions.Builder builder = new MediaOptions.Builder();
//        MediaOptions options = builder.selectPhoto().canSelectMultiPhoto(true).build();
//        MediaPickerActivity.open(this, 100, options);
//        MediaPickerActivity.open

        Clicked=(Button)findViewById(R.id.button2);

        Clicked.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                    System.out.println("in here");
                    //System.out.println(ensureExternalStoragePermissionGranted());
                if (!ensureExternalStoragePermissionGranted()) {    //false to stop, true to open intent
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                }

//                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                intent.setType("image/*");
//                startActivityForResult(intent, 1);

                ArrayAdapter<String> adapter;
                ArrayList<String> web = new ArrayList<String>();


                Cursor cursor = null;

                    try {
                        cursor = MediaStore.Images.Media.query(
                                getContentResolver(),
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null,
                                null, null,
                                MediaStore.Images.Media.DATE_TAKEN + " DESC");
                        if (cursor != null) {
                            int imageIdColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID);
                            int bucketIdColumn = cursor
                                    .getColumnIndex(MediaStore.Images.Media.BUCKET_ID);
                            int bucketNameColumn = cursor
                                    .getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
                            int dataColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                            int dateColumn = cursor
                                    .getColumnIndex(MediaStore.Images.Media.DATE_MODIFIED);
                            int orientationColumn = cursor
                                    .getColumnIndex(MediaStore.Images.Media.ORIENTATION);
                            int mimeTypeColumn = cursor.getColumnIndex(MediaStore.Files.FileColumns.MIME_TYPE);

                            int size = cursor.getColumnIndex(MediaStore.Images.Media.SIZE);
                            int namecol = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);

                            while (cursor.moveToNext()) {
                                 int imageId = cursor.getInt(imageIdColumn);
                                int bucketId = cursor.getInt(bucketIdColumn);
                                String bucketName = cursor.getString(bucketNameColumn);
                                String path = cursor.getString(dataColumn);
                                long dateTaken = cursor.getLong(dateColumn);
                                int orientation = cursor.getInt(orientationColumn);
                                String mimeType = cursor.getString(mimeTypeColumn);
                                String sizefile = cursor.getString(size);
                                String name = cursor.getString(namecol);

                                if (path == null || path.length() == 0) {
                                    continue;
                                }
                                if(path.equals("in"))
                                    continue;
                                web.add(path);
//                                display(path, view);

                                //System.out.println(path);

                                //Grid adaptor for image
                                // s
                                //TODO: add method call to grid view

//                                break;
                            }
                            System.out.println("here---------------------------------------------------------------------");
                            System.out.println(web);
                            GridAdapter ga=new GridAdapter(getApplicationContext(),web);
//                            adapter = new ArrayAdapter<String>(getApplicationContext(),
//                                    R.id.drawee_view,  web);
                            gv.setAdapter(ga);

                        }
                    } catch (Throwable e) {
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        System.out.println(data.getData());
        View view=findViewById(android.R.id.content);
        SimpleDraweeView draweeView = (SimpleDraweeView)view.findViewById(R.id.drawee_view);
        draweeView.getHierarchy().setRetryImage(R.color.colorPrimary);
        draweeView.setController(
                Fresco.newDraweeControllerBuilder()
                        .setTapToRetryEnabled(true)
                        .setUri(data.getData())
                        .build());


    }


    public void SimpleToast(View view) {
        Toast.makeText(this, "Works", Toast.LENGTH_SHORT).show();
    }

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

//            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//            intent.addCategory(Intent.CATEGORY_OPENABLE);
//            intent.setType("image/*");
//            startActivityForResult(intent, 1);
//            setOnClickListener(this.findViewById(android.R.id.content));
            //TODO: add method call to grid view method

        }

    }






}






