package br.unb.appdev.igor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewSessionFragment extends Fragment {


    public NewSessionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity.fragAtual = MainActivity.Fragments.NEWSESSION;
        return inflater.inflate(R.layout.fragment_new_session, container, false);
    }

}
