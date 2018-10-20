package br.unb.appdev.igor;


import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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

    private List<Adventure> adventure;

    private void initializeList(){
        adventure = new ArrayList<>();
        adventure.add(new Adventure("Projeto I.G.O.R",R.drawable.heartlands));
        adventure.add(new Adventure("Campos de Nhame",R.drawable.corvali));
        adventure.add(new Adventure("Teste3",R.drawable.coast));
        adventure.add(new Adventure("Teste4",R.drawable.krevast));
        adventure.add(new Adventure("Teste5",R.drawable.heartlands));
        adventure.add(new Adventure("Teste6",R.drawable.corvali));
    }



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);

        layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);

        newAdvButton = view.findViewById(R.id.button_new_adventure);

        initializeList();
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

            adventureViewHolder.view.setOnClickListener(new View.OnClickListener() {  // <--- here
                @Override
                public void onClick(View v) {

                    MainActivity.fragAtual = MainActivity.Fragments.ONGOING;
                    MainActivity.fragmentManager.beginTransaction().replace(
                            R.id.fragment_container,new OngoingAdventureFragment(),
                            null).addToBackStack(null).commit();
                    Toast.makeText(v.getContext(), adventureList.get(position).name, Toast.LENGTH_SHORT).show();

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
                this.view = itemView;


            }
        }

    }


}
