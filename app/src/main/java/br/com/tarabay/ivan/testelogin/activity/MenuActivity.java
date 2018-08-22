package br.com.tarabay.ivan.testelogin.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.tarabay.ivan.testelogin.R;
import br.com.tarabay.ivan.testelogin.model.Usuario;

/**
 * Created by itarabay on 21/08/2018.
 */

public class MenuActivity extends AppCompatActivity {

    private TextView tvUsuario;
    private Usuario usuario;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        usuario = (Usuario) getIntent().getExtras().get("usuario");

        tvUsuario = (TextView) findViewById(R.id.tv_usuario);
        tvUsuario.setText("Seja bem vindo " + usuario.getLogin());
    }

    public void onMenuClick(View v){
        if(v.getId() == R.id.btn_cadastro){
            Intent i = new Intent(this, CadastroActivity.class);
            startActivity(i);
        }else if(v.getId() == R.id.btn_sair){
            SharedPreferences sharedPreferences = getSharedPreferences("Teste-Login", 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear().commit();

            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }

}
