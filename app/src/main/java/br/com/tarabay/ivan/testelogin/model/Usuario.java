package br.com.tarabay.ivan.testelogin.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by itarabay on 21/08/2018.
 */

@DatabaseTable
public class Usuario implements Serializable {

    @DatabaseField(generatedId = true, columnName = "id")
    private int id;
    @DatabaseField
    private String login;
    @DatabaseField
    private String senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
