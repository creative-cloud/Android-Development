package com.example.toaster2;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> imgURLs;

    GridAdapter(Context context, ArrayList<String> imgURLs) {
        super(context,0, imgURLs);
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = context;
        this.imgURLs = imgURLs;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        /*
        Viewholder build pattern (Similar to recyclerview)
         */
//        final RecyclerView.ViewHolder holder;
        SimpleDraweeView holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.grid_view, null, true);
//             holder = new View();
        }

        holder= (SimpleDraweeView) convertView.findViewById(R.id.img_view);
        String imagePath= imgURLs.get(position);
        imagePath.replace(" ","/");
        imagePath="file://".concat(imagePath);
//        Fresco.newDraweeControllerBuilder(
//                        .setTapToRetryEnabled(true)
//                        .setUri(imagePath)
//                        .build());

        DraweeController controller= Fresco.newDraweeControllerBuilder()
                .setUri(imagePath)
                .setTapToRetryEnabled(true)
                .setTapToRetryEnabled(true)
                .build();

        holder.setController(controller);

//        holder.setImageURI(imagePath);
//        convertView.setTag(holder);

//        String imgURL = getItem(position);
//        holder.setImageURI(imgURL);
//        holder.setImageURI(imgURLs.get(0));


        return convertView;
    }

    public void display(String imagePath, View view, SimpleDraweeView holder)
    {
//        Uri uri=Uri.parse("https://homepages.cae.wisc.edu/~ece533/images/serrano.png");
//        Fresco.getImagePipeline().clearCaches();
//        SimpleDraweeView draweeView = (SimpleDraweeView)view.findViewById(R.id.drawee_view);
//        draweeView.getHierarchy().setRetryImage(R.color.colorPrimary);
//        Uri uri = new Uri.Builder()
//                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME) // "res"
//                .path(String.valueOf(imageID))
//                .build();
// uri looks like res:/123456789
        imagePath= imagePath.replace(" ","/");
        imagePath="file://".concat(imagePath);
        Uri imageUri= Uri.fromFile(new File(imagePath));// For files on device
//        draweeView.setImageURI("https://raw.githubusercontent.com/facebook/fresco/master/docs/static/logo.png");
//            draweeView.setImageURI(imageUri);
//        draweeView.setController(
//        draweeView.setImageURI("file:///storage/4DD2-986A/DCIM/Camera/20170527_190139.jpg");
//                Fresco.newDraweeControllerBuilder()
//                        .setTapToRetryEnabled(true)
////                        .setUri(uri)
//                        .build());

//        System.out.println(imagePath);
        System.out.println("in");

    }


}
