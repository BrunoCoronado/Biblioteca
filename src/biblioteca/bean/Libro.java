/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.bean;

import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class Libro extends Ejemplar{
    private String autor;
    private int paginas;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public Libro() {
    }

    public Libro(String autor, int paginas, int idTabla, String id, String titulo, String tema, int estado) {
        super(idTabla, id, titulo, tema, estado);
        this.autor = autor;
        this.paginas = paginas;
    }
}
