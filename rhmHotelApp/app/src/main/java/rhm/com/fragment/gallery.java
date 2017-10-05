package rhm.com.fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import com.release.rhm.rhmhotelapp.R;
import rhm.com.adapters.MyGridAdapter;

public class gallery extends Fragment {

    private View myGalleryView;
    private GridView myGrid;

    public Integer[] randomPics = {
            R.drawable.about5,
            R.drawable.about6,
            R.drawable.about,
            R.drawable.bar1,
            R.drawable.bar2,
            R.drawable.chambrebg,
            R.drawable.simple1,
            R.drawable.simple2,
            R.drawable.simple3,
        //    R.drawable.double1,
            R.drawable.double2,
            R.drawable.suitejr1,
            R.drawable.suitejr2,
            R.drawable.suitejr3,
            R.drawable.suitejr4,
            R.drawable.suitejr5,
            R.drawable.suiteexe1,
            R.drawable.suiteexe2,
            R.drawable.suiteexe3
    };

    private ImageView expandedImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("La galerie");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Inflate the layout for this fragment
        this.myGalleryView =  inflater.inflate(R.layout.fragment_gallery, container, false);
        this.myGrid = (GridView)myGalleryView.findViewById(R.id.myGalleryGrid);

        return this.myGalleryView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
/*
        Runnable runnable = new Runnable() {
            @Override
            public void run() {*/
                myGrid.setAdapter(new MyGridAdapter(myGalleryView.getContext(),350));
           /* }
        };*/

        //getActivity().runOnUiThread(runnable);


        //new Thread(runnable).start();

        myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //On lance le viewPager
                PhotoViewPager pvp = new PhotoViewPager();
                pvp.setPosition(position);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, pvp).commit();
            }
        });
    }
}