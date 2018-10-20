package br.unb.appdev.igor;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NewAdventureActivity extends AppCompatActivity {

    private EditText nomeNovaAventura;
    private ImageView botaoPronto;
    private ImageView botaoXNewAdventure;
    private TextView iconeFecharNewAdventure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_adventure);

        //definindo um layout portrait
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //recuperando os ids
        nomeNovaAventura = (EditText) findViewById(R.id.nomeAventuraid);
        botaoPronto = (ImageView) findViewById(R.id.botaoProntoid);
        botaoXNewAdventure = (ImageView) findViewById(R.id.x_new_adventureid);
        iconeFecharNewAdventure = (TextView) findViewById(R.id.icone_fechar_newAdventureid);

        //passar o edit text à activity main
        botaoPronto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewAdventureActivity.this, MainActivity.class);
                intent.putExtra("nomeAventura", nomeNovaAventura.getText().toString());

                startActivity(intent);
            }
        });

        //formas de fechar a activity
        botaoXNewAdventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewAdventureActivity.this, MainActivity.class));
            }
        });

        iconeFecharNewAdventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewAdventureActivity.this, MainActivity.class));
            }
        });
    }
}
