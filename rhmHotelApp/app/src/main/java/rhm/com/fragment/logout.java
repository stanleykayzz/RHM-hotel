package rhm.com.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.release.rhm.rhmhotelapp.MainActivity;
import com.release.rhm.rhmhotelapp.R;

public class logout extends Fragment {
    private TextView fullname;
    private TextView email;
    private TextView phone;
    private Button logoutButton;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logout, container, false);
        this.fullname = (TextView) view.findViewById(R.id.textView2);
        this.email = (TextView) view.findViewById(R.id.textView6);
        this.phone = (TextView) view.findViewById(R.id.textView8);
        this.logoutButton = (Button) view.findViewById(R.id.logoutButton);
        this.sharedPreferences = getContext().getSharedPreferences("myUser", 0);
        getActivity().setTitle("Déconnexion");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.fullname.setText(this.sharedPreferences.getString("name", null) + " " + this.sharedPreferences.getString("firstName", null));
        this.email.setText(this.sharedPreferences.getString("email", null));
        this.phone.setText(this.sharedPreferences.getString("phone", null));
        this.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On vide le sharedPreference, on retire les infos du dernier utilisateur connecté
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear().commit();
                logoutUser();
            }
        });
    }

    private void logoutUser() {
        getActivity().finish();
        startActivity(new Intent(getActivity(), MainActivity.class));
    }

    public TextView getFullname() {
        return fullname;
    }

    public void setFullname(TextView fullname) {
        this.fullname = fullname;
    }

    public TextView getEmail() {
        return email;
    }

    public void setEmail(TextView email) {
        this.email = email;
    }

    public TextView getPhone() {
        return phone;
    }

    public void setPhone(TextView phone) {
        this.phone = phone;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }

    public void setLogoutButton(Button logoutButton) {
        this.logoutButton = logoutButton;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }
}