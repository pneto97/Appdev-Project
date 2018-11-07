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
public class TelaEdicao1Fragment extends Fragment {

    private ImageView bgAventura;
    private TextView nameAventura;
    private TextView andamentoText;
    private TextView jogadoresText;
    private ImageView abaDeConteudo;
    private ImageView botaoConfirmarTela1;

    int position;
    boolean flip;

    void imgFlip(ImageView param, boolean flip){
        int flip_arg;
        if(flip == false){
            flip_arg = -1;
        } else{
            flip_arg = 1;
        }
        param.setScaleX(flip_arg);
    }

    public TelaEdicao1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tela_edicao1, container, false);

        nameAventura = (TextView) view.findViewById(R.id.nameTelaEdicao1);
        bgAventura = (ImageView) view.findViewById(R.id.bgTelaEdicao1);
        andamentoText = (TextView) view.findViewById(R.id.andamento_text_tela1Edicao);
        jogadoresText = (TextView) view.findViewById(R.id.jogadores_text_tela1Edicao);
        abaDeConteudo = (ImageView) view.findViewById(R.id.aba_conteudo_tela1Edicao);
        botaoConfirmarTela1 = (ImageView) view.findViewById(R.id.botao_corfirmar_tela1Edicao);

        final Bundle bundle = this.getArguments();
        if(bundle != null){
            position = bundle.getInt("posicaoAventura",position);
            nameAventura.setText(MainActivity.adventures.get(position).name);
            bgAventura.setImageResource(MainActivity.adventures.get(position).photoId);
            Toast.makeText(view.getContext(), "Edição - " + MainActivity.adventures.get(position).name, Toast.LENGTH_SHORT).show();
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

        botaoConfirmarTela1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragAtual = MainActivity.Fragments.TELAEDICAO2;

                TelaEdicao2Fragment tela2Edicao = new TelaEdicao2Fragment();

                bundle.putInt("posicaoAventura", position);
                tela2Edicao.setArguments(bundle);

                MainActivity.fragmentManager.beginTransaction().replace(
                        R.id.fragment_container, tela2Edicao,
                        null).addToBackStack(null).commit();
            }
        });
        return view;
    }

}
