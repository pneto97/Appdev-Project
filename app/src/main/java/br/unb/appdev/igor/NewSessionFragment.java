package br.unb.appdev.igor;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewSessionFragment extends Fragment {


    public NewSessionFragment() {
        // Required empty public constructor
    }

    public TextView eventDate;
    public ImageButton dateBtn;
    public ImageView pronto;
    public MyDate date;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_new_session, container, false);
        MainActivity.fragAtual = MainActivity.Fragments.NEWSESSION;

        eventDate = view.findViewById(R.id.dateId);
        dateBtn = view.findViewById(R.id.botaoProntoid2);
        pronto = view.findViewById(R.id.botaoProntoid4);


        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date = new MyDate(day,month,year);
                                month++;
                                eventDate.setText(day + "/" + month);
                            }
                        }, year, month, dayOfMonth);

                datePickerDialog.show();
            }

        });

        pronto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragAtual = MainActivity.Fragments.NEWSESSIONTEXT;
                MainActivity.fragmentManager.beginTransaction().replace(
                        R.id.fragment_container,new NewSessionTextFragment(),
                        null).addToBackStack(null).commit();
            }
        });
        return view;
    }





}
