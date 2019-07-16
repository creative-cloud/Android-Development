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
import android.widget.TextView;

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
            return web.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return o == view;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            View view = getActivity().getLayoutInflater().inflate(R.layout.pager_item,
                    container, false);


            SimpleDraweeView simpleDraweeView=view.findViewById(R.id.carousel_draw);
            simpleDraweeView.setImageURI(web.get(position));
//            simpleDraweeView.setImageURI("https://media.wired.com/photos/5b8999943667562d3024c321/master/w_582,c_limit/trash2-01.jpg");
            Log.e("Setting url",""+web.get(position));

            TextView tv=view.findViewById(R.id.text2);
            tv.setText("Photo Number "+(position+1));
            container.addView(view);
            return view;
        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);

        }
    }

}
