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
import rhm.com.entities.ClientInformation;

/**
 * Created by alvin.ondzounga on 09/07/2017.
 */

public class BookingTypeAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> infos;

    public BookingTypeAdapter(Context context, List<String> i) {
        super(context, R.layout.booking_type, i);
        this.context = context;
        this.infos = i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layout.inflate(R.layout.booking_type,parent,false);
        TextView title = (TextView) v.findViewById(R.id.bookingTypeLabel);
        title.setText(infos.get(position).toString());
        return v;
    }
}
