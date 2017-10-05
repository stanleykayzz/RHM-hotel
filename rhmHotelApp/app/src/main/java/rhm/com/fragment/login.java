package rhm.com.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.release.rhm.rhmhotelapp.MainActivity;
import com.release.rhm.rhmhotelapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class login extends Fragment {
    private ProgressDialog progressDialog;
    boolean canReload = false;
    private TextView email, password, forgotPassword;
    private Button loginButton, createAccountButton;
    private String myResponse;
    private JSONObject json;
    private View v;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public login() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.v = inflater.inflate(R.layout.fragment_login, container, false);
        this.email = (EditText) v.findViewById(R.id.input_email);
        this.progressDialog = new ProgressDialog(getContext());
        this.password = (EditText) v.findViewById(R.id.input_password);
        this.loginButton = (Button) v.findViewById(R.id.btn_login);
        this.createAccountButton = (Button) v.findViewById(R.id.signup);
        this.forgotPassword = (TextView) v.findViewById(R.id.forgotPassword);
        this.myResponse = new String();
        this.json = new JSONObject();
        this.sharedPreferences = getContext().getSharedPreferences("myUser", 0);
        this.editor = sharedPreferences.edit();

        getActivity().setTitle("Connexion");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().length() == 0 || password.getText().length() == 0) {
                    Toast.makeText(getContext(), "Remplissez tous les champs avant de vous connecter", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser();
                }
            }
        });

        this.createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragMan = getFragmentManager();
                fragMan.beginTransaction().replace(R.id.content_frame, new suscribe()).commit();
            }
        });

        this.forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToResetPassword();
            }
        });
    }

    public void loginUser() {
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        String url = "https://residencedeshautsdemenaye.fr/api/client/login?email=" + getEmail().getText().toString() + "&password=" + getPassword().getText().toString();
        progressDialog.setTitle("Connexion en cours");
        progressDialog.show();
        StringRequest getClientRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        setJson(response);
                        try {
                            getEditor().putInt("idClient", getJson().getInt("id"));
                            getEditor().putString("name", getJson().getString("lastName"));
                            getEditor().putString("firstName", getJson().getString("firstName"));
                            getEditor().putString("birthday", formatBirthdayToString(getJson().getLong("birthday")));
                            getEditor().putString("email", getJson().getString("email"));
                            getEditor().putString("phone", getJson().getString("phone"));
                            getEditor().putString("country", getJson().getString("country"));
                            getEditor().putString("city", getJson().getString("city"));
                            getEditor().putString("address", getJson().getString("address"));
                            getEditor().putString("postalCode", getJson().getString("postalCode"));
                            getEditor().putString("password", getJson().getString("password"));
                            getEditor().putString("token", getJson().getString("token"));
                            getEditor().putString("code", getJson().getString("code"));
                            getEditor().putString("statusActif", getJson().getString("statusActif"));
                            getEditor().putBoolean("UserConnected", true);
                            getEditor().commit();
                            getProgressDialog().dismiss();
                            reloadYourApp();
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
        queue.add(getClientRequest);
    }

    private void handleVolleyError(VolleyError error) {
        getProgressDialog().dismiss();
        if (error instanceof TimeoutError || error instanceof NoConnectionError)
            Toast.makeText(getContext(), " Votre connexion a expiré, veuillez vous reconnecter", Toast.LENGTH_SHORT).show();
        else if (error instanceof AuthFailureError)
            Toast.makeText(getContext(), " Vos identifiants sont mauvais ou vous n'avez pas encore de compte", Toast.LENGTH_SHORT).show();
        else if (error instanceof ServerError)
            Toast.makeText(getContext(), " Veuillez patienter, le serveur est en cours de re redémarrage", Toast.LENGTH_SHORT).show();
        else if (error instanceof NetworkError)
            Toast.makeText(getContext(), " Vous n'avez pas de connexion internet", Toast.LENGTH_SHORT).show();
        /*else if (error instanceof ParseError)
            Toast.makeText(getContext(), " code pas connecté", Toast.LENGTH_SHORT).show();*/
    }

    private String formatBirthdayToString(Long birthday) {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date(birthday));
    }

    public void reloadYourApp() {
        getActivity().finish();
        startActivity(new Intent(getActivity(), MainActivity.class));
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public void setProgressDialog(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
    }

    public TextView getEmail() {
        return email;
    }

    public void setEmail(TextView email) {
        this.email = email;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public void setEditor(SharedPreferences.Editor editor) {
        this.editor = editor;
    }

    public TextView getPassword() {
        return password;
    }

    public void setPassword(TextView password) {
        this.password = password;
    }

    public TextView getForgotPassword() {
        return forgotPassword;
    }

    public void setForgotPassword(TextView forgotPassword) {
        this.forgotPassword = forgotPassword;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(Button loginButton) {
        this.loginButton = loginButton;
    }

    public Button getCreateAccountButton() {
        return createAccountButton;
    }

    public void setCreateAccountButton(Button createAccountButton) {
        this.createAccountButton = createAccountButton;
    }

    public boolean isCanReload() {
        return canReload;
    }

    public void setCanReload(boolean canReload) {
        this.canReload = canReload;
    }

    public View getV() {
        return v;
    }

    public void setV(View v) {
        this.v = v;
    }

    public String getMyResponse() {
        return myResponse;
    }

    public void setMyResponse(String myResponse) {
        this.myResponse = myResponse;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject j) {
        this.json = j;
    }

    public void setJson(String j) {
        try {
            this.json = new JSONObject(j);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void goToResetPassword() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new resetPassword()).commit();
    }

}