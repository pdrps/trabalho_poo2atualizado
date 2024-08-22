package entity;

public class Autor {
    private int id_autor;
    private String nome_autor;

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public String getNome_autor() {
        return nome_autor;
    }

    public void setNome_autor(String nome_autor) {
        this.nome_autor = nome_autor;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id_autor=" + id_autor +
                ", nome_autor='" + nome_autor + '\'' +
                '}';
    }
}
