package br.unb.appdev.igor;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TelaCombateActivity extends AppCompatActivity {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference cvDatabase = databaseReference.child("CvReference");
    private DatabaseReference personagensReferencias = databaseReference.child("Personagens");
    private FirebaseAuth autenticacao;

    private TextView hpPlayer;
    private TextView atqPlayer;
    private TextView hpMonster;
    private TextView atqMonster;
    private TextView textoResultado;

    private Button atacar;
    private Button defender;
    private Button correr;

    int hpPers;
    int atqPers;
    int hpMonst;
    int atqMonst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_combate);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        hpPlayer = (TextView) findViewById(R.id.hpPlayerid);
        atqPlayer = (TextView) findViewById(R.id.atqPlayerid);
        hpMonster = (TextView) findViewById(R.id.hpMonstroid);
        atqMonster = (TextView) findViewById(R.id.atqMonstroid);
        textoResultado = (TextView) findViewById(R.id.textResultCombateid);

        atacar = (Button) findViewById(R.id.botaoAtacarid);
        defender = (Button) findViewById(R.id.botaoDefenderid);

        autenticacao = FirebaseAuth.getInstance();
        final String idUsuario = autenticacao.getCurrentUser().getUid();

        personagensReferencias.child(idUsuario).child("hp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String hpPersonagem = String.valueOf(dataSnapshot.getValue());
                hpPers = Integer.parseInt(hpPersonagem);

                hpPlayer.setText("•Hp (Jogador): " + hpPers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        personagensReferencias.child(idUsuario).child("atq").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String atqPersonagem = String.valueOf(dataSnapshot.getValue());
                atqPers = Integer.parseInt(atqPersonagem);

                atqPlayer.setText("•Ataque (Jogador): " + atqPers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        cvDatabase.child("new").child("hpMonstro").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String hpMonstro = String.valueOf(dataSnapshot.getValue());
                hpMonst = Integer.parseInt(hpMonstro);

                hpMonster.setText("•Hp (Monstro): " + hpMonst);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        cvDatabase.child("new").child("atqMonstro").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String atqMonstro = String.valueOf(dataSnapshot.getValue());
                atqMonst = Integer.parseInt(atqMonstro);

                atqMonster.setText("•Ataque (Monstro): " + atqMonst);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        atacar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hpPers = hpPers - atqMonst;
                hpMonst = hpMonst - atqPers;

                if(hpMonst <= 0){
                    textoResultado.setText("Você venceu a batalha!");
                    cvDatabase.child("new").child("hpMonstro").setValue(0);

                    textoResultado.setTextColor(getResources().getColor(R.color.new_adv_background));
                    hpMonster.setText("•Hp (Monstro): 0");
                }
                else if(hpPers <= 0){
                    hpPlayer.setText("•Hp (Player): 0" );

                    textoResultado.setTextColor(getResources().getColor(R.color.red_text));
                    textoResultado.setText("Você perdeu a batalha!");
                }
                else {
                    hpPlayer.setText("•Hp (Player): " + hpPers);
                    hpMonster.setText("•Hp (Monstro): " + hpMonst);

                    cvDatabase.child("new").child("hpMonstro").setValue(hpMonst);
                }
            }
        });

        defender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hpPers = hpPers - (atqMonst/2);
                hpMonst = hpMonst - (atqPers/3);

                if(hpMonst <= 0){
                    textoResultado.setText("Você venceu a batalha!");
                    cvDatabase.child("new").child("hpMonstro").setValue(0);

                    textoResultado.setTextColor(getResources().getColor(R.color.new_adv_background));
                    hpMonster.setText("•Hp (Monstro): 0");
                }
                else if(hpPers <= 0){
                    hpPlayer.setText("•Hp (Player): 0");

                    textoResultado.setTextColor(getResources().getColor(R.color.red_text));
                    textoResultado.setText("Você perdeu a batalha!");
                }
                else {
                    hpPlayer.setText("•Hp (Player): " + hpPers);
                    hpMonster.setText("•Hp (Monstro): " + hpMonst);

                    cvDatabase.child("new").child("hpMonstro").setValue(hpMonst);
                }
            }
        });

    }

}
