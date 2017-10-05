package rhm.com.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rhm.com.adapters.FreeRoomAdapter;
import rhm.com.entities.FreeRoomInformation;

public class freeRoomList extends Fragment {
    FreeRoomAdapter myFreeRoomListAdapter;
    private int countRoomAvailable;
    private View view;
    private ListView listOfRoomAvailable;
    private SharedPreferences sharedPreferences;
    private TextView messageCount;
    private RequestQueue queue;
    private List<FreeRoomInformation> freeRoomInformations;
    private JSONObject jsonObject;
    private String myString;

    public freeRoomList() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.queue = Volley.newRequestQueue(this.getContext());
        freeRoomInformations = new ArrayList<FreeRoomInformation>();
        sharedPreferences = this.getContext().getSharedPreferences("myUser", 0);
        String url = "https://residencedeshautsdemenaye.fr/api/room/getListRoomFree?dateStart=" + sharedPreferences.getString("startDate", null) + "&dateEnd=" + sharedPreferences.getString("endDate", null);
        this.view = inflater.inflate(R.layout.fragment_free_room_list, container, false);
        this.listOfRoomAvailable = view.findViewById(R.id.listFreeRoom);
        this.countRoomAvailable = 0;
        this.messageCount = view.findViewById(R.id.countFreeRooms);
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        myString = "{ \"myArray\":" + response.toString() + "}";
                        jsonObject = null;
                        try {
                            jsonObject = new JSONObject(myString);
                            JSONArray jArray = jsonObject.getJSONArray("myArray");
                            for (int i = 0; i < jArray.length(); i++) {
                                JSONObject jObj = jArray.getJSONObject(i);
                                FreeRoomInformation f = new FreeRoomInformation(jObj.getJSONObject("roomCategory").getString("name"),
                                        jObj.getJSONObject("roomCategory").getDouble("price"));
                                freeRoomInformations.add(f);
                            }
                            setCountRoomAvailable(freeRoomInformations.size());
                            setMessageCount("iIl y a actuellement " + getCountRoomAvailable() + " chambres disponible");
                            List<String> laListe = new ArrayList<String>();
                            for (FreeRoomInformation fr : freeRoomInformations
                                    ) {
                                laListe.add(fr.getTypeRoomName());
                            }
                            Map<String, Integer> hashmap = new HashMap<String, Integer>();
                            for (String s : laListe
                                    ) {
                                hashmap.put(s, java.util.Collections.frequency(laListe, s));
                            }

                            List<FreeRoomInformation> finalList = new ArrayList<FreeRoomInformation>();
                            HashSet<FreeRoomInformation> finalL = new HashSet<FreeRoomInformation>();

                            for (FreeRoomInformation f : freeRoomInformations
                                    ) {
                                String s = f.getTypeRoomName();
                                f.setCountAvailable(hashmap.get(s));
                                finalL.add(f);
                            }


                            Set<FreeRoomInformation> set = new HashSet<FreeRoomInformation>(finalList);
                            final List<FreeRoomInformation> l = new ArrayList<FreeRoomInformation>(new HashSet<FreeRoomInformation>(finalL));

                            myFreeRoomListAdapter = new FreeRoomAdapter(getContext(), l);

                            getListOfRoomAvailable().setAdapter(myFreeRoomListAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(request);


        return view;
    }


    private void formatCountRoomAvailableMessage(int count) {
        String message = "Il y a actuellement " + count + " chambres disponible";
        setMessageCount(message);
    }

    public int getCountRoomAvailable() {
        return countRoomAvailable;
    }

    public void setCountRoomAvailable(int countRoomAvailable) {
        this.countRoomAvailable = countRoomAvailable;
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public ListView getListOfRoomAvailable() {
        return listOfRoomAvailable;
    }

    public void setListOfRoomAvailable(ListView listOfRoomAvailable) {
        this.listOfRoomAvailable = listOfRoomAvailable;
    }

    public FreeRoomAdapter getMyFreeRoomListAdapter() {
        return myFreeRoomListAdapter;
    }

    public void setMyFreeRoomListAdapter(FreeRoomAdapter myFreeRoomListAdapter) {
        this.myFreeRoomListAdapter = myFreeRoomListAdapter;
    }

    public TextView getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(TextView messageCount) {
        this.messageCount = messageCount;
    }

    public void setMessageCount(String messageCount) {
        this.messageCount.setText(messageCount);
    }

    public RequestQueue getQueue() {
        return queue;
    }

    public void setQueue(RequestQueue queue) {
        this.queue = queue;
    }

    public List<FreeRoomInformation> getFreeRoomInformations() {
        return freeRoomInformations;
    }

    public void setFreeRoomInformations(List<FreeRoomInformation> freeRoomInformations) {
        this.freeRoomInformations = freeRoomInformations;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

}
