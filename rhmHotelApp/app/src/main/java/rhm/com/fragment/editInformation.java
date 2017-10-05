package rhm.com.fragment;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.release.rhm.rhmhotelapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class editInformation extends Fragment {

    private EditText editCity, editCountry, editOldPass, editNewPass, editNewPass2;
    private Button validateCity, validateCountry, validatePassword, validateCurrency;
    private SharedPreferences sharedPreferences;
    private Spinner currencies;
    private String newCurrency;
    private String clientToken;
    private String url = "http://residencedeshautsdemenaye.com/api/client";

    public editInformation() {
        // Required empty public constructor
    }

    private void goBackToAccount() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new account()).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_information, container, false);
        this.editCity = (EditText) view.findViewById(R.id.editCity);
        this.editCountry = (EditText) view.findViewById(R.id.editCountry);
        this.editNewPass = (EditText) view.findViewById(R.id.editNewPass);
        this.editOldPass = (EditText) view.findViewById(R.id.editPassword);
        this.editNewPass2 = (EditText) view.findViewById(R.id.editNewPass2);
        this.validateCity = (Button) view.findViewById(R.id.validateCity);
        this.validateCountry = (Button) view.findViewById(R.id.validateCountry);
        this.validatePassword = (Button) view.findViewById(R.id.ValidatePass);
        this.currencies = (Spinner) view.findViewById(R.id.currencies);
        this.validateCurrency = (Button) view.findViewById(R.id.validateCurrency);
        this.sharedPreferences = getContext().getSharedPreferences("myUser", 0);
        getActivity().setTitle("Modification d'informations");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        this.validateCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editCity.getText().toString().length() == 0) {
                    Toast.makeText(getContext(), "Veuillez saisir votre ville avant de valider la modification", Toast.LENGTH_SHORT).show();
                } else {
                    editor.putString("city", editCity.getText().toString());
                    editor.commit();
                    updateClient("password", sharedPreferences.getString("password", null), editNewPass2.getText().toString());
                    goBackToAccount();
                }
            }
        });

        this.validatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editOldPass.getText().toString().length() == 0 || editNewPass.getText().toString().length() == 0 || editNewPass2.getText().toString().length() == 0) {
                    Toast.makeText(getContext(), "Veuillez remplir les trois champs avant de valider la modification", Toast.LENGTH_SHORT).show();
                } else {
                    if (editNewPass.getText().toString() == editNewPass2.getText().toString()) {
                        editor.putString("password", editNewPass2.getText().toString());
                        editor.commit();
                        updateClient("password", sharedPreferences.getString("password", null), editNewPass2.getText().toString());
                        goBackToAccount();
                    } else if (editNewPass.getText().toString() != editNewPass2.getText().toString()) {
                        Toast.makeText(getContext(), "Les deux mots de passes saisis sont différents, veuillez saisir le même mot de passe", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        this.validateCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editCountry.getText().toString().length() == 0) {
                    Toast.makeText(getContext(), "Veuillez saisir votre Pays avant de valider la modification", Toast.LENGTH_SHORT).show();
                } else {
                    editor.putString("country", editCountry.getText().toString());
                    editor.commit();
                    updateClient("country", sharedPreferences.getString("country", null), editCountry.getText().toString());
                    goBackToAccount();
                }
            }
        });

        this.currencies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    newCurrency = "XAF";
                } else if (position == 1) {
                    newCurrency = "EUR";
                } else if (position == 2) {
                    newCurrency = "USD";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.validateCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("currency", newCurrency);
                editor.commit();
                goBackToAccount();
            }
        });
    }

    private void updateClient(final String column, final String toUpdate, final String theUpdate) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        this.clientToken = this.sharedPreferences.getString("clientToken", null);
        //this.url = this.url + this.sharedPreferences.getString("clientId", null);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                byte[] body = new byte[0];
                JSONObject jsonObject = new JSONObject();
                try {
                    //jsonObject.put(column, toUpdate + " " + theUpdate);
                    jsonObject.put(column, toUpdate + " " + theUpdate);
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
        queue.add(stringRequest);
    }


}
