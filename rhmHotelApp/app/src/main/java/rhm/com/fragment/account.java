package rhm.com.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.release.rhm.rhmhotelapp.R;

import java.util.ArrayList;
import java.util.List;
import rhm.com.adapters.ClientInfoAdapter;
import rhm.com.entities.ClientInformation;

public class account extends Fragment {

    private String nom;
    private String prenom;
    private String email, birth, mdp, currency;
    private TextView nometprenom;
    private ListView listeDinfo;
    private int countPasswordChars;
    private List<ClientInformation> infos;

    public account() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_account, container, false);
        getActivity().setTitle("Compte");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("myUser",0);
        this.nom = sharedPreferences.getString("name",null);
        this.prenom = sharedPreferences.getString("firstName",null);
        String myEmail = sharedPreferences.getString("email",null);
        String password = sharedPreferences.getString("password",null);
        countPasswordChars = password.length();
        currency = sharedPreferences.getString("currency","XAF");

        sharedPreferences.getString("currency",null);
        String passwordToShow = createPasswordToShow();
        this.infos = new ArrayList<ClientInformation>();
        infos.add(new ClientInformation("Votre identité",this.nom+" "+this.prenom, R.drawable.account,0));
        infos.add(new ClientInformation("Votre adresse email",myEmail,R.drawable.cogs,0));
        infos.add(new ClientInformation("Votre mot de passe",passwordToShow,R.drawable.cogs,R.drawable.edit));
        infos.add(new ClientInformation("Votre date de naissance",sharedPreferences.getString("birthday",null),R.drawable.cogs,0));
        infos.add(new ClientInformation("Votre pays",sharedPreferences.getString("country",null),R.drawable.cogs,R.drawable.edit));
        infos.add(new ClientInformation("Votre ville",sharedPreferences.getString("city",null),R.drawable.cogs,R.drawable.edit));
        infos.add(new ClientInformation("La devise appliquée à l'application",currency,R.drawable.cogs,R.drawable.edit));
        this.listeDinfo = (ListView) v.findViewById(R.id.listInfo);

        ClientInfoAdapter MyAdapter = new ClientInfoAdapter(getContext(),infos);
        this.listeDinfo.setAdapter(MyAdapter);
        return v;
    }

    /*
    * Création d'une fonction qui renvoie une chaine de caractère avec la longueur du mot de passe de l'utilisateur
    */
    public String createPasswordToShow(){
        String secret="";
        for(int x=0;x<countPasswordChars;x++){
            secret+='*';
        }
        return secret;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getContext(), " nom et prénom : "+nom+ " "+prenom, Toast.LENGTH_LONG).show();
        this.listeDinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame,new editInformation()).commit();
            }
        });
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getBirth() {
        return birth;
    }
    public void setBirth(String birth) {
        this.birth = birth;
    }
    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public TextView getNometprenom() {
        return nometprenom;
    }
    public void setNometprenom(TextView nometprenom) {
        this.nometprenom = nometprenom;
    }
}