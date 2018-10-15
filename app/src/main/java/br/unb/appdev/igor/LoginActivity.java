package br.unb.appdev.igor;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.xml.validation.Validator;

import br.unb.appdev.igor.Entidades.Usuarios;
import br.unb.appdev.igor.config.ConfiguracaoFirebase;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmail;
    private EditText loginSenha;
    private ImageView botaoEntrar;
    private TextView cadastrarUsuario;
    private FirebaseAuth autenticacao;
    private Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //definindo um layout portrait
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //recuperando com findViewById
        loginEmail = (EditText) findViewById(R.id.emailLoginid);
        loginSenha = (EditText) findViewById(R.id.senhaLoginid);
        botaoEntrar = (ImageView) findViewById(R.id.botaoEntrarid);
        cadastrarUsuario = (TextView) findViewById(R.id.cadastrarid);


        //evento de click para o "botaoEntrar"
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //verificar se login/senha estão vazios
                if (!loginEmail.getText().toString().equals("") && !loginSenha.getText().toString().equals("")) {

                    //recuperar dados do usuário
                    usuarios = new Usuarios();
                    usuarios.setEmail(loginEmail.getText().toString());
                    usuarios.setSenha(loginSenha.getText().toString());

                    validacaoLogin();
                } else {
                    Toast.makeText(LoginActivity.this, "Preencha adequadamente os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //abrir a activity de cadastro usuário
        cadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
            }
        });
    }

    private void validacaoLogin()

    {
        autenticacao = ConfiguracaoFirebase.getFireBaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuarios.getEmail(), usuarios.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Toast.makeText(LoginActivity.this, "Login efetuado", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Dados incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
        System.exit(0);
    }
}
