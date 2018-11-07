package br.unb.appdev.igor;


import android.os.Bundle;
import android.provider.ContactsContract;
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
public class TelaEdicao2Fragment extends Fragment {

    private TextView nameAventura;
    private ImageView bgAventura;
    private ImageView botaoNextTela;
    int position;

    public TelaEdicao2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tela_edicao2, container, false);

        nameAventura = (TextView) view.findViewById(R.id.nameTelaEdicao2);
        bgAventura = (ImageView) view.findViewById(R.id.bgTelaEdicao2);
        botaoNextTela = (ImageView) view.findViewById(R.id.botao_corfirmar_tela2Edicao);

        final Bundle bundle = this.getArguments();
        if(bundle != null){
            position = bundle.getInt("posicaoAventura",position);
            nameAventura.setText(MainActivity.adventures.get(position).name);
            bgAventura.setImageResource(MainActivity.adventures.get(position).photoId);
        }

        botaoNextTela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragAtual = MainActivity.Fragments.TELAEDICAO3;

                TelaEdicao3Fragment tela3Edicao = new TelaEdicao3Fragment();

                bundle.putInt("posicaoAventura",position);
                tela3Edicao.setArguments(bundle);

                MainActivity.fragmentManager.beginTransaction().replace(
                        R.id.fragment_container, tela3Edicao,
                        null).addToBackStack(null).commit();
            }
        });

        return view;
    }

}
