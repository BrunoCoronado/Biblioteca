/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.bean;

/**
 *
 * @author bruno
 */
public class Tesis extends Ejemplar{
    private String autor;
    private String grado;
    private String año;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public Tesis() {
    }
    
    public Tesis(String usuario, String autor, String grado, String año, int idTabla, String id, String titulo, String tema, int estado) {
        super(usuario, idTabla, id, titulo, tema, estado);
        this.autor = autor;
        this.grado = grado;
        this.año = año;
    }
}
