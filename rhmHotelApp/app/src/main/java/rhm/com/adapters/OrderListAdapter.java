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
import rhm.com.entities.Order;

/**
 * Created by alvin.ondzounga on 02/07/2017.
 */

public class OrderListAdapter extends ArrayAdapter {
    private Context context;
    private List<Order> orders;

    public OrderListAdapter(Context context, List<Order> o) {
        super(context, R.layout.custom_room_ordered, o);
        this.context = context;
        this.orders = o;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layout.inflate(R.layout.custom_room_ordered,parent,false);
        TextView typename = (TextView)v.findViewById(R.id.typeName);
        typename.setText(orders.get(position).getProduct().getTypeRoom());
        TextView p = (TextView)v.findViewById(R.id.p);
        TextView quantity = (TextView)v.findViewById(R.id.quantity);
        p.setText(orders.get(position).getProductPrice().toString());
        quantity.setText(""+(orders.get(position).getQuantity()));
                //leftIcon.setImageResource(R.drawable.cogs);
        //editIcon.setImageResource(R.drawable.edit);
        return v;
    }
}