package br.unb.appdev.igor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    String msg = "Android : ";

    private ImageButton new_adventure_button;
    private TextView nomeAventuraCorvali;
    private ImageView imagemExibicaoCorvali;
    private SeekBar seekBarCorvali;
    private TextView seekBarTextCorvali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(msg, "Evento onCreate()");

        //defindo um layout portrait
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //recuperação dos ids
        new_adventure_button = (ImageButton) findViewById(R.id.button_new_adventure);
        nomeAventuraCorvali = (TextView) findViewById(R.id.nomeAventuraCorvaliid);
        imagemExibicaoCorvali = (ImageView) findViewById(R.id.imagemExibicaoCorvali);
        seekBarCorvali = (SeekBar) findViewById(R.id.seekBarCorvaliid);
        seekBarTextCorvali = (TextView) findViewById(R.id.textProgressCorvaliid);

        //abrindo newAdventures
        new_adventure_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewAdventureActivity();
            }
        });



        //recuperando o edit do newAdventures
        Bundle extra = getIntent().getExtras();

        if (extra != null && nomeAventuraCorvali.length() == 0) {
            String textoCorvaliPassado = extra.getString("nomeAventura");
            nomeAventuraCorvali.setText(textoCorvaliPassado);
        }
        if (nomeAventuraCorvali != null && nomeAventuraCorvali.length() != 0) {
            imagemExibicaoCorvali.setBackgroundResource(R.drawable.corvali);

            seekBarCorvali.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    seekBarTextCorvali.setText("próxima sessão: " + progress + "/" + seekBarCorvali.getMax());
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });


        }

    }



    public void openNewAdventureActivity() {

        Intent intent = new Intent(this, NewAdventureActivity.class);
        startActivity(intent);
    }

    //criar um alertDialog
    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder voltarLogin = new AlertDialog.Builder(this);
        voltarLogin.setMessage("Deseja sair?");

        //configurando evento de click negativo e positivo
        voltarLogin.setNegativeButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }


        });

        voltarLogin.setPositiveButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alert = voltarLogin.create();
        alert.show();

    }

}
