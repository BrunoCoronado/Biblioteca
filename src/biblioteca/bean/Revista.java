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
public class Revista extends Ejemplar{
    private String compañia;
    private String fecha;

    public String getCompañia() {
        return compañia;
    }

    public void setCompañia(String compañia) {
        this.compañia = compañia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Revista() {
    }
    
    public Revista(String compañia, String fecha, int idTabla, String id, String titulo, String tema, int estado) {
        super(idTabla, id, titulo, tema, estado);
        this.compañia = compañia;
        this.fecha = fecha;
    }
}
