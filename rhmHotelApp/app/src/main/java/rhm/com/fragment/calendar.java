package rhm.com.fragment;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;
import com.release.rhm.rhmhotelapp.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class calendar extends Fragment {

    //private CalendarView calendar;
    private DatePicker calendar;
    private View v;
    private String title;
    private String selectedDate;
    public calendar() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        title = args.getString("title");

        v = inflater.inflate(R.layout.fragment_calendar, container, false);
        this.calendar = (DatePicker) v.findViewById(R.id.myCalendar);
        getActivity().setTitle(title+" date");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.calendar.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                    Toast.makeText(getContext(), i2+"/"+i1+"/"+i , Toast.LENGTH_LONG).show();
                    selectedDate = i2+"/"+i1+"/"+i;
                    Bundle bundle = new Bundle();
                    bundle.putString("checkin",selectedDate);
                    bookingRoom bookingRoom  = new bookingRoom();
                    bookingRoom.setArguments(bundle);
//                getActivity().getSupportFragmentManager().beginTransaction().remove(this.getClass()).commit();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, bookingRoom).commit();
                }
            });
        }
    }
}
