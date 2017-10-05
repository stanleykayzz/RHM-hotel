package rhm.com.fragment;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rhm.com.converter.DateConverter;
import rhm.com.converter.GetServerData;
import rhm.com.entities.FreeRoomInformation;
import rhm.com.entities.Order;
import rhm.com.entities.RoomBooking;

public class bookingRoom extends Fragment {
    public Order order;
    private EditText count;
    private TextView arrived, departure;
    private String checkin, checkout;
    private boolean in = false, out = false;
    private SharedPreferences sharedPreferences;
    private Button validate;
    private Spinner spinner;
    private Spinner reason;
    private String motive;
    private DatePickerDialog.OnDateSetListener dialogListener1;
    private DatePickerDialog.OnDateSetListener dialogListener2;
    private RequestQueue queue;
    private List<FreeRoomInformation> freeRoomInformations;
    private List<RoomBooking> roomBookings;
    private String token;
    private String roomType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_booking, container, false);
        this.count = (EditText) v.findViewById(R.id.countRoom);
        this.arrived = (TextView) v.findViewById(R.id.arriveDate);
        this.sharedPreferences = getContext().getSharedPreferences("myUser", 0);
        this.token = sharedPreferences.getString("token", null);
        this.departure = (TextView) v.findViewById(R.id.departureDate);
        this.validate = (Button) v.findViewById(R.id.validate);
        this.reason = (Spinner) v.findViewById(R.id.reason);
        this.spinner = (Spinner) v.findViewById(R.id.spinner);
        this.motive = reason.getSelectedItem().toString();
        this.roomType = spinner.getSelectedItem().toString();
        this.roomBookings = new ArrayList<>();

        this.arrived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                Calendar maxDate = Calendar.getInstance();
                maxDate.set(Calendar.YEAR, year + 1);
                maxDate.set(Calendar.MONTH, month);
                maxDate.set(Calendar.DAY_OF_MONTH, 27);
                DatePickerDialog dialog = new DatePickerDialog(getContext(), R.style.Theme_AppCompat_DayNight_DarkActionBar, dialogListener1, year, month, day);
                dialog.getDatePicker().setMinDate(cal.getTimeInMillis());
                dialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());
                dialog.show();
            }
        });

        departure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                Calendar maxDate = Calendar.getInstance();
                maxDate.set(Calendar.YEAR, year + 1);
                maxDate.set(Calendar.MONTH, month);
                maxDate.set(Calendar.DAY_OF_MONTH, 27);
                DatePickerDialog dialog = new DatePickerDialog(getContext(), R.style.Theme_AppCompat_DayNight_DarkActionBar, dialogListener2, year, month, day);
                dialog.getDatePicker().setMinDate(cal.getTimeInMillis());
                dialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());
                dialog.show();
            }

        });

        this.dialogListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                checkin = i + "-" + (i1 + 1) + "-" + i2;
                arrived.setText(checkin);
            }
        };

        this.dialogListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                checkout = i + "-" + (i1 + 1) + "-" + i2;
                departure.setText(checkout);
            }
        };

        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setMotive(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getActivity().setTitle("Réservation");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetServerData.updateReloadClientConnection(getContext(), getToken());
                queue = Volley.newRequestQueue(getContext());
                Toast.makeText(getContext(), " Votre connexion a expiré, veuillez vous reconnecter", Toast.LENGTH_SHORT).show();

                if (count.getText().length() > 0 && arrived.getText().length() > 0 && departure.getText().length() > 0) {

                    String url = "https://residencedeshautsdemenaye.fr/api/roomBooking?token=" + getToken();
                    Toast.makeText(getContext(), "url "+url, Toast.LENGTH_SHORT).show();
                    StringRequest request = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
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
                                JSONArray jsonArray = new JSONArray();
                                jsonObject.put("dateStart", arrived.getText().toString());
                                jsonObject.put("dateEnd", departure.getText().toString());
                                if(getRoomType() == "Chambre Simple")
                                    jsonObject.put("idRoomCategory",1);
                                else if(getRoomType() == "Chambre Double")
                                    jsonObject.put("idRoomCategory",2);
                                else if(getRoomType() == "Suite Junior")
                                    jsonObject.put("idRoomCategory",3);
                                else if(getRoomType() == "Suite Executive")
                                    jsonObject.put("idRoomCategory",4);
                                jsonObject.put("reason", reason.getSelectedItem().toString());
                                jsonArray.put(jsonObject);
                                body = jsonArray.toString().getBytes("UTF-8");
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
                    queue.add(request);
                }
            }
        });
        return v;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public String getToken() {
        return token;
    }

    public Object getQueue() {
        return queue;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
