package rhm.com.adapters;

import android.content.Context;
import android.graphics.Matrix;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;
import com.release.rhm.rhmhotelapp.R;

/**
 * Created by alvin.ondzounga on 20/06/2017.
 */

public class MyCustomPageAdapter extends PagerAdapter{
    ImageView imageView;
    Context mContext;
    LayoutInflater mLayoutInflater;
    private Integer[] mResources = {
            R.drawable.about5,
            R.drawable.about6,
            R.drawable.about,
            R.drawable.bar1,
            R.drawable.bar2,
            R.drawable.bar3,
            R.drawable.chambrebg,
            R.drawable.contact,
            R.drawable.contact1,
            R.drawable.contact3,
            R.drawable.contact4,
            R.drawable.contact5,
            R.drawable.contact6,
            R.drawable.double1,
            R.drawable.double2,
            R.drawable.double3,
            R.drawable.double4,
            R.drawable.simple1,
            R.drawable.simple2,
            R.drawable.simple3,
            R.drawable.simple4,
            R.drawable.simple5,
            R.drawable.simple6,
            R.drawable.suiteexe1,
            R.drawable.suiteexe3,
            R.drawable.suiteexe2,
            R.drawable.suiteexe4,
            R.drawable.suiteexe5,
            R.drawable.suiteexe6,
            R.drawable.suiteexe17,
            R.drawable.suitejr1,
            R.drawable.suitejr2,
            R.drawable.suitejr3,
            R.drawable.suitejr4,
            R.drawable.suitejr5,
            R.drawable.suitejr6,
            R.drawable.suitejr7,
            R.drawable.suitejr8
    };
    public MyCustomPageAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.view_pager_items_layout, container, false);

        imageView = (ImageView) itemView.findViewById(R.id.viewPagerImage);
        imageView.setImageResource(mResources[position]);
        imageView.setOnTouchListener(new ImageMatrixTouchHandler(itemView.getContext()));

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public LayoutInflater getmLayoutInflater() {
        return mLayoutInflater;
    }

    public void setmLayoutInflater(LayoutInflater mLayoutInflater) {
        this.mLayoutInflater = mLayoutInflater;
    }

    public Integer[] getmResources() {
        return mResources;
    }

    public void setmResources(Integer[] mResources) {
        this.mResources = mResources;
    }
}
