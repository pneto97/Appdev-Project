package br.unb.appdev.igor;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    String msg = "Android : ";
    private ImageButton new_adventure_button;
    private Button startBotao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(msg, "Evento onCreate()");

        new_adventure_button = (ImageButton) findViewById(R.id.button_new_adventure);
        new_adventure_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewAdventureActivity();
            }
        });

        startBotao = (Button) findViewById(R.id.startid);
        startBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }



    public void openNewAdventureActivity() {

        Intent intent = new Intent(this, NewAdventureActivity.class);
        startActivity(intent);
    }
}
