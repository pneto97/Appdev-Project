package br.unb.appdev.igor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
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
    public enum Fragments {
        HOME,NEWADVENTURE,ONGOING;
    }
    public static Fragments fragAtual;

    private ImageButton new_adventure_button;
    private TextView nomeAventuraCorvali;
    private ImageView imagemExibicaoCorvali;
    private SeekBar seekBarCorvali;
    private TextView seekBarTextCorvali;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView logo;
    private int offset;

    public static FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(msg, "Evento onCreate()");

        //defindo um layout portrait
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        toolbar = findViewById(R.id.toolBar);
        logo = (ImageView) findViewById(R.id.logo);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.nav_aventura:
                        item.setChecked(true);
                        displayMessage("aventura");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_livros:
                        item.setChecked(true);
                        displayMessage("livros");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_notificacoes:
                        item.setChecked(true);
                        displayMessage("notificacoes");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_configuracoes:
                        item.setChecked(true);
                        displayMessage("configuracoes");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_conta:
                        item.setChecked(true);
                        displayMessage("conta");
                        drawerLayout.closeDrawers();
                        return true;

                }
                return false;
            }
        });





//        //recuperação dos ids
//        new_adventure_button = (ImageButton) findViewById(R.id.button_new_adventure);
//        nomeAventuraCorvali = (TextView) findViewById(R.id.nomeAventuraCorvaliid);
//        imagemExibicaoCorvali = (ImageView) findViewById(R.id.imagemExibicaoCorvali);
//        seekBarCorvali = (SeekBar) findViewById(R.id.seekBarCorvaliid);
//        seekBarTextCorvali = (TextView) findViewById(R.id.textProgressCorvaliid);

//        //abrindo newAdventures
//        new_adventure_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openNewAdventureActivity();
//            }
//        });



        //recuperando o edit do newAdventures
        Bundle extra = getIntent().getExtras();

//        if (extra != null && nomeAventuraCorvali.length() == 0) {
//            String textoCorvaliPassado = extra.getString("nomeAventura");
//            nomeAventuraCorvali.setText(textoCorvaliPassado);
//        }
//        if (nomeAventuraCorvali != null && nomeAventuraCorvali.length() != 0) {
//            imagemExibicaoCorvali.setBackgroundResource(R.drawable.corvali);
//
//            seekBarCorvali.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                @Override
//                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                    seekBarTextCorvali.setText("próxima sessão: " + progress + "/" + seekBarCorvali.getMax());
//                }
//
//                @Override
//                public void onStartTrackingTouch(SeekBar seekBar) {
//
//                }
//
//                @Override
//                public void onStopTrackingTouch(SeekBar seekBar) {
//
//                }
//            });
//
//
//        }

        //setando o home fragment
        fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.fragment_container) != null){

            if(savedInstanceState != null){
                return;
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragAtual = Fragments.HOME;
            HomeFragment homeFragment = new HomeFragment();
            fragmentTransaction.add(R.id.fragment_container,homeFragment, null);

            fragmentTransaction.commit();


        }

    }



    public void openNewAdventureActivity() {

        Intent intent = new Intent(this, NewAdventureActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_bar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {

            case R.id.action_editar:
                Toast.makeText(this, "editar selecionado",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_ordenar:
                Toast.makeText(this, "ordenar selecionado",
                        Toast.LENGTH_SHORT).show();
                return true;

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }


    //criar um alertDialog
    @Override
    public void onBackPressed()
    {
        if(fragAtual == Fragments.HOME) {

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
        } else{
            fragmentManager.popBackStackImmediate();
            fragAtual = Fragments.HOME;
        }

    }

    private void displayMessage(String message){

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus){
//
//        // set toolbar logo to center programmatically
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
//        ImageView logo = (ImageView) findViewById(R.id.logo);
//        int offset = (toolbar.getWidth() / 2) - (logo.getWidth() / 2);
//        // set
//        logo.setX(offset);
//
//    }

}
