package rhm.com.fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.release.rhm.rhmhotelapp.R;
import rhm.com.email.SendEmail;

public class contact extends Fragment {
    private Button send;
    private TextView name,email,phone,textMessage;
    private String nom,mail,number,text;
    private String newLine = "\n";
    private String myMessage = "Vous avez reçu un message de l'application Android.\n Voici les informations : \n";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Initializing the views
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        this.name = (TextView)view.findViewById(R.id.input_nom);
        this.email = (TextView)view.findViewById(R.id.contactEmail);
        this.phone = (TextView)view.findViewById(R.id.phoneNumber);
        this.textMessage = (TextView)view.findViewById(R.id.inputMessage);
        this.send = (Button)view.findViewById(R.id.sendMail);
        getActivity().setTitle("Contactez-nous");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().length() == 0 ||
                        email.getText().toString().length() == 0 ||
                        phone.getText().toString().length() == 0 ||
                        textMessage.getText().toString().length() == 0 ){
                    Toast.makeText(getContext(),"Remplissez les champs vide",Toast.LENGTH_LONG).show();
                }
                else{
                    nom = name.getText().toString();
                    mail = email.getText().toString();
                    number = phone.getText().toString();
                    text = textMessage.getText().toString();
                    sendContactEmailToRhm("Formulaire de contact de l'application Android",
                            myMessage+"Nom et Prénom : "+nom+newLine+"Email : "+mail+newLine+"Numéro de telephone : "+number+newLine+"Message : "+text);
                }
            }
        });
    }

    protected void sendContactEmailToRhm(String subject, String message) {
        //Getting content for email
        //Creating SendMail object
       // SendEmail sm = new SendEmail("residencedeshautsdemenaye@outlook.com", subject, message);
        SendEmail sm = new SendEmail(getContext(), "residencedeshautsdemenaye@gmail.com", subject, message);
        //Executing sendmail to send email
        sm.execute();
    }
}
