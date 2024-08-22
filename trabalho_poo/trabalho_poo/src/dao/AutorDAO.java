package dao;

import entity.Autor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutorDAO extends BaseDAO{

    public void inserir(Autor a){
        String sql = """
            insert into autor(nome_autor) values(?);
                """;
        // try - with - resources
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            // Recurso bem caro
            pre.setString(1,a.getNome_autor());
            pre.execute();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void atualizar(Autor a){
        String sql = """
            update autor set nome_autor = ? where id_autor = ?;
                """;
        // try - with - resources
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            // Recurso bem caro
            pre.setString(1,a.getNome_autor());
            pre.setInt(2,a.getId_autor());
            pre.execute();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deletar(Autor a){
        String sql = """
            delete from autor where id_autor = ?;
                """;
        // try - with - resources
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            // Recurso bem caro
            pre.setInt(1,a.getId_autor());
            pre.execute();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Autor> obterTodos(){
        List<Autor> lista = new ArrayList<>();
        String sql = """
            select id_autor, nome from autor
            order by nome asc;
                """;
        // try - with - resources
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            // Recurso bem caro
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                Autor a = new Autor();
                // Selecionado um registro da consulta
                a.setId_autor(rs.getInt("id_autor"));
                a.setNome_autor(rs.getString("nome_autor"));
                lista.add(a);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public Optional<Autor> obterPeloId_autor(int id_autor){
        String sql = """
            select id_autor, nome_autor from autor
            where id_autor = ?
            order by nome_autor asc;
                """;
        // try - with - resources
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            pre.setInt(1,id_autor);
            // Recurso bem caro
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                Autor a = new Autor();
                // Selecionado um registro da consulta
                a.setId_autor(rs.getInt("id_autor"));
                a.setNome_autor(rs.getString("nome_autor"));
                return Optional.of(a);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
