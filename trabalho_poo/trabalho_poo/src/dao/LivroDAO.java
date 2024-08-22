package dao;

import entity.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivroDAO extends BaseDAO{

    public void inserir(Livro l){
        String sql = """
            insert into livro(nome_livro) values(?);
                """;
        // try - with - resources
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            // Recurso bem caro
            pre.setString(1,l.getNome_livro());
            pre.execute();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void atualizar(Livro l){
        String sql = """
            update pessoa set nome_livro = ? where id_livro = ?;
                """;
        // try - with - resources
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            // Recurso bem caro
            pre.setString(1,l.getNome_livro());
            pre.setInt(2,l.getId_livro());
            pre.execute();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deletar(Livro l){
        String sql = """
            delete from livro where id_livro = ?;
                """;
        // try - with - resources
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            // Recurso bem caro
            pre.setInt(1,l.getId_livro());
            pre.execute();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Livro> obterTodos(){
        List<Livro> lista = new ArrayList<>();
        String sql = """
            select id_livro, nome from livro
            order by nome_livro asc;
                """;
        // try - with - resources
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            // Recurso bem caro
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                Livro l = new Livro();
                // Selecionado um registro da consulta
                l.setId_livro(rs.getInt("id_livro"));
                l.setNome_livro(rs.getString("nome_livro"));
                lista.add(l);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public Optional<Livro> obterPeloId_livro(int id_livro){
        String sql = """
            select id_livro, nome_livro from livro
            where id_livro = ?
            order by nome_livro asc;
                """;
        // try - with - resources
        try(Connection con = con();
            PreparedStatement pre = con.prepareStatement(sql)){
            pre.setInt(1,id_livro);
            // Recurso bem caro
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                Livro l = new Livro();
                // Selecionado um registro da consulta
                l.setId_livro(rs.getInt("id_livro"));
                l.setNome_livro(rs.getString("nome_livro"));
                return Optional.of(l);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
