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
public class TelaEdicao3Fragment extends Fragment {

    private TextView nameAventura;
    private ImageView bgAventura;
    private ImageView botaoNextTela;

    int position;

    public TelaEdicao3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tela_edicao3, container, false);

        nameAventura = (TextView) view.findViewById(R.id.nameTelaEdicao3);
        bgAventura = (ImageView) view.findViewById(R.id.bgTelaEdicao3);
        botaoNextTela = (ImageView) view.findViewById(R.id.botao_corfirmar_tela3Edicao);

        final Bundle bundle = this.getArguments();
        if(bundle != null){
            position = bundle.getInt("posicaoAventura",position);
            nameAventura.setText(MainActivity.adventures.get(position).name);
            bgAventura.setImageResource(MainActivity.adventures.get(position).photoId);
        }

        botaoNextTela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragAtual = MainActivity.Fragments.HOME;

                MainActivity.fragmentManager.beginTransaction().replace(
                        R.id.fragment_container, new HomeFragment(),
                        null).addToBackStack(null).commit();

                ModoEdicaoFragment modoEdicaoFragment = new ModoEdicaoFragment();
                MainActivity.fragmentManager.beginTransaction().add(
                        R.id.fragment_container,modoEdicaoFragment, "MODOEDICAO")
                        .addToBackStack(null).commit();

            }
        });

        return view;
    }

}
