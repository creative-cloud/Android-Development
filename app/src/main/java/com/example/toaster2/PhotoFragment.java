package com.example.toaster2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class PhotoFragment extends Fragment {

    int position;
    ViewPager vp;
    ArrayList <String> web;


    PhotoFragment()
    {}


    PhotoFragment( ArrayList<String> web) {
        this.position = position;
        this.web=web;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sample, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vp=view.findViewById(R.id.viewpager);
        vp.setAdapter(new PhotoAdapter());
    }

    class PhotoAdapter extends PagerAdapter
    {

        @Override
        public int getCount() {
            return 15;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return false;       //check
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            View view = getActivity().getLayoutInflater().inflate(R.layout.pager_item,
                    container, false);
            container.addView(view);
            SimpleDraweeView simpleDraweeView=view.findViewById(R.id.img_view);
            simpleDraweeView.setImageURI(web.get(position));
            Log.e("Setting url",""+web.get(position));
            return view;
        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);

        }
    }

}
