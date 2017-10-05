package rhm.com.fragment;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.release.rhm.rhmhotelapp.R;

import rhm.com.adapters.MyGridAdapter;
import rhm.com.converter.Currency;

/**
 * Created by alvin.ondzounga on 15/06/2017.
 */

public class DetailedRoom extends Fragment {
    private SharedPreferences sharedPreferences;
    private ImageView detailPic;
    private TextView price;
    private TextView roomType;
    private Spinner currency;
    private MyGridAdapter myAdapter;
    private GridView littleGrid;
    private double myPrice = 0;
    private double myXafPrice = 0;
    private double myEuroPrice = 0;
    private double myDollarPrice = 0;

    public DetailedRoom() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Détails des chambres");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Bundle bundle = this.getArguments();
        View view = inflater.inflate(R.layout.room_details_layout, container, false);
        detailPic = (ImageView) view.findViewById(R.id.pic);
        this.price = (TextView) view.findViewById(R.id.priceValue);
        this.roomType = (TextView) view.findViewById(R.id.roomType);
        this.currency = (Spinner) view.findViewById(R.id.currency);
        this.sharedPreferences = getContext().getSharedPreferences("myUser",0);
        this.price.setText(bundle.getString("LePrix"));
        this.myPrice = Double.parseDouble(bundle.getString("LePrix"));

        checkCurrency();


        this.roomType.setText(bundle.getString("LeType"));
        this.detailPic.setImageResource(bundle.getInt("LaPhoto"));
        this.littleGrid = (GridView) view.findViewById(R.id.littleGridView);
        myAdapter = new MyGridAdapter(view.getContext(), 110, roomType.getText().toString());
        littleGrid.setAdapter(myAdapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.littleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                detailPic.setImageResource(myAdapter.mThumbIds[position]);
            }
        });

        this.currency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Toast.makeText(getContext(), "Prix en Francs CFA", Toast.LENGTH_SHORT).show();
                    price.setText("" + myPrice);
                } else if (position == 1) {
                    Toast.makeText(getContext(), "Prix en Euro ", Toast.LENGTH_SHORT).show();
                    price.setText(String.format("%.2f", myEuroPrice));
                } else if (position == 2) {
                    Toast.makeText(getContext(), "Prix en Dollar ", Toast.LENGTH_SHORT).show();
                    price.setText(String.format("%.2f", myDollarPrice));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void checkCurrency() {
        if(sharedPreferences.getString("currency","XAF") == "XAF"){
            this.myEuroPrice = Currency.convertXafToEuro(this.myPrice);
            this.myXafPrice = (this.myPrice);
            this.myDollarPrice = Currency.convertXafToDollar(this.myPrice);
        }
        else if(sharedPreferences.getString("currency","XAF") == "EUR"){
            this.myEuroPrice = (this.myPrice);
            this.myXafPrice = Currency.convertEuroToXaf(this.myPrice);
            this.myDollarPrice = Currency.convertEuroToDollar(this.myPrice);
        }
        else if(sharedPreferences.getString("currency","XAF") == "USD"){
            this.myEuroPrice = Currency.convertDollarToEuro(this.myPrice);
            this.myXafPrice = Currency.convertDollarToXaf(this.myPrice);
        }
    }

    private void setAllRoomPrice(String s, String s1, String s2, String s3) {

    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public ImageView getDetailPic() {
        return detailPic;
    }

    public void setDetailPic(ImageView detailPic) {
        this.detailPic = detailPic;
    }

    public TextView getPrice() {
        return price;
    }

    public void setPrice(TextView price) {
        this.price = price;
    }

    public TextView getRoomType() {
        return roomType;
    }

    public void setRoomType(TextView roomType) {
        this.roomType = roomType;
    }

    public Spinner getCurrency() {
        return currency;
    }

    public void setCurrency(Spinner currency) {
        this.currency = currency;
    }

    public MyGridAdapter getMyAdapter() {
        return myAdapter;
    }

    public void setMyAdapter(MyGridAdapter myAdapter) {
        this.myAdapter = myAdapter;
    }

    public GridView getLittleGrid() {
        return littleGrid;
    }

    public void setLittleGrid(GridView littleGrid) {
        this.littleGrid = littleGrid;
    }

    public double getMyPrice() {
        return myPrice;
    }

    public void setMyPrice(double myPrice) {
        this.myPrice = myPrice;
    }

    public double getMyXafPrice() {
        return myXafPrice;
    }

    public void setMyXafPrice(double myXafPrice) {
        this.myXafPrice = myXafPrice;
    }

    public double getMyEuroPrice() {
        return myEuroPrice;
    }

    public void setMyEuroPrice(double myEuroPrice) {
        this.myEuroPrice = myEuroPrice;
    }

    public double getMyDollarPrice() {
        return myDollarPrice;
    }

    public void setMyDollarPrice(double myDollarPrice) {
        this.myDollarPrice = myDollarPrice;
    }
}
