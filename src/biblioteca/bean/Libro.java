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
public class Libro extends Ejemplar{
    private String autor;
    private int paginas;
    private int contadorVisto;
    private int contadorPrestado;

    public int getContadorVisto() {
        return contadorVisto;
    }

    public void setContadorVisto(int contadorVisto) {
        this.contadorVisto = contadorVisto;
    }

    public int getContadorPrestado() {
        return contadorPrestado;
    }

    public void setContadorPrestado(int contadorPrestado) {
        this.contadorPrestado = contadorPrestado;
    }

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
    
    public Libro(String usuario,int contadorVisto, int contadorPrestado, String autor, int paginas, int idTabla, String id, String titulo, String tema, int estado) {
        super(usuario, idTabla, id, titulo, tema, estado);
        this.autor = autor;
        this.paginas = paginas;
        this.contadorVisto = contadorVisto;
        this.contadorPrestado = contadorPrestado;
    }
    
    
}
