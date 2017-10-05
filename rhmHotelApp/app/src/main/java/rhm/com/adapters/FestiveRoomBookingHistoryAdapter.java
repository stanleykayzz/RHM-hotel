package rhm.com.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.release.rhm.rhmhotelapp.R;
import java.util.List;
import rhm.com.entities.FestiveRoomBooking;

/**
 * Created by stanley on 03/09/2017.
 */

public class FestiveRoomBookingHistoryAdapter extends ArrayAdapter {

    private Context context;
    private List<FestiveRoomBooking> festiveRoomBookings;

    public FestiveRoomBookingHistoryAdapter(Context context, List<FestiveRoomBooking> bookingList) {
        super(context, R.layout.custom_festive_room_booking_history, bookingList);
        this.context = context;
        this.festiveRoomBookings = bookingList;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layout.inflate(R.layout.custom_room_booking_history, parent, false);
        TextView ref = view.findViewById(R.id.ref);
        TextView start = view.findViewById(R.id.arrived);
        TextView end = view.findViewById(R.id.departure);
        TextView prix = view.findViewById(R.id.FestiveRoomBills);


        ref.setText(festiveRoomBookings.get(position).getId().toString());
        start.setText(festiveRoomBookings.get(position).getStart());
        end.setText(festiveRoomBookings.get(position).getEnd());
        prix.setText("" + festiveRoomBookings.get(position).getPrice());
        return view;
    }
}
