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
import rhm.com.entities.RestaurantBooking;

/**
 * Created by stanley on 02/09/2017.
 */

public class RestaurantBookingHistoryAdapter extends ArrayAdapter {
    private Context context;
    private List<RestaurantBooking> restaurantBookings;


    public RestaurantBookingHistoryAdapter(Context context, List<RestaurantBooking> restaurantBookings) {
        super(context, R.layout.custom_restaurant_booking_history,restaurantBookings);
        this.context = context;
        this.restaurantBookings = restaurantBookings;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layout.inflate(R.layout.custom_restaurant_booking_history,parent,false);
        TextView date = view.findViewById(R.id.date);
        TextView places = view.findViewById(R.id.place);

        date.setText(restaurantBookings.get(position).getDate());
        places.setText(restaurantBookings.get(position).getPlace());
        return view;
    }

}
