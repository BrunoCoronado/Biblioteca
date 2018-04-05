/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.bean;

import biblioteca.sistema.Sistema;

/**
 *
 * @author bruno
 */
public class Usuario {
    private int idTabla;
    private int estado;
    private int librosPrestados;
    private String usuario;
    private String contraseña;
    private String nombre;
    private String apellido;
    private int nivel;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public int getIdTabla() {
        return idTabla;
    }

    public void setIdTabla(int idTabla) {
        this.idTabla = idTabla;
    }

    public int getLibrosPrestados() {
        return librosPrestados;
    }

    public void setLibrosPrestados(int librosPrestados) {
        this.librosPrestados = librosPrestados;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Usuario() {
    }

    public Usuario(int idTabla,String usuario, String contraseña, String nombre, String apellido, int nivel) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nivel = nivel;
        this.idTabla = idTabla;
    }
    
    public Usuario(String usuario, String contraseña, String nombre, String apellido, int nivel) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nivel = nivel;
    }

    public Usuario(int idTabla, int estado, int librosPrestados, String usuario, String contraseña, String nombre, String apellido, int nivel) {
        this.idTabla = idTabla;
        this.estado = estado;
        this.librosPrestados = librosPrestados;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nivel = nivel;
    }
    
    
    
    public boolean validarSesion(String usuario, String contraseña){
        try {
            for (int i = 0; i < Sistema.usuarios.length; i++) {
            if(Sistema.usuarios[i].getUsuario().equals(usuario)&&Sistema.usuarios[i].getContraseña().equals(contraseña)){
                Sistema.usuarioLogeado = Sistema.usuarios[i];
                return true;
            }
        }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
