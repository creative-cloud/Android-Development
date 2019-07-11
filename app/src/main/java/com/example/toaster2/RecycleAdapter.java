package com.example.toaster2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> imgURLs;

    RecycleAdapter(Context context, ArrayList<String> imgURLs) {
        super();
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = context;
        this.imgURLs = imgURLs;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = mInflater.inflate(R.layout.grid_view, null, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Fresco.getImagePipeline().clearCaches();
        SimpleDraweeView draweeView = (SimpleDraweeView) viewHolder.itemView.findViewById(R.id.img_view);
        draweeView.getHierarchy().setRetryImage(R.color.colorPrimary);
        imgURLs.set(i, imgURLs.get(i).replace(" ", "/"));
        Uri imageUri = Uri.fromFile(new File(imgURLs.get(i)));// For files on device
        draweeView.setImageURI(imgURLs.get(i));
        String path=imgURLs.get(i).substring(7);
        Bitmap myBitmap = BitmapFactory.decodeFile(path);
                draweeView.setAspectRatio((float)myBitmap.getWidth()/myBitmap.getHeight());
//        draweeView.setAspectRatio(imgURLs.get(i).gett/imgURLs.get(i).);
        Log.e("image url",""+imgURLs.get(i));
    }

    @Override
    public int getItemCount() {
        return imgURLs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        SimpleDraweeView draweeView;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            draweeView = itemView.findViewById(R.id.img_view);
        }


    }
}

