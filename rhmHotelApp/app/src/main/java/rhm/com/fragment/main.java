package rhm.com.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
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

import org.json.JSONException;
import org.json.JSONObject;

public class main extends Fragment {
    private SharedPreferences sharedPreferences;
    private Boolean userConnected;
    private String userEmail;
    private String status;
    private String userCode;
    private EditText code;
    private Button validate;
    private RequestQueue queue;

    private String url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        getActivity().setTitle("Accueil");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.sharedPreferences = getContext().getSharedPreferences("myUser", 0);
        this.userConnected = sharedPreferences.getBoolean("UserConnected", false);
        this.status = sharedPreferences.getString("statusActif", null);
        this.userCode = sharedPreferences.getString("code", null);
        this.userEmail = sharedPreferences.getString("email", null);
        this.queue = Volley.newRequestQueue(this.getContext());

        String s = userCode;

        if(this.userCode != null &&  this.userCode.length() > 2 ){
            showDialog();
        }
        //  }

        return v;
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this.getContext());
        dialog.setTitle("Code de validation");
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == keyEvent.KEYCODE_BACK && keyEvent.getAction() == keyEvent.ACTION_UP)
                    getActivity().finish();
                return false;
            }
        });
        dialog.setContentView(R.layout.fragment_account_activation);
        dialog.setCanceledOnTouchOutside(false);

        this.code = dialog.findViewById(R.id.inputCode);
        this.validate = dialog.findViewById(R.id.validateActivation);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (code.getText().length() == 0) {
                    Toast.makeText(getContext(), "Veuillez saisir le code qui vous a été envoyé par email", Toast.LENGTH_LONG).show();

                } else if (code.getText().length() > 0){
                    //on fait les request json pour verifier si le ccode est bon avant de dismiss
                    setUrl("https://residencedeshautsdemenaye.fr/api/client/confirmation?email="+getUserEmail()+"&code="+code.getText().toString());
                    StringRequest accountValidationRequest = new StringRequest(Request.Method.GET, getUrl(),
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject json = new JSONObject(response);
                                        SharedPreferences sharedPreferences = getContext().getSharedPreferences("myUser", 0);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("code", json.getString("code"));
                                        editor.putString("statusActif", json.getString("statusActif"));
                                        editor.commit();
                                        dialog.dismiss();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });
                    getQueue().add(accountValidationRequest);

                }
            }
        });
         dialog.show();
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public Boolean getUserConnected() {
        return userConnected;
    }

    public void setUserConnected(Boolean userConnected) {
        this.userConnected = userConnected;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EditText getCode() {
        return code;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserCode() {
        return userCode;
    }

    public RequestQueue getQueue() {
        return queue;
    }

    public void setQueue(RequestQueue queue) {
        this.queue = queue;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public void setCode(EditText code) {
        this.code = code;
    }

    public Button getValidate() {
        return validate;
    }

    public void setValidate(Button validate) {
        this.validate = validate;
    }
}
