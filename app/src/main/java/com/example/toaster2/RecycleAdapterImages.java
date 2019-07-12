package com.example.toaster2;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;

import java.util.ArrayList;

public class RecycleAdapterImages extends RecyclerView.Adapter  {


    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> imgURLs;

    RecycleAdapterImages(Context context, ArrayList<String> imgURLs) {
        super();
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = context;
        this.imgURLs = imgURLs;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = mInflater.inflate(R.layout.grid_view, null, false);
        // set the view's size, margins, paddings and layout parameters
        ImageViewHolder vh = new ImageViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
    //        Fresco.getImagePipeline().clearCaches();

        final SimpleDraweeView draweeView = (SimpleDraweeView) viewHolder.itemView.findViewById(R.id.img_view);

        imgURLs.set(i, imgURLs.get(i).replace(" ", "/"));

        // For files on device
        //        Uri imageUri = Uri.fromFile(new File(imgURLs.get(i)));

        draweeView.setImageURI(imgURLs.get(i));
        final String path = imgURLs.get(i);


        ControllerListener listener = new BaseControllerListener() {
            @Override
            public void onFinalImageSet(String id, Object imageInfo, Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                updateViewSize(draweeView, (ImageInfo) imageInfo);
            }

            @Override
            public void onIntermediateImageSet(String id, Object imageInfo) {
                super.onIntermediateImageSet(id, imageInfo);
                updateViewSize(draweeView, (ImageInfo) imageInfo);
            }
        };
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setLowResImageRequest(ImageRequest.fromUri("https://langleyinsuranceagents.com/wp-content/uploads/2018/09/image-placeholder.png"))
                .setImageRequest(ImageRequest.fromUri(path))
                .setUri(path)
                .setOldController(draweeView.getController())
                .setControllerListener(listener)
//                .setAutoPlayAnimations(true)
                .build();

        draweeView.setController(controller);
        draweeView.getHierarchy().setRetryImage(R.color.colorPrimary);
        draweeView.getHierarchy().setPlaceholderImage(R.color.colorPrimaryDark);


        // only for local files
//        String path=imgURLs.get(i).substring(7);
//        Bitmap myBitmap = BitmapFactory.decodeFile(path);



        Log.e("image url", "" + imgURLs.get(i));

//        viewHolder.itemView.requestLayout();
    }

    private void updateViewSize(SimpleDraweeView draweeView, ImageInfo imageInfo) {
        if (imageInfo != null) {
            draweeView.setAspectRatio((float) imageInfo.getWidth() / imageInfo.getHeight());
        }
    }

    @Override
    public int getItemCount() {
        return imgURLs.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        SimpleDraweeView draweeView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            draweeView = itemView.findViewById(R.id.img_view);
        }
    }
}

