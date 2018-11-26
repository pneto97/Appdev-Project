package br.unb.appdev.igor;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
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
    private FirebaseAuth autenticacao;


    private ImageView botaoCriarPersonagem;
    private EditText nomePersonagem;
    private EditText nomeJogador;
    private TextView textoExibicaoPersonagem;
    private TextView textoExibicaoJogador;
    private TextView textoExibicaoXp;
    private TextView textoExibicaoHp;
    private TextView textoExibicaoAtq;
    private TextView textoClasse;

    private Button botaoGuerreiro;
    private Button botaoMago;
    private Button botaoArqueiro;

    private ConstraintLayout layout;

    int hp;
    int atq;
    int xp;

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
        botaoGuerreiro = (Button) view.findViewById(R.id.botaoGuerreiroid);
        botaoMago = (Button) view.findViewById(R.id.botaoMagoid);
        botaoArqueiro = (Button) view.findViewById(R.id.botaoArqueiroid);
        layout = (ConstraintLayout) view.findViewById(R.id.layoutid);
        textoClasse = (TextView) view.findViewById(R.id.textoClasseid);

        autenticacao = FirebaseAuth.getInstance();
        final String idUsuario = autenticacao.getCurrentUser().getUid();

        botaoGuerreiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setBackgroundResource(R.drawable.guerreiro_background);
                hp = 1080;
                atq = 250;
                textoClasse.setText("Guerreiro");
            }
        });

        botaoMago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setBackgroundResource(R.drawable.mago_background);
                hp = 720;
                atq = 400;
                textoClasse.setText("Mago");
            }
        });

        botaoArqueiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setBackgroundResource(R.drawable.arqueiro_background);
                hp = 800;
                atq = 380;
                textoClasse.setText("Arqueira");
            }
        });

        botaoCriarPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(textoClasse.getText().equals("")){
                    Toast.makeText(getContext(), "Você deve escolher uma classe", Toast.LENGTH_SHORT).show();
                }
                else {
                    String jogadorNome = nomeJogador.getText().toString();
                    final String personagemNome = nomePersonagem.getText().toString();

                    if(nomeJogador.getText().toString().equals("") || nomePersonagem.getText().toString().equals("")){
                        Toast.makeText(getContext(), "Preencha os dados do usuário", Toast.LENGTH_SHORT).show();
                    }

                    else {
                        nomePersonagem.setVisibility(View.INVISIBLE);
                        nomeJogador.setVisibility(View.INVISIBLE);
                        botaoCriarPersonagem.setVisibility(View.INVISIBLE);
                        botaoGuerreiro.setVisibility(View.INVISIBLE);
                        botaoArqueiro.setVisibility(View.INVISIBLE);
                        botaoMago.setVisibility(View.INVISIBLE);

                        final Personagens personagens = new Personagens();
                        personagens.setNomePersonagem(personagemNome);
                        personagens.setNomeJogador(jogadorNome);

                        //player atributos
                        personagens.setAtq(atq);
                        personagens.setHp(hp);
                        personagens.setXp(0);


                        personagensReferencias.child(idUsuario).child("Atributos").setValue(personagens);

                        /*
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
                        */

                        personagensReferencias.child(idUsuario).child("Atributos").child("nomePersonagem").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                textoExibicaoPersonagem.setText("•Nome personagem: " + String.valueOf(dataSnapshot.getValue()));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        personagensReferencias.child(idUsuario).child("Atributos").child("nomeJogador").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                textoExibicaoJogador.setText("•Nome jogador: " + String.valueOf(dataSnapshot.getValue()));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        personagensReferencias.child(idUsuario).child("Atributos").child("xp").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                textoExibicaoXp.setText("•Xp: " + String.valueOf(dataSnapshot.getValue()));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        personagensReferencias.child(idUsuario).child("Atributos").child("hp").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                textoExibicaoHp.setText("•Hp:" + String.valueOf(dataSnapshot.getValue()));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        personagensReferencias.child(idUsuario).child("Atributos").child("atq").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                textoExibicaoAtq.setText("•Ataque: " + String.valueOf(dataSnapshot.getValue()));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }
        });

        return view;
    }

}
