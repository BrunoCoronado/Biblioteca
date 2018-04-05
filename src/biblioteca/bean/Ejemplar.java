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
public class Ejemplar {
   private String usuario; 
   private int idTabla;
   private String id;
   private String titulo;
   private String tema;
   private int estado;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public int getIdTabla() {
        return idTabla;
    }

    public void setIdTabla(int idTabla) {
        this.idTabla = idTabla;
    }
   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Ejemplar() {
    }
   
    public Ejemplar(String usuario, int idTabla, String id, String titulo, String tema, int estado) {
        this.usuario = usuario;
        this.idTabla = idTabla;
        this.id = id;
        this.titulo = titulo;
        this.tema = tema;
        this.estado = estado;
    } 
}
