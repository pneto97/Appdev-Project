package br.unb.appdev.igor;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {



    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    private ImageButton newAdvButton;
    private ImageView botaoExcluirCV;
    private AlertDialog.Builder dialog;

    private List<Adventure> adventure = MainActivity.adventures;

    private void initializeList(){
        //adventure = new ArrayList<>();
        adventure.add(new Adventure("Projeto I.G.O.R",R.drawable.heartlands));
        adventure.add(new Adventure("Campos de Nhame",R.drawable.corvali));
        adventure.add(new Adventure("Teste3",R.drawable.coast));
        adventure.add(new Adventure("Teste4",R.drawable.krevast));
        adventure.add(new Adventure("Teste5",R.drawable.heartlands));
        adventure.add(new Adventure("Teste6",R.drawable.corvali));
    }

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



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity.fragAtual = MainActivity.Fragments.HOME;
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);

        layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);

        newAdvButton = view.findViewById(R.id.button_new_adventure);

        //initializeList();
        RVAdapter adapter = new RVAdapter(adventure);
        recyclerView.setAdapter(adapter);

        newAdvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.fragAtual = MainActivity.Fragments.NEWADVENTURE;
                MainActivity.fragmentManager.beginTransaction().replace(
                        R.id.fragment_container,new NewAdventureFragment(),
                        null).addToBackStack(null).commit();
            }
        });


        return view;
    }

    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.AdventureViewHolder>{

        List<Adventure> adventureList;

        RVAdapter(List<Adventure> adventureList){
            this.adventureList = adventureList;
        }

        @NonNull
        @Override
        public AdventureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
            AdventureViewHolder avh = new AdventureViewHolder(v);
            return avh;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public void onBindViewHolder(@NonNull AdventureViewHolder adventureViewHolder, final int position) {
            adventureViewHolder.advName.setText(adventureList.get(position).name);
            adventureViewHolder.advImg.setImageResource(adventureList.get(position).photoId);
            //adventureViewHolder.advImg.setScaleType(ImageView.ScaleType.FIT_XY);

            botaoExcluirCV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog = new AlertDialog.Builder(getContext());
                    dialog.setIcon(android.R.drawable.ic_menu_delete);
                    dialog.setTitle("Deseja excluir a aventura?");

                    //configurando evento de click negativo e positivo
                    dialog.setNegativeButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adventure.remove(position + 0);
                            notifyDataSetChanged();
                        }


                    });

                    dialog.setPositiveButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    dialog.create();
                    dialog.show();
                }
            });


            adventureViewHolder.view.setOnClickListener(new View.OnClickListener() {  // <--- here
                @Override
                public void onClick(View v) {
                    //condicional que define entrar na tela de edição ou onGoing

                    ModoEdicaoFragment modoEdicao = (ModoEdicaoFragment) getFragmentManager().findFragmentByTag("MODOEDICAO");
                    if (modoEdicao != null && modoEdicao.isVisible()) {
                        MainActivity.fragAtual = MainActivity.Fragments.TELAEDICAO1;

                        TelaEdicao1Fragment tela1Edicao = new TelaEdicao1Fragment();

                        Bundle bundle = new Bundle();

                        bundle.putInt("posicaoAventura", position);
                        tela1Edicao.setArguments(bundle);

                        MainActivity.fragmentManager.beginTransaction().replace(
                                R.id.fragment_container, tela1Edicao,
                                null).addToBackStack(null).commit();
                    }
                    else {
                        MainActivity.fragAtual = MainActivity.Fragments.ONGOING;

                        OngoingAdventureFragment onGoing = new OngoingAdventureFragment();

                        Bundle bundle = new Bundle();

                        bundle.putInt("posicaoAventura", position);
                        onGoing.setArguments(bundle);

                        MainActivity.fragmentManager.beginTransaction().replace(
                                R.id.fragment_container, onGoing,
                                null).addToBackStack(null).commit();
                        //Toast.makeText(v.getContext(), adventureList.get(position).name, Toast.LENGTH_SHORT).show();


//                    MainActivity.fragmentManager.beginTransaction().replace(
//                            R.id.fragment_container,new OngoingAdventureFragment(),
//                            null).addToBackStack(null).commit();
//                    Toast.makeText(v.getContext(), adventureList.get(position).name, Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return adventureList.size();
        }



        public class AdventureViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView advName;
            ImageView advImg;
            View view;


            AdventureViewHolder(final View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.cv);
                advName = (TextView)itemView.findViewById(R.id.nameAdventure);
                advImg = (ImageView)itemView.findViewById(R.id.bgadventure);
                botaoExcluirCV = (ImageView)itemView.findViewById(R.id.botaoExcluirCardid);
                this.view = itemView;


            }
        }
    }
}
