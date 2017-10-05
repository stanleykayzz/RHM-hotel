package rhm.com.fragment;

import android.content.pm.ActivityInfo;
import android.graphics.Matrix;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.release.rhm.rhmhotelapp.R;
import rhm.com.adapters.MyCustomPageAdapter;

/**
 * Created by alvin.ondzounga on 20/06/2017.
 */

public class PhotoViewPager extends Fragment{

    private int position;
     ViewPager mViewPager;

    public PhotoViewPager(){
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View photoviewpager = inflater.inflate(R.layout.viewpager_swipe_layout, container, false);
        getActivity().setTitle("Aperçu");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        MyCustomPageAdapter mCustomPagerAdapter = new MyCustomPageAdapter(getContext());
        mViewPager = (ViewPager)photoviewpager.findViewById(R.id.viewpager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        return photoviewpager;

    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager.setCurrentItem(position);
    }

    public void setPosition(int p){
        this.position = p;
    }
    public int getPosition(){
        return this.position;
    }

}
