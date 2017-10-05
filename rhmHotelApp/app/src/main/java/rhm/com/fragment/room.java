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
import android.widget.ListView;
import com.release.rhm.rhmhotelapp.R;
import rhm.com.adapters.RoomListAdapter;
import rhm.com.converter.Currency;
import rhm.com.entities.Chambre;
import java.util.ArrayList;
import java.util.List;

public class room extends Fragment {

    private ListView listeDesChambres;
    private List<Chambre> chambres;
    private SharedPreferences sharedPreferences;
    private View v;
    private SharedPreferences.Editor editor;
    private double simpleRoomPrice, doubleRoomPrice, juniorRoomPrice , executiveRoomPrice;
    public room(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.sharedPreferences = getContext().getSharedPreferences("myUser",0);

        // Inflate the layout for this fragment
         v = inflater.inflate(R.layout.fragment_room, container, false);



        getActivity().setTitle("Nos chambres");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.chambres = new ArrayList<Chambre>();
        this.simpleRoomPrice = 60000;
        this.doubleRoomPrice = 100000;
        this.juniorRoomPrice = 150000;
        this.executiveRoomPrice = 200000;
        checkCurrency();


        chambres.add(new Chambre(1,"Chambre Simple", (this.simpleRoomPrice),R.drawable.simple1));
        chambres.add(new Chambre(2,"Chambre Double", (this.doubleRoomPrice),R.drawable.double1));
        chambres.add(new Chambre(3,"Suite Junior", (this.juniorRoomPrice),R.drawable.suitejr1));
        chambres.add(new Chambre(4,"Suite Exécutive", (this.executiveRoomPrice),R.drawable.suiteexe1));
        this.listeDesChambres = (ListView) v.findViewById(R.id.listViewRooms);
        this.listeDesChambres.setAdapter(new RoomListAdapter(getContext(), chambres));

        //on ajoute le code pour afficher la page de détails lorsqu'on clique sur un objet
        this.listeDesChambres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Chambre detailRoom = chambres.get(position);
                DetailedRoom dr = new DetailedRoom();
                Bundle bundle = new Bundle();
                bundle.putString("LePrix",detailRoom.getPriceIntoString());
                bundle.putString("LeType",detailRoom.getTypeRoom());
                bundle.putInt("LaPhoto",detailRoom.getPhoto());
                dr.setArguments(bundle);
                goToDetailRoom(dr);
            }
        });

    }

    private void checkCurrency() {

        if(sharedPreferences.getString("currency","XAF") == "XAF"){
            setAllRoomPrice(60000,100000,150000,200000);
        }
        else if(sharedPreferences.getString("currency","XAF") == "EUR"){
            setAllRoomPrice(Currency.convertXafToEuro(60000),
                    Currency.convertXafToEuro(100000),
                     Currency.convertXafToEuro(150000),
                    Currency.convertXafToEuro(200000));
        }
        else if(sharedPreferences.getString("currency","XAF") == "USD"){
            setAllRoomPrice(Currency.convertXafToDollar(60000),
                    Currency.convertXafToDollar(100000),
                     Currency.convertXafToDollar(150000),
                    Currency.convertXafToDollar(200000));
        }
    }

    /*On appel une fonction qui mène vers la page d edétail et qui prend ene paramètre
    le fragment dans lequel on passe nos arguments*/
    private void goToDetailRoom(Fragment fragment) {
        FragmentManager fragMan = getFragmentManager();
        fragMan.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }

    private void setAllRoomPrice(double simple, double d, double jr,double ex){
        setSimpleRoomPrice(simple);
        setDoubleRoomPrice(d);
        setJuniorRoomPrice(jr);
        setExecutiveRoomPrice(ex);
    }

    public double getSimpleRoomPrice() {
        return simpleRoomPrice;
    }

    public void setSimpleRoomPrice(double simpleRoomPrice) {
        this.simpleRoomPrice = simpleRoomPrice;
    }

    public double getDoubleRoomPrice() {
        return doubleRoomPrice;
    }

    public void setDoubleRoomPrice(double doubleRoomPrice) {
        this.doubleRoomPrice = doubleRoomPrice;
    }

    public ListView getListeDesChambres() {
        return listeDesChambres;
    }

    public void setListeDesChambres(ListView listeDesChambres) {
        this.listeDesChambres = listeDesChambres;
    }

    public List<Chambre> getChambres() {
        return chambres;
    }

    public void setChambres(List<Chambre> chambres) {
        this.chambres = chambres;
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

    public double getJuniorRoomPrice() {
        return juniorRoomPrice;
    }

    public void setJuniorRoomPrice(double juniorRoomPrice) {
        this.juniorRoomPrice = juniorRoomPrice;
    }

    public double getExecutiveRoomPrice() {
        return executiveRoomPrice;
    }

    public void setExecutiveRoomPrice(double executiveRoomPrice) {
        this.executiveRoomPrice = executiveRoomPrice;
    }
}