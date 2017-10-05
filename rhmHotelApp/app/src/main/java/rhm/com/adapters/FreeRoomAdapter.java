package rhm.com.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.release.rhm.rhmhotelapp.R;

import java.util.List;

import rhm.com.entities.FreeRoomInformation;

/**
 * Created by stanley on 28/09/2017.
 */

public class FreeRoomAdapter extends ArrayAdapter<FreeRoomInformation> {

    private Context context;
    private List<FreeRoomInformation> infos;

    private View v;
    private TextView typeRoomName;
    private TextView costByNight;
    private TextView costOfStay;
    private TextView countDays;
    private TextView countAvailable;
    private ImageView pic;

    public FreeRoomAdapter(Context context, List<FreeRoomInformation> list) {
        super(context, R.layout.custom_free_room, list);
        this.context = context;
        this.infos = list;
    }

    @Nullable
    @Override
    public FreeRoomInformation getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = layout.inflate(R.layout.custom_free_room, parent, false);
        typeRoomName = v.findViewById(R.id.typeName);
        costByNight = v.findViewById(R.id.costByNight);
        costOfStay = v.findViewById(R.id.costOfStay);
        countDays = v.findViewById(R.id.countDays);
        countAvailable = (TextView) v.findViewById(R.id.countAvailable);
        pic = v.findViewById(R.id.pic);
        typeRoomName.setText(infos.get(position).getTypeRoomName());
        costByNight.setText(infos.get(position).getPriceByNightToString());
        costOfStay.setText(infos.get(position).getCountNightsPriceToString());
        countDays.setText(infos.get(position).getCountDaysToString());
        countAvailable.setText(infos.get(position).getCountAvailableToString());
        //pic.setText(infos.get(position).getCountOfRoom());

        return v;
    }

    public void test() {
        Toast.makeText(getContext(), "RETOSTER", Toast.LENGTH_LONG).show();
    }

    @NonNull
    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<FreeRoomInformation> getInfos() {
        return infos;
    }

    public void setInfos(List<FreeRoomInformation> infos) {
        this.infos = infos;
    }
}
