package br.com.tarabay.ivan.testelogin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.tarabay.ivan.testelogin.R;
import br.com.tarabay.ivan.testelogin.model.Usuario;

/**
 * Created by itarabay on 22/08/2018.
 */

public class UsuarioAdapter extends BaseAdapter {

    private ArrayList<Usuario> usuarios;
    private Context context;

    public UsuarioAdapter(ArrayList<Usuario> usuarios, Context context) {
        this.usuarios = usuarios;
        this.context = context;
    }

    @Override
    public int getCount() {
        return usuarios.size();
    }

    @Override
    public Object getItem(int i) {
        return usuarios.get(i);
    }

    @Override
    public long getItemId(int i) {
        return usuarios.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater =   (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.lv_usuarios, null);

        TextView tvId = (TextView) linearLayout.findViewById(R.id.tv_id);
        TextView tvLogin = (TextView) linearLayout.findViewById(R.id.tv_login);

        try{tvId.setText(String.valueOf(usuarios.get(i).getId()));}catch (Exception e){}
        try{tvLogin.setText(usuarios.get(i).getLogin());}catch (Exception e){}

        return linearLayout;
    }
}
