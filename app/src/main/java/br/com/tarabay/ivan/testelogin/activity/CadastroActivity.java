package br.com.tarabay.ivan.testelogin.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import br.com.tarabay.ivan.testelogin.R;
import br.com.tarabay.ivan.testelogin.adapter.UsuarioAdapter;
import br.com.tarabay.ivan.testelogin.dao.UsuarioDAO;
import br.com.tarabay.ivan.testelogin.helper.DatabaseHelper;
import br.com.tarabay.ivan.testelogin.model.Usuario;
import br.com.tarabay.ivan.testelogin.task.ListaUsuarioTask;

/**
 * Created by itarabay on 22/08/2018.
 */

public class CadastroActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etSenha;


    private ListView lvUsuarios;
    private UsuarioAdapter usuarioAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etLogin = (EditText) findViewById(R.id.et_login);
        etSenha = (EditText) findViewById(R.id.et_senha);

        lvUsuarios = (ListView) findViewById(R.id.lv_usuarios);

        gerarLista();
    }

    public void gerarLista(){
        ListaUsuarioTask task = new ListaUsuarioTask(this,lvUsuarios, usuarioAdapter);
        task.execute();
    }

    public void salvar(View v){
        boolean invalido = false;

        if(etLogin.getText().toString().equals("")){
            etLogin.setError("Obrigatório");
            invalido = true;
        }

        if(etSenha.getText().toString().equals("")){
            etSenha.setError("Obrigatório");
            invalido = true;
        }

        if(!invalido){
            try {
                etLogin.setError(null);
                etSenha.setError(null);

                DatabaseHelper databaseHelper = new DatabaseHelper(this);
                UsuarioDAO usuarioDAO = new UsuarioDAO(databaseHelper.getConnectionSource());
                if(!usuarioDAO.verificarSeUsuarioExiste(etLogin.getText().toString())) {
                    Usuario usuario = new Usuario();
                    usuario.setLogin(etLogin.getText().toString());
                    usuario.setSenha(etSenha.getText().toString());
                    usuarioDAO.create(usuario);
                    etSenha.setText(null);
                    etLogin.setText(null);
                    gerarLista();
                } else{
                    Toast.makeText(this, "Já existe um usuário cadastrado para o login informado", Toast.LENGTH_LONG).show();
                }

            }catch (Exception e){
                Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        gerarLista();
    }
}
