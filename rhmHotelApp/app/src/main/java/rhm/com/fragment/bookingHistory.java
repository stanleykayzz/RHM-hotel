package rhm.com.fragment;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.release.rhm.rhmhotelapp.R;

public class bookingHistory extends Fragment {

    private Button roomHistory, restaurantHistory , festiveRoomHistory;
    private View view;

    public bookingHistory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_booking_history, container, false);
        roomHistory = view.findViewById(R.id.roomBills);
        restaurantHistory = view.findViewById(R.id.restaurantBills);
        festiveRoomHistory = view.findViewById(R.id.FestiveRoomBills);

        getActivity().setTitle("Vos commandes");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        roomHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragMan = getFragmentManager();
                fragMan.beginTransaction().replace(R.id.tabLayoout, new roomBookingHistory()).commit();

            }
        });
        restaurantHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"On passe au fragment facture restaurant",Toast.LENGTH_SHORT).show();
                FragmentManager fragMan = getFragmentManager();
                fragMan.beginTransaction().replace(R.id.tabLayoout, new restaurantBookingHistory()).commit();
            }
        });
        festiveRoomHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"On passe au fragment facture salle de fete",Toast.LENGTH_SHORT).show();
                FragmentManager fragMan = getFragmentManager();
                fragMan.beginTransaction().replace(R.id.tabLayoout, new festiveRoomBookingHistory()).commit();
            }
        });

        return view;
    }
}
