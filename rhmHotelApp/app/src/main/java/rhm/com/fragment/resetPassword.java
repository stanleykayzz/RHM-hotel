package rhm.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

import rhm.com.adapters.FreeRoomAdapter;
import rhm.com.entities.FreeRoomInformation;

public class resetPassword extends Fragment {

    private Button recoverButton;
    private EditText recoveryEmail;
    private View view;
    private String url = "https://residencedeshautsdemenaye.fr/api/client/passwordRecovery?email=";
    private RequestQueue queue;

    public resetPassword() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_reset_password, container, false);
        this.recoveryEmail = view.findViewById(R.id.email);
        this.recoverButton = view.findViewById(R.id.recoverButton);
        this.queue = Volley.newRequestQueue(this.getContext());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.recoverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUrl(url+recoveryEmail.getText().toString());
                if (recoveryEmail.getText().length() == 0) {
                    Toast.makeText(getContext(), "Veuillez saisir votre adressee email", Toast.LENGTH_SHORT).show();
                } else if (recoveryEmail.getText().length() > 0){
                    StringRequest request = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });
                    queue.add(request);

                }
            }
        });
    }

    public Button getRecoverButton() {
        return recoverButton;
    }

    public void setRecoverButton(Button recoverButton) {
        this.recoverButton = recoverButton;
    }

    public String getRecoveryEmail() {
        return recoveryEmail.toString();
    }

    public void setRecoveryEmail(String recoveryEmail) {
        this.recoveryEmail.setText(recoveryEmail);
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestQueue getQueue() {
        return queue;
    }

    public void setQueue(RequestQueue queue) {
        this.queue = queue;
    }
}
