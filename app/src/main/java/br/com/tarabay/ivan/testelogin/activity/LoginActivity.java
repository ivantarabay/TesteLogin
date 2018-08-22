package br.com.tarabay.ivan.testelogin.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

import br.com.tarabay.ivan.testelogin.R;
import br.com.tarabay.ivan.testelogin.dao.UsuarioDAO;
import br.com.tarabay.ivan.testelogin.helper.DatabaseHelper;
import br.com.tarabay.ivan.testelogin.model.Usuario;

/**
 * Created by itarabay on 21/08/2018.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etSenha;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = (EditText) findViewById(R.id.et_login);
        etSenha = (EditText) findViewById(R.id.et_senha);

        sharedPreferences = getSharedPreferences("Teste-Login", 0);
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            usuarioDAO = new UsuarioDAO(databaseHelper.getConnectionSource());
        }catch (SQLException e){
            e.printStackTrace();
        }

        int sId = sharedPreferences.getInt("id_usuario", -1);
        if(sId != -1){
            try {
                Usuario usuario = usuarioDAO.buscarPorId(sId);
                Intent i = new Intent(this, MenuActivity.class);
                i.putExtra("usuario", usuario);
                startActivity(i);
                finish();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void login(View v){
        try{

            Usuario usuario = usuarioDAO.validarUsuario(etLogin.getText().toString(), etSenha.getText().toString());

            if(usuario != null){

                editor = sharedPreferences.edit();
                editor.putInt("id_usuario", usuario.getId());
                editor.commit();

                Intent i = new Intent(this, MenuActivity.class);
                i.putExtra("usuario", usuario);
                startActivity(i);
                finish();
            }else{
                Toast.makeText(this, "Login e/ou senha inválidos.", Toast.LENGTH_LONG).show();
            }
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
