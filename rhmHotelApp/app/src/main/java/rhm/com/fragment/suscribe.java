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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
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

import cz.msebera.android.httpclient.HttpStatus;

public class suscribe extends Fragment {
    private View view;
    private Button validate;
    private TextView inputLastname,
            inputCity,
            inputCountry,
            address,
            postalCode,
            inputFirstname,
            birthDate,
            inputPhoneNumber,
            inputEmail,
            inputPassword;
    private RadioGroup radioGroupSex;
    private RadioButton male, female;
    private String url = "https://residencedeshautsdemenaye.fr/api/client";
    private String requestBody;

    public suscribe() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Inscription");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // On crée les composants de la page
        this.view = inflater.inflate(R.layout.fragment_suscribe, container, false);
        this.validate = (Button) this.view.findViewById(R.id.validate);
        this.inputLastname = (TextView) view.findViewById(R.id.nom);
        this.inputFirstname = (TextView) view.findViewById(R.id.prenom);
        this.address = (TextView) view.findViewById(R.id.address);
        this.postalCode = (TextView) view.findViewById(R.id.cp);
        this.inputCity = (TextView) view.findViewById(R.id.city);
        this.inputCountry = (TextView) view.findViewById(R.id.country);
        this.inputEmail = (TextView) view.findViewById(R.id.email);
        this.inputPassword = (TextView) view.findViewById(R.id.mdp);
        this.birthDate = (TextView) view.findViewById(R.id.birthdate);
        this.inputPhoneNumber = (TextView) view.findViewById(R.id.phoneNumber);
        this.radioGroupSex = (RadioGroup) view.findViewById(R.id.radioSexGroup);
        this.male = (RadioButton) view.findViewById(R.id.male);
        this.female = (RadioButton) view.findViewById(R.id.female);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputEmail.getText().length() == 0 ||
                        inputLastname.getText().length() == 0 ||
                        inputCity.getText().length() == 0 ||
                        inputCountry.getText().length() == 0 ||
                        inputFirstname.getText().length() == 0 ||
                        inputPassword.getText().length() == 0 ||
                        inputPhoneNumber.getText().length() == 0 ||
                        address.getText().length() == 0 ||
                        birthDate.getText().length() == 0 ||
                        radioGroupSex.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    //radioSexButton = (RadioButton)view.findViewById(radioGroupSex.getCheckedRadioButtonId());
                    registerNewClient();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, new hotel()).commit();
                }
            }
        });
    }

    private String formatDate(TextView t) {
        String s = t.getText().toString();
        char sep = '-';
        String[] tab = s.split("/");
        String res = tab[2] + sep + tab[1] + sep + tab[0];
        return res;
    }

    private void registerNewClient() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        int selectedId = radioGroupSex.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        final RadioButton radioSexButton = (RadioButton) view.findViewById(selectedId);
        Toast.makeText(this.getContext(), radioSexButton.getText(), Toast.LENGTH_SHORT).show();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error + " error ");
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse.statusCode == 406) {
                    Toast.makeText(getContext(),"L'email que vous avez saisis est déjà associé à un compte, veuillez changer d'email ou demander un nouveau mot de passe",Toast.LENGTH_LONG).show();
                }
            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                byte[] body = new byte[0];
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("address", address.getText().toString());
                    jsonObject.put("birthday", formatDate(birthDate));
                    jsonObject.put("city", inputCity.getText().toString());
                    jsonObject.put("country", inputCountry.getText().toString());
                    jsonObject.put("email", inputEmail.getText().toString());
                    jsonObject.put("firstName", inputFirstname.getText().toString());
                    jsonObject.put("lastName", inputLastname.getText().toString());
                    jsonObject.put("password", inputPassword.getText().toString());
                    jsonObject.put("phone", inputPhoneNumber.getText().toString());
                    jsonObject.put("postalCode", postalCode.getText().toString());
                    jsonObject.put("sexe", radioSexButton.getText().toString());
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
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}