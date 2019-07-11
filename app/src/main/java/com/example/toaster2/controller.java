package com.example.toaster2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
//
//import com.facebook.drawee.view.SimpleDraweeView;
//import com.facebook.fresco.samples.showcase.BaseShowcaseFragment;
//import com.facebook.fresco.samples.showcase.R;
//import com.facebook.fresco.sampl  es.showcase.misc.ImageUriProvider;

public class controller extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
    }

    private Context context;
    private String msg="Default";
    private int bg= Color.GRAY;
    private float textSize=17;
    private int duration=0;
    private int img=0;


    public controller setImg(int img) {
        this.img = img;
        return this;
    }


    public controller setMessage(String message) {
        this.msg = message;
        return this;
    }

    public controller setContext(Context context) {
        this.context = context;
        return this;
    }

    public controller setColor(int color) {
        this.bg = color;
        return this;
    }

    public controller setTextSize(int size)
    {
        this.textSize=size;
        return this;
    }

    public controller setDuration(int duration) {
        this.duration = duration;
        return this;
    }


    public void show()
    {

        View customToastroot=View.inflate(context,R.layout.custom_toast,null);
        Toast cust =new Toast(this.context);
        cust.setView(customToastroot);

        TextView text = (TextView) customToastroot.findViewById(R.id.textv);
        text.setText(this.msg);
        text.setTextColor(Color.WHITE);
        text.setShadowLayer(0.5f, 1,1,0xBB000000);
        text.setTextSize(this.textSize);

        ImageView image=(ImageView) customToastroot.findViewById(R.id.imgv);
        image.setImageResource(this.img);

        if(this.img==0) {
            image.setVisibility(View.GONE);
        }



        customToastroot.setBackgroundResource(R.drawable.draw_bg);
        GradientDrawable bgShape = (GradientDrawable)customToastroot.getBackground();
        bgShape.setColor(this.bg);
        cust.setDuration(this.duration);

        cust.show();

    }



}
