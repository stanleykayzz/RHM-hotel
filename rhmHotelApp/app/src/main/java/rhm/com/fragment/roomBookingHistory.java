package rhm.com.fragment;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.release.rhm.rhmhotelapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import rhm.com.adapters.RoomBookingHistoryAdapter;
import rhm.com.entities.RoomBooking;
import rhm.com.entities.RoomCategory;

public class roomBookingHistory extends Fragment {
    private ListView listView;
    private List<RoomBooking> roomBookings;
    private Set<RoomCategory> roomCategories;
    private RequestQueue requestQueue;
    private String url = "https://residencedeshautsdemenaye.fr/api/roomBooking/getListByIdClient?token=";
    private String urlForRoomList = "https://residencedeshautsdemenaye.fr/api/room/";
    private String token;
    private SharedPreferences sharedPreferences;

    public roomBookingHistory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_booking_history, container, false);
        getActivity().setTitle("Vos commandes");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.listView = view.findViewById(R.id.listRoomBooking);
        this.roomBookings = new ArrayList<>();
        this.roomCategories = new HashSet<>();
        this.sharedPreferences = getContext().getSharedPreferences("myUser", 0);
        this.token = sharedPreferences.getString("token", null);
        getRoomListFromServer();
        getDataFromServer();
        return view;
    }

    private void getRoomListFromServer() {
        setRequestQueue(Volley.newRequestQueue(getContext()));
        setRoomBookings(new ArrayList<RoomBooking>());
        StringRequest request = new StringRequest(Request.Method.GET, getUrlForRoomList(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Set<RoomCategory> myList = new HashSet<>();
                        try {
                            JSONArray jsonArr = new JSONArray(response);
                            for (int i = 0; i < jsonArr.length(); i++) {
                                JSONObject jsonObj = jsonArr.getJSONObject(i);
                                myList.add(new RoomCategory((long) jsonObj.getJSONObject("roomCategory").getLong("id"),
                                        jsonObj.getJSONObject("roomCategory").getString("name"),
                                        jsonObj.getJSONObject("roomCategory").getDouble("price")));
                            }
                            setRoomCategories(myList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
            }
        });
        getRequestQueue().add(request);
    }

    private void handleVolleyError(VolleyError error) {
        if (error instanceof TimeoutError || error instanceof NoConnectionError)
            Toast.makeText(getContext(), " code pas connecté", Toast.LENGTH_SHORT).show();
        else if (error instanceof AuthFailureError)
            Toast.makeText(getContext(), " Vous ne pouvez pas vous connecter a votre commpte", Toast.LENGTH_SHORT).show();
        else if (error instanceof ServerError)
            Toast.makeText(getContext(), " code pas connecté", Toast.LENGTH_SHORT).show();
        else if (error instanceof NetworkError)
            Toast.makeText(getContext(), " code pas connecté", Toast.LENGTH_SHORT).show();
        else if (error instanceof ParseError)
            Toast.makeText(getContext(), " code pas connecté", Toast.LENGTH_SHORT).show();/*
                else if(error instanceof NoConnectionError)
                    Toast.makeText(getContext()," code pas connecté",Toast.LENGTH_SHORT).show();*/
    }

    private void getDataFromServer() {
        setRequestQueue(Volley.newRequestQueue(getContext()));
        setRoomBookings(new ArrayList<RoomBooking>());
        setUrl(getUrl() + getToken());
        StringRequest request = new StringRequest(Request.Method.GET, getUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        List<RoomBooking> myList = new ArrayList<>();
                        Set<RoomCategory> categs = getRoomCategories();
                        HashMap<Long, Double> prices = new HashMap<>();
                        HashMap<Long, String> categoriesName = new HashMap<>();
                        for (RoomCategory c : categs) {
                            categoriesName.put(c.getId(), c.getName());
                            prices.put(c.getId(), c.getPrice());

                        }
                        HashMap<Long, String> roomTypes = new HashMap<>();
                        try {
                            JSONArray jsonArr = new JSONArray(response);
                            for (int i = 0; i < jsonArr.length(); i++) {
                                JSONObject jsonObj = jsonArr.getJSONObject(i);
                                myList.add(new RoomBooking((long) jsonObj.getLong("id"),
                                        convertDateToString(convertLongToDate(jsonObj.getLong("dateStart"))),
                                        convertDateToString(convertLongToDate(jsonObj.getLong("dateEnd"))),
                                        categoriesName.get(jsonObj.getLong("idRoomCategory")),
                                        prices.get(jsonObj.getLong("idRoomCategory"))/*,
                                        getOccurrenceRoomBooking(jsonObj.getLong("idRoomCategory"))*/
                                ));

                            }
                            setRoomBookings(myList);
                            System.out.println("liste de restaut contient " + getRoomBookings().size());

                            RoomBookingHistoryAdapter roomBookingHistoryAdapter = new RoomBookingHistoryAdapter(getContext(), roomBookings);
                            getListView().setAdapter(roomBookingHistoryAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
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

    public List<RoomBooking> getRoomBookings() {
        return roomBookings;
    }

    public void setRoomBookings(List<RoomBooking> roomBookings) {
        this.roomBookings = roomBookings;
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

    public Set<RoomCategory> getRoomCategories() {
        return roomCategories;
    }

    public void setRoomCategories(Set<RoomCategory> roomCategories) {
        this.roomCategories = roomCategories;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getOccurrenceRoomBooking(Long roomId, List<RoomCategory> list) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == roomId) {
                count++;
            }
        }
        return count;
    }

    public String getUrlForRoomList() {
        return urlForRoomList;
    }

    public void setUrlForRoomList(String urlForRoomList) {
        this.urlForRoomList = urlForRoomList;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public Date convertLongToDate(long l) {
        long tmp = l;
        Date d = new Date(tmp);
        return d;
    }

    public String convertDateToString(Date d) {
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
        try {
            dateString = sdfr.format(d);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dateString;
    }

}
