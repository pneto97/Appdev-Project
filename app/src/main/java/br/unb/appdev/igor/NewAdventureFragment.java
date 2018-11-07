package br.unb.appdev.igor;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewAdventureFragment extends Fragment {

    //public interface

    public NewAdventureFragment() {
        // Required empty public constructor
    }

    private EditText nomeNovaAventura;
    private ImageView botaoPronto;
    private ImageView botaoXNewAdventure;
    private TextView iconeFecharNewAdventure;

    private int genImgID(int position){
        int val = position%5;
        int id = 0;
        switch(val){

            case 0:
                id = R.drawable.coast;
                break;
            case 1:
                id = R.drawable.krevast;
                break;
            case 2:
                id = R.drawable.heartlands;
                break;
            case 3:
                id = R.drawable.corvali;
                break;
            case 4:
                id = R.drawable.miniatura_imagem_automatica;
                break;
            default:
                id = R.drawable.miniatura_imagem_automatica;
        }
        return id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_adventure, container, false);

        //recuperando os ids
        nomeNovaAventura = view.findViewById(R.id.nomeAventuraid);
        botaoPronto = view.findViewById(R.id.botaoProntoid);
        botaoXNewAdventure = view.findViewById(R.id.x_new_adventureid);
        iconeFecharNewAdventure = view.findViewById(R.id.icone_fechar_newAdventureid);

        MainActivity.fragAtual = MainActivity.Fragments.NEWADVENTURE;

        //passar o edit text o home fragment
        botaoPronto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int whichId;
//                if(MainActivity.adventures.isEmpty()){
//                    whichId = 0;
//                } else{
//                    whichId = MainActivity.adventures.size() - 1;
//                }

                String nome = nomeNovaAventura.getText().toString();
                if(nome.equals(""))
                {
                    nome = "Aventura sem título";
                }
                Adventure adv = new Adventure(nome,genImgID(MainActivity.adventures.size()));
                MainActivity.adventures.add(adv);

                MainActivity.fragAtual = MainActivity.Fragments.HOME;
                MainActivity.fragmentManager.beginTransaction().replace(
                        R.id.fragment_container,new HomeFragment(),
                        null).addToBackStack(null).commit();
                MainActivity.hideKeyboard(getActivity());

            }
        });

        //formas de fechar o fragment
        botaoXNewAdventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragAtual = MainActivity.Fragments.HOME;
                MainActivity.fragmentManager.popBackStackImmediate();
            }
        });

        iconeFecharNewAdventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragAtual = MainActivity.Fragments.HOME;
                MainActivity.fragmentManager.popBackStackImmediate();
            }
        });

        return view;
    }

}
