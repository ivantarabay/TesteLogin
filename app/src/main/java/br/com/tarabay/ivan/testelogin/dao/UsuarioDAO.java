package br.com.tarabay.ivan.testelogin.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.stmt.query.In;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import br.com.tarabay.ivan.testelogin.model.Usuario;

/**
 * Created by itarabay on 21/08/2018.
 */

public class UsuarioDAO extends BaseDaoImpl<Usuario, Integer> {

    public UsuarioDAO(ConnectionSource connectionSource) throws SQLException {
        super(Usuario.class);
        setConnectionSource(connectionSource);
        initialize();
    }

    public Usuario validarUsuario(String login, String senha) throws Exception{
        try{
            QueryBuilder<Usuario, Integer> queryBuilder = queryBuilder();
            Where<Usuario, Integer> where = queryBuilder.where();
            where.eq("login", login);
            where.and().eq("senha", senha);
            return queryBuilder.queryForFirst();
        }catch (Exception e){
            throw  e;
        }
    }

    public boolean verificarSeUsuarioExiste(String login) throws Exception{
        try{
            QueryBuilder<Usuario, Integer> queryBuilder = queryBuilder();
            queryBuilder.where().eq("login", login);
            if(queryBuilder.query().size() != 0){
                return true;
            } else{
                return false;
            }
        }catch (Exception e){
            throw e;
        }
    }

    public Usuario buscarPorId(int id) throws Exception{
        try{
            QueryBuilder<Usuario, Integer> queryBuilder = queryBuilder();
            Where<Usuario, Integer> where = queryBuilder.where();
            where.eq("id", id);
            return queryBuilder().queryForFirst();
        }catch(Exception e){
            throw e;
        }
    }

}
