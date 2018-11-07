package br.unb.appdev.igor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class OngoingAdventureFragment extends Fragment {

    ImageView abaDeConteudo;
    TextView jogadoresText;
    TextView andamentoText;
    ImageView buttonNewSession;

    ImageView bgOngoingAdventure;
    TextView nameOnGoingAdventure;
    boolean flip;
    int position;

    void imgFlip(ImageView param, boolean flip){
        int flip_arg;
        if(flip == false){
            flip_arg = -1;
        } else{
            flip_arg = 1;
        }
        param.setScaleX(flip_arg);
    }


    public OngoingAdventureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ongoing_adventure, container, false);

        MainActivity.fragAtual = MainActivity.Fragments.ONGOING;

        abaDeConteudo = view.findViewById(R.id.aba_conteudo_ongoing);
        andamentoText = view.findViewById(R.id.andamento_text);
        jogadoresText = view.findViewById(R.id.jogadores_text);
        bgOngoingAdventure = view.findViewById(R.id.bgOngoingAdventure);
        nameOnGoingAdventure = view.findViewById(R.id.nameOnGoingAdventure);
        buttonNewSession = view.findViewById(R.id.button_new_session);
        flip = false;

        Bundle bundle = this.getArguments();
        if(bundle != null){
            position = bundle.getInt("posicaoAventura",position);
            nameOnGoingAdventure.setText(MainActivity.adventures.get(position).name);
            bgOngoingAdventure.setImageResource(MainActivity.adventures.get(position).photoId);
            Toast.makeText(view.getContext(), MainActivity.adventures.get(position).name, Toast.LENGTH_SHORT).show();

        } else{
            //Toast.makeText(view.getContext(), "Deu ruim", Toast.LENGTH_SHORT).show();

        }


        jogadoresText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flip == false) {
                    imgFlip(abaDeConteudo, flip);
                    flip = true;
                }

            }
        });

        andamentoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flip == true) {
                    imgFlip(abaDeConteudo,flip);
                    flip = false;
                }


            }
        });

        buttonNewSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragAtual = MainActivity.Fragments.NEWSESSION;

                NewSessionFragment newSessionFragment = new NewSessionFragment();
                MainActivity.fragmentManager.beginTransaction().replace(
                        R.id.fragment_container,newSessionFragment,
                        null).addToBackStack(null).commit();
            }
        });


        return view;
    }

}
