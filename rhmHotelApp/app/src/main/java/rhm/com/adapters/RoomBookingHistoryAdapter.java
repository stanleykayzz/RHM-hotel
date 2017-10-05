package rhm.com.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.release.rhm.rhmhotelapp.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import rhm.com.entities.RoomBooking;

/**
 * Created by stanley on 02/09/2017.
 */

public class RoomBookingHistoryAdapter extends ArrayAdapter {
    private Context context;
    private List<RoomBooking> roomBookingList;

    public RoomBookingHistoryAdapter(Context context, List<RoomBooking> bookingList) {
        super(context, R.layout.custom_room_booking_history,bookingList);
        this.context = context;
        this.roomBookingList = bookingList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layout.inflate(R.layout.custom_room_booking_history,parent,false);
        TextView ref = view.findViewById(R.id.ref);
        TextView start = view.findViewById(R.id.arrived);
        TextView end = view.findViewById(R.id.departure);
        // TextView chambre = view.findViewById(R.id.roomBills);
        TextView categ = view.findViewById(R.id.restaurantBill);
        TextView prix = view.findViewById(R.id.FestiveRoomBills);

        ref.setText(roomBookingList.get(position).getRef().toString());
        start.setText(roomBookingList.get(position).getStart());
        //chambre.setText(""+roomBookingList.get(position).getQuantity());
        end.setText(roomBookingList.get(position).getEnd());
        categ.setText(roomBookingList.get(position).getRoomCategory());
        prix.setText(""+roomBookingList.get(position).getPrice());
        return view;
    }

    private Date convertLongToDate (long l){
        long tmp = l;
        Date d = new Date(tmp);
        return d;
    }

    private String convertDateToString (Date d){
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
        try{
            dateString = sdfr.format( d );
        }catch (Exception ex ){
            System.out.println(ex);
        }
        return dateString;
    }

}
