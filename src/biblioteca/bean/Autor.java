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
public class Autor {
    private String nombre;
    private int conadorLibros;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getConadorLibros() {
        return conadorLibros;
    }

    public void setConadorLibros(int conadorLibros) {
        this.conadorLibros = conadorLibros;
    }

    public Autor(String nombre, int conadorLibros) {
        this.nombre = nombre;
        this.conadorLibros = conadorLibros;
    }

    public Autor() {
    }
}
