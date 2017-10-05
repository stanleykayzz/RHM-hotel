package rhm.com.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import java.util.List;
import android.widget.*;
import android.content.*;
import android.view.*;
import com.release.rhm.rhmhotelapp.R;
import rhm.com.entities.Chambre;
import rhm.com.fragment.*;

/**
 * Created by alvin.ondzounga on 10/06/2017.
 */

public class RoomListAdapter extends ArrayAdapter<Chambre> {

    private Context context;
    private List<Chambre> chambres;
    private SharedPreferences sharedPreferences;
    private TextView currencyLabel;

    public RoomListAdapter(Context context, List<Chambre> Chambres) {
        super(context, R.layout.custom_room_list_layout, Chambres);
        this.context = context;
        this.chambres = Chambres;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layout.inflate(R.layout.custom_room_list_layout,parent,false);
        ImageView picture = (ImageView) v.findViewById(R.id.imageView2);
        currencyLabel = (TextView) v.findViewById(R.id.labelCurrency);
        checkCurrency();
        picture.setImageResource(chambres.get(position).getPhoto());
        TextView textViewName = (TextView) v.findViewById(R.id.RoomName);
        textViewName.setText(chambres.get(position).getTypeRoom());
        TextView textViewPrice = (TextView)v.findViewById(R.id.roomPrice);
        textViewPrice.setText(String.valueOf(chambres.get(position).getPrice()));
        return v;
    }

    private void checkCurrency() {
        this.sharedPreferences = getContext().getSharedPreferences("myUser",0);
        if(this.sharedPreferences.getString("currency","XAF") =="EUR"){
            this.currencyLabel.setText(this.sharedPreferences.getString("currency","XAF"));
        }
        else if(this.sharedPreferences.getString("currency","XAF") =="USD"){
            this.currencyLabel.setText(this.sharedPreferences.getString("currency","USD"));
        }
        else if(this.sharedPreferences.getString("currency","XAF") =="XAF"){
            this.currencyLabel.setText(this.sharedPreferences.getString("currency","XAF"));
        }
    }
    }