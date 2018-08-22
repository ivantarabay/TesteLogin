package br.com.tarabay.ivan.testelogin.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.tarabay.ivan.testelogin.adapter.UsuarioAdapter;
import br.com.tarabay.ivan.testelogin.dao.UsuarioDAO;
import br.com.tarabay.ivan.testelogin.helper.DatabaseHelper;
import br.com.tarabay.ivan.testelogin.model.Usuario;

/**
 * Created by itarabay on 22/08/2018.
 */

public class ListaUsuarioTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private ListView lvUsuarios;
    private UsuarioAdapter usuarioAdapter;

    private ProgressDialog progressDialog;


    public ListaUsuarioTask(Context context, ListView lvUsuarios, UsuarioAdapter usuarioAdapter) {
        this.context = context;
        this.lvUsuarios = lvUsuarios;
        this.usuarioAdapter = usuarioAdapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            UsuarioDAO usuarioDAO = new UsuarioDAO(databaseHelper.getConnectionSource());
            ArrayList<Usuario> usuarios = (ArrayList<Usuario>) usuarioDAO.queryForAll();
            usuarioAdapter = new UsuarioAdapter(usuarios, context);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        lvUsuarios.setAdapter(usuarioAdapter);
        progressDialog.dismiss();
    }
}
