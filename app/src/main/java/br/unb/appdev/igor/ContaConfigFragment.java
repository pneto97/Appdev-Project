package br.unb.appdev.igor;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.unb.appdev.igor.Entidades.Personagens;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContaConfigFragment extends Fragment {


    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference personagensReferencias = databaseReference.child("Personagens");


    private ImageView botaoCriarPersonagem;
    private EditText nomePersonagem;
    private EditText nomeJogador;
    private TextView textoExibicaoPersonagem;
    private TextView textoExibicaoJogador;
    private TextView textoExibicaoXp;
    private TextView textoExibicaoHp;
    private TextView textoExibicaoAtq;
    private AlertDialog.Builder dialog;

    public ContaConfigFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_conta_config, container, false);

        botaoCriarPersonagem = (ImageView) view.findViewById(R.id.botaoCriarPersonagemid);
        textoExibicaoPersonagem = (TextView) view.findViewById(R.id.textoExibicaoPersonagemid);
        textoExibicaoJogador = (TextView) view.findViewById(R.id.textoExibicaoJogadorid);
        textoExibicaoXp = (TextView) view.findViewById(R.id.textoExibicaoXpid);
        textoExibicaoHp = (TextView) view.findViewById(R.id.textoExibicaoHpid);
        textoExibicaoAtq = (TextView) view.findViewById(R.id.textoExibicaoAtqid);
        nomePersonagem = (EditText) view.findViewById(R.id.nomeNovoPersonagemid);
        nomeJogador = (EditText) view.findViewById(R.id.nomeNovoJogadorid);


        botaoCriarPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String jogadorNome = nomeJogador.getText().toString();
                final String personagemNome = nomePersonagem.getText().toString();

                final Personagens personagens = new Personagens();
                personagens.setNomePersonagem(personagemNome);
                personagens.setNomeJogador(jogadorNome);
                personagens.setAtq(80);
                personagens.setHp(1080);
                personagens.setXp(0);


                personagensReferencias.child(personagemNome).setValue(personagens);

                personagensReferencias.child(personagemNome).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dialog = new AlertDialog.Builder(getContext());

                        dialog.setTitle("Dados do jogador:");
                        dialog.setMessage(String.valueOf(dataSnapshot.getValue()));

                        dialog.create();
                        dialog.show();

                        //Log.i("PERSON", dataSnapshot.getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                personagensReferencias.child(personagemNome).child("nomePersonagem").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        textoExibicaoPersonagem.setText("•Nome personagem: " + String.valueOf(dataSnapshot.getValue()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                personagensReferencias.child(personagemNome).child("nomeJogador").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        textoExibicaoJogador.setText("•Nome jogador: " + String.valueOf(dataSnapshot.getValue()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                personagensReferencias.child(personagemNome).child("xp").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        textoExibicaoXp.setText("•Xp: " + String.valueOf(dataSnapshot.getValue()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                personagensReferencias.child(personagemNome).child("hp").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        textoExibicaoHp.setText("•Hp:" + String.valueOf(dataSnapshot.getValue()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                personagensReferencias.child(personagemNome).child("atq").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        textoExibicaoAtq.setText("•Ataque: " + String.valueOf(dataSnapshot.getValue()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        return view;
    }

}
