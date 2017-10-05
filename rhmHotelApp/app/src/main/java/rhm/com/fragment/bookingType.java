package rhm.com.fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.release.rhm.rhmhotelapp.R;

import java.util.ArrayList;
import java.util.List;

import rhm.com.adapters.BookingTypeAdapter;

public class bookingType extends Fragment {


    private ListView listView;
    private TextView textView;

    public bookingType() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_booking_type, container, false);
        listView =  v.findViewById(R.id.bookingType);
        textView =  v.findViewById(R.id.bookingTypeLabel);
        getActivity().setTitle("Types de réservations");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        List<String> bookingTypeList = new ArrayList<String>();
        bookingTypeList.add("Réservation de chambres");
        bookingTypeList.add("Réservation de Tables du Restaurant");
        bookingTypeList.add("Réservation de la salle des fêtes");

        BookingTypeAdapter MyAdapter = new BookingTypeAdapter(getContext(), bookingTypeList);

        listView.setAdapter(MyAdapter);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, new bookingRoom()).commit();
                } else if (position == 1) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, new bookingTable()).commit();
                } else if (position == 2) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, new bookingPartyRoom()).commit();
                }
            }
        });
    }
}