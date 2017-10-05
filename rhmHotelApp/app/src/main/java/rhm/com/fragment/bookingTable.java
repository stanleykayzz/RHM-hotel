package rhm.com.fragment;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.release.rhm.rhmhotelapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

import rhm.com.converter.DateConverter;
import rhm.com.converter.GetServerData;
import rhm.com.entities.FreeRoomInformation;

public class bookingTable extends Fragment {

    private String mySpinVal, jsonToString;
    private SharedPreferences sharedPreferences;
    private String token;
    private Spinner spinner;
    private int count;
    private ArrayList<FreeRoomInformation> freeRooms;
    private JSONObject json;
    private EditText countChairs;
    private RequestQueue requestQueue;
    private String url;
    private String[] spinnerValue = {"12:00", "13:00", "19:30", "20:30", "21:30"};

    public bookingTable() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking_table, container, false);
        getActivity().setTitle("Réservation de table");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        spinner = view.findViewById(R.id.spinner);
        Button book = view.findViewById(R.id.validate);
        this.countChairs = view.findViewById(R.id.countChair);
        this.requestQueue = Volley.newRequestQueue(this.getContext());
        this.sharedPreferences = getContext().getSharedPreferences("myUser", 0);
        this.token = sharedPreferences.getString("token", null);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mySpinVal = spinnerValue[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetServerData.updateReloadClientConnection(getContext(), getToken());
                if (spinner.getSelectedItem().toString().length() > 0 && countChairs.getText().length() > 0) {
                    setUrl("https://residencedeshautsdemenaye.fr/api/restaurantTableBooking?token=" + getToken());
                    StringRequest request = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(getActivity().getApplicationContext(),"Votre réservation a bien été effectué",Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (error instanceof TimeoutError || error instanceof NoConnectionError)
                                Toast.makeText(getContext(), " Votre connexion a expiré, veuillez vous reconnecter", Toast.LENGTH_SHORT).show();
                            else if (error instanceof AuthFailureError || error instanceof ServerError)
                                Toast.makeText(getContext(), " Votre réservationa a déjà été effectué", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        public byte[] getBody() throws AuthFailureError {
                            byte[] body = new byte[0];
                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("date", DateConverter.convertDateToStringFormatYMDT(new Date()) + "T" + mySpinVal + ":00+0200");
                                jsonObject.put("idClient", sharedPreferences.getInt("idClient", 0));
                                jsonObject.put("number", countChairs.getText().toString());
                                body = jsonObject.toString().getBytes("UTF-8");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            return body;
                        }

                        @Override
                        public String getBodyContentType() {
                            return "application/json";
                        }
                    };
                    getRequestQueue().add(request);
                }
            }
        });
        return view;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
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

    public String getJsonToString() {
        return jsonToString;
    }

    public void setJsonToString(String jsonToString) {
        this.jsonToString = jsonToString;
    }

    public Spinner getSpinner() {
        return spinner;
    }

    public void setSpinner(Spinner spinner) {
        this.spinner = spinner;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<FreeRoomInformation> getFreeRooms() {
        return freeRooms;
    }

    public void setFreeRooms(ArrayList<FreeRoomInformation> freeRooms) {
        this.freeRooms = freeRooms;
    }

    public EditText getCountChairs() {
        return countChairs;
    }

    public void setCountChairs(EditText countChairs) {
        this.countChairs = countChairs;
    }
}
