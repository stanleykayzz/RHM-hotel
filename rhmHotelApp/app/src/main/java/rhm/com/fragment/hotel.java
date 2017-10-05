package rhm.com.fragment;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.release.rhm.rhmhotelapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rhm.com.entities.FreeRoomInformation;

public class hotel extends Fragment {

    private EditText arrivalDate, departureDate;
    private TextView countNights;
    private Button searchButton;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private DatePickerDialog.OnDateSetListener dialogListener1;
    private DatePickerDialog.OnDateSetListener dialogListener2;
    private List<FreeRoomInformation> freeRooms;
    private String responseArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hotel, container, false);
        getActivity().setTitle("Disponibilités");
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.arrivalDate = (EditText) v.findViewById(R.id.arrivalDate);
        departureDate = (EditText) v.findViewById(R.id.departureDate);
        countNights = (TextView) v.findViewById(R.id.countNights);
        sharedPreferences = getContext().getSharedPreferences("myUser",0);
        editor = sharedPreferences.edit();
        searchButton = (Button) v.findViewById(R.id.searchButton);

        this.dialogListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String checkin = i2 + "/" + (i1 + 1) + "/" + i;
                arrivalDate.setText(checkin);
            }
        };

        this.dialogListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String checkout = i2 + "/" + (i1 + 1) + "/" + i;
                departureDate.setText(checkout);
            }
        };
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchDisponibilities();
            }
        });

        arrivalDate.setOnClickListener(new View.OnClickListener() {
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

                DatePickerDialog dialog = new DatePickerDialog(getContext(), R.style.Theme_AppCompat_DayNight_DarkActionBar, dialogListener1, year, month, day);
                dialog.getDatePicker().setMinDate(cal.getTimeInMillis());
                dialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());
                dialog.show();
            }
        });

        arrivalDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });

        arrivalDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkCountNights();
            }
        });

        departureDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkCountNights();
            }
        });

        departureDate.setOnClickListener(new View.OnClickListener() {
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

    }

    private void checkCountNights() {
        if (this.arrivalDate.getText().length() > 0 && this.departureDate.getText().length() > 0) {
            this.countNights.setText("" + getCountNightsBetweenTowDates(this.arrivalDate.getText().toString(), this.departureDate.getText().toString() + " "));
        }
    }

    private Long getCountNightsBetweenTowDates(String date1, String date2) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = formatter.parse(date1);
            d2 = formatter.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long v = d2.getTime() - d1.getTime();

        return TimeUnit.DAYS.convert(v, TimeUnit.MILLISECONDS);
    }

    private void searchDisponibilities() {

        if (this.arrivalDate.getText().length() == 0 && this.departureDate.getText().length() == 0) {
            Toast.makeText(getContext(), "Veuillez saisir les dates avant de faire une recherche de disponibilité.", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getContext(), "On recupère les information du server du serveur", Toast.LENGTH_SHORT).show();
            editor.putString("startDate", dateFormatForApiRequest(arrivalDate.getText().toString()));
            editor.putString("endDate", dateFormatForApiRequest(arrivalDate.getText().toString()));
            editor.commit();
            goToFreeRoomList();
        }
    }

    private String dateFormatForApiRequest(String dateToFormat) {
        char sep = '/';
        String[] tab = dateToFormat.split("/");
        String formatedDate = tab[1] + sep + tab[2] + sep + tab[0];
        return formatedDate;
    }

    private void goToFreeRoomList() {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new freeRoomList()).commit();
    }

    public List<FreeRoomInformation> getFreeRooms() {
        return freeRooms;
    }

    public void setFreeRooms(List<FreeRoomInformation> freeRooms) {
        this.freeRooms = freeRooms;
    }

    public EditText getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(EditText arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public EditText getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(EditText departureDate) {
        this.departureDate = departureDate;
    }

    public TextView getCountNights() {
        return countNights;
    }

    public void setCountNights(TextView countNights) {
        this.countNights = countNights;
    }

    public Button getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(Button searchButton) {
        this.searchButton = searchButton;
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

    public DatePickerDialog.OnDateSetListener getDialogListener1() {
        return dialogListener1;
    }

    public void setDialogListener1(DatePickerDialog.OnDateSetListener dialogListener1) {
        this.dialogListener1 = dialogListener1;
    }

    public DatePickerDialog.OnDateSetListener getDialogListener2() {
        return dialogListener2;
    }

    public void setDialogListener2(DatePickerDialog.OnDateSetListener dialogListener2) {
        this.dialogListener2 = dialogListener2;
    }

    public String getResponseArray() {
        return responseArray;
    }

    public void setResponseArray(String responseArray) {
        this.responseArray = responseArray;
    }
}