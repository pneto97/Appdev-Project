package br.unb.appdev.igor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import br.unb.appdev.igor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModoEdicaoFragment extends Fragment {

    private ImageView botaoFecharEdicao;
    private ImageView botaoProntoEdicao;

    public ModoEdicaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_modo_edicao, container, false);

        botaoFecharEdicao = (ImageView) view.findViewById(R.id.botaoFecharEdicaoid);
        botaoProntoEdicao = (ImageView) view.findViewById(R.id.botaoProntoEdicaoid);

        botaoFecharEdicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                Fragment fragment = fm.findFragmentByTag("MODOEDICAO");
                FragmentTransaction ft = fm.beginTransaction();
                ft.remove(fragment);
                ft.commit();

                displayMessage("Modo edicao fechado");
            }
        });

        botaoProntoEdicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                Fragment fragment = fm.findFragmentByTag("MODOEDICAO");
                FragmentTransaction ft = fm.beginTransaction();
                ft.remove(fragment);
                ft.commit();

                displayMessage("Aventura editada");
            }
        });

        return view;
    }

    public void displayMessage(String message)
    {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
