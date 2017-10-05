package rhm.com.fragment;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.release.rhm.rhmhotelapp.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import rhm.com.adapters.FestiveRoomBookingHistoryAdapter;
import rhm.com.converter.DateConverter;
import rhm.com.entities.FestiveRoomBooking;
import rhm.com.entities.RoomCategory;

public class festiveRoomBookingHistory extends Fragment {
    private ListView listView;
    private List<FestiveRoomBooking> festiveRoomBookingList;
    private Set<RoomCategory> roomCategories;
    private RequestQueue requestQueue;
    private String url = "https://residencedeshautsdemenaye.fr/api/festiveRoomBooking/getByIdClient?token=";
    private String token;
    private SharedPreferences sharedPreferences;

    public festiveRoomBookingHistory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_festive_room_booking_history, container, false);
        getActivity().setTitle("Vos commandes");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.listView = v.findViewById(R.id.listView);
        this.festiveRoomBookingList = new ArrayList<>();
        this.sharedPreferences = getContext().getSharedPreferences("myUser", 0);
        this.token = sharedPreferences.getString("token", null);
        getDataFromServer();
        return v;
    }

    private void getDataFromServer() {
        setRequestQueue(Volley.newRequestQueue(getContext()));
        setFestiveRoomBookingList(new ArrayList<FestiveRoomBooking>());
        setUrl(getUrl() + getToken());
        StringRequest request = new StringRequest(Request.Method.GET, getUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        List<FestiveRoomBooking> myList = new ArrayList<>();
                        try {
                            JSONArray jsonArr = new JSONArray(response);
                            for (int i = 0; i < jsonArr.length(); i++) {
                                JSONObject jsonObj = jsonArr.getJSONObject(i);
                                myList.add(new FestiveRoomBooking((long) jsonObj.getLong("id"),
                                        DateConverter.convertDateToString(DateConverter.convertLongToDate(jsonObj.getLong("dateStart"))),
                                        DateConverter.convertDateToString(DateConverter.convertLongToDate(jsonObj.getLong("dateEnd"))),
                                        200.0
                                ));

                            }
                            setFestiveRoomBookingList(myList);
                            FestiveRoomBookingHistoryAdapter festiveRoomBookingHistoryAdapter = new FestiveRoomBookingHistoryAdapter(getContext(), getFestiveRoomBookingList());
                            getListView().setAdapter(festiveRoomBookingHistoryAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        getRequestQueue().add(request);
    }

    //private getcALCULATEDpRICEfROMsERVER

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public List<FestiveRoomBooking> getFestiveRoomBookingList() {
        return festiveRoomBookingList;
    }

    public void setFestiveRoomBookingList(List<FestiveRoomBooking> festiveRoomBookingList) {
        this.festiveRoomBookingList = festiveRoomBookingList;
    }

    public Set<RoomCategory> getRoomCategories() {
        return roomCategories;
    }

    public void setRoomCategories(Set<RoomCategory> roomCategories) {
        this.roomCategories = roomCategories;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public void setRequestQueue(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }
}
