package rhm.com.fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.release.rhm.rhmhotelapp.R;

import java.util.ArrayList;
import java.util.List;

import rhm.com.adapters.OrderListAdapter;
import rhm.com.entities.Chambre;
import rhm.com.entities.Order;

/**
 * Created by alvin.ondzounga on 02/07/2017.
 */

public class order extends Fragment {

    private static final String TAG = order.class.getSimpleName();
    private static final String PATH_TO_SERVER = "http://stanleysandbox.esy.es/";
    private TextView price, totalvalue, bookingFee;
    private Button goPay;
    private String url = "https://residencedeshautsdemenaye.fr/api/roomBooking/validate?refBookRoom=";
    private String start, end;
    private String clientToken;
    private Order o;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_summary, container, false);
        getActivity().setTitle("Bilan de votre commande");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ListView listView = (ListView) view.findViewById(R.id.orderList);
        Bundle bundle = this.getArguments();
        this.start = bundle.getString("OrderStart");
        this.end = bundle.getString("OrderEnd");
        List<Order> l = new ArrayList<Order>();

        o = (new Order(bundle.getString("OrderDate"),
                new Chambre(bundle.getString("OrderProductName"), bundle.getDouble("OrderPrice")),
                bundle.getInt("OrderQuantity")));

        l.add(o);
        o.calculateTotalOrderPrice();
        o.calculateBookingFee();
        goPay = (Button) view.findViewById(R.id.gopay);
        totalvalue = (TextView) view.findViewById(R.id.totalValue);
        bookingFee = (TextView) view.findViewById(R.id.fee);
        totalvalue.setText(o.getTotaleOderPrice().toString());
        bookingFee.setText(o.getBookingFee().toString());

        goPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, new payment()).commit();
            }
        });

        listView.setAdapter(new OrderListAdapter(view.getContext(), l));
        return view;
    }

    public Double convertcfaToEuro() {
        return (Double.parseDouble(totalvalue.getText().toString()) / 655.597);
    }
}