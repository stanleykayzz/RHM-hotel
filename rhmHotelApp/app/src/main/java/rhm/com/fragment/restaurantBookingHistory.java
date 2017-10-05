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
import rhm.com.adapters.RestaurantBookingHistoryAdapter;
import rhm.com.entities.RestaurantBooking;

public class restaurantBookingHistory extends Fragment {
    private ListView listView;
    private SharedPreferences sharedPreferences;
    private List<RestaurantBooking> restaurantBookings;
    private RequestQueue requestQueue;
    private String token;
    private String url = "https://residencedeshautsdemenaye.fr/api/restaurantTableBooking/getByIdClient?token=";

    public restaurantBookingHistory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_booking_history, container, false);
        getActivity().setTitle("Vos commandes");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.listView = view.findViewById(R.id.listBookingTbale);
        this.restaurantBookings = new ArrayList<RestaurantBooking>();
        this.sharedPreferences = getContext().getSharedPreferences("myUser", 0);
        this.token = sharedPreferences.getString("token", null);

        getDataFromServer();
        RestaurantBookingHistoryAdapter restaurantBookingHistoryAdapter = new RestaurantBookingHistoryAdapter(this.getContext(), getRestaurantBookings());
        this.listView.setAdapter(restaurantBookingHistoryAdapter);

        return view;
    }

    private void getDataFromServer() {
        setRequestQueue(Volley.newRequestQueue(getContext()));
        setRestaurantBookings(new ArrayList<RestaurantBooking>());
        setUrl(getUrl() + getToken());
        StringRequest request = new StringRequest(Request.Method.GET, getUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String myResponse = "{ \"myArray\":" + response.toString() + "}";
                        System.out.println("la reponse "+myResponse);

                        List<RestaurantBooking> myList = new ArrayList<>();
                        try {
                            JSONObject jsonObject = new JSONObject(myResponse);
                            JSONArray jArray = jsonObject.getJSONArray("myArray");
                            for (int i = 0; i < jArray.length(); i++) {
                                JSONObject jObj = jArray.getJSONObject(i);
                                System.out.println("date "+jObj.getString("date")+" number "+jObj.getString("number"));
                                myList.add(new RestaurantBooking("" + jObj.getJSONObject("date"), "" + jObj.getJSONObject("number")));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        setRestaurantBookings(myList);
                        System.out.println("liste de restaut contient "+getRestaurantBookings().size());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error is: " + error.toString());
            }
        });
        getRequestQueue().add(request);
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public List<RestaurantBooking> getRestaurantBookings() {
        return restaurantBookings;
    }

    public void setRestaurantBookings(List<RestaurantBooking> restaurantBookings) {
        this.restaurantBookings = restaurantBookings;
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
}