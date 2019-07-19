package com.example.toaster2;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import java.util.ArrayList;

import me.relex.photodraweeview.PhotoDraweeView;

public class Swiper extends AppCompatActivity {


    /*
     protected void onCreate(Bundle savedInstanceState) {

     @Override
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_swiper);

         if (savedInstanceState == null) {

             FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
             PhotoFragment fragment = new PhotoFragment(getIntent().getExtras().getStringArrayList("url"));
             transaction.replace(R.id.frame, fragment);
             transaction.commit();
         }
     }*/


    CarouselView customCarouselView;
    ArrayList<String> web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel);
        web = getIntent().getExtras().getStringArrayList("url");
        customCarouselView = (CarouselView) findViewById(R.id.customCarouselView);
        int NUMBER_OF_PAGES = web.size();
        customCarouselView.setPageCount(NUMBER_OF_PAGES);
        // set ViewListener for custom view
        customCarouselView.setViewListener(viewListener);
    }

    ViewListener viewListener = new ViewListener() {

        @Override
        public View setViewForPosition(int position) {
            View customView = getLayoutInflater().inflate(R.layout.pager_item, null);
            //set view attributes here
//            SimpleDraweeView draweeView = customView.findViewById(R.id.swiper_draw);
//
//            draweeView.setImageURI(web.get(position));

            PhotoDraweeView mPhotoDraweeView = customView.findViewById(R.id.swiper_draw);
            mPhotoDraweeView.setPhotoUri(Uri.parse(web.get(position)));

            return customView;
        }
    };
}
