package rhm.com.adapters;

import android.content.Context;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.release.rhm.rhmhotelapp.R;

/**
 * Created by alvin.ondzounga on 03/06/2017.
 */

public class MyGridAdapter extends BaseAdapter{
    private Context mContext;
    private int size=500;
    private String value = "";

    //On crée un liste pour chaque type de chambre
    private Integer[] simpleRoom = {
            R.drawable.simple1,
            R.drawable.simple2,
            R.drawable.simple3,
            R.drawable.simple4,
            R.drawable.simple5,
            R.drawable.simple6
    };
    private Integer[] doubleRoom = {
            R.drawable.double1,
            R.drawable.double2,
            R.drawable.double3,
            R.drawable.double4
    };
    private Integer[] suiteJr = {
            R.drawable.suitejr1,
            R.drawable.suitejr2,
            R.drawable.suitejr3,
            R.drawable.suitejr4,
            R.drawable.suitejr5,
            R.drawable.suitejr6,
            R.drawable.suitejr7,
            R.drawable.suitejr8
    };
    private Integer[] suiteExec = {
            R.drawable.suiteexe1,
            R.drawable.suiteexe3,
            R.drawable.suiteexe2,
            R.drawable.suiteexe4,
            R.drawable.suiteexe5,
            R.drawable.suiteexe6,
            R.drawable.suiteexe17
    };

    private Integer[] randomPics = {
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

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer[] getSimpleRoom() {
        return simpleRoom;
    }

    public void setSimpleRoom(Integer[] simpleRoom) {
        this.simpleRoom = simpleRoom;
    }

    public Integer[] getDoubleRoom() {
        return doubleRoom;
    }

    public void setDoubleRoom(Integer[] doubleRoom) {
        this.doubleRoom = doubleRoom;
    }

    public Integer[] getSuiteJr() {
        return suiteJr;
    }

    public void setSuiteJr(Integer[] suiteJr) {
        this.suiteJr = suiteJr;
    }

    public Integer[] getSuiteExec() {
        return suiteExec;
    }

    public void setSuiteExec(Integer[] suiteExec) {
        this.suiteExec = suiteExec;
    }

    public Integer[] getRandomPics() {
        return randomPics;
    }

    public void setRandomPics(Integer[] randomPics) {
        this.randomPics = randomPics;
    }

    public Integer[] getmThumbIds() {
        return mThumbIds;
    }

    public void setmThumbIds(Integer[] mThumbIds) {
        this.mThumbIds = mThumbIds;
    }

    public Integer[] mThumbIds = {};

    public MyGridAdapter(Context c) {
        mContext = c;
        this.mThumbIds = this.randomPics;
    }
    public MyGridAdapter(Context c, int val) {
        mContext = c;
        this.size = val;
        this.mThumbIds = this.randomPics;
    }

    public MyGridAdapter(Context c, int v , String s){
        this.mContext = c;
        this.size = v;
        this.value = s;
        if(s == "Chambre Simple" ){
            this.mThumbIds = this.simpleRoom;
        }
        else if(s == "Chambre Double"){
            this.mThumbIds = this.doubleRoom;
        }
        else if(s == "Suite Junior"){
            this.mThumbIds = this.suiteJr;
        }
        else if(s == "Suite Exécutive"){
            this.mThumbIds = this.suiteExec;
        }
        else{
            this.mThumbIds = this.randomPics;
        }
    }
    public int getCount() {
        return mThumbIds.length;
    }
    public Object getItem(int position) {
        return null;
    }
    public long getItemId(int position) {
        return 0;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView picture;
        View grid = convertView;
        if (convertView == null) {
            picture = new ImageView(mContext);
            picture.setLayoutParams(new GridView.LayoutParams(this.size, this.size));
            picture.setScaleType(ImageView.ScaleType.CENTER_CROP);
            picture.setPadding(2, 2, 2, 2);
        } else {
            picture = (ImageView) convertView;
        }

        if(this.value == "Chambre Simple")
            this.mThumbIds = this.simpleRoom;
        else if(this.value == "Chambre Double")
            this.mThumbIds =this.doubleRoom;
        else if(this.value == "Suite Junior")
            this.mThumbIds =this.suiteJr;
        else if(this.value == "Suite Exécutive")
            this.mThumbIds =this.suiteExec;

        picture.setImageResource(this.mThumbIds[position]);
        return picture;
    }

}
