/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.sistema;

import biblioteca.bean.Libro;
import biblioteca.bean.Revista;
import biblioteca.bean.Tesis;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author bruno
 */
public class VerMaterial {
    private JFrame ventana;
    private JLabel lblCampo1;
    private JLabel lblCampo2;
    private JLabel lblCampo3;
    private JLabel lblCampo4;
    private JLabel lblCampo5;
    private JLabel lblCampo6;
    private JLabel lblCampo7;
    
    public void mostrarMaterial(int idMaterial, String tipo){
        ventana = new JFrame();
        ventana.setLayout(new GridLayout(10, 1));
        
        switch(tipo){
            case "Libros":
                //agregar contenido libro
                mostrarLibros(idMaterial);
                break;
            case "Revistas":
                //agregar contenido revista
                mostrarRevistas(idMaterial);
                break;
            case "Tesis":
                //agregar contenido tesis
                mostrarTesis(idMaterial);
                break;
        }
        ventana.pack();
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void mostrarLibros(int idMaterial){
        Libro libro = Sistema.libros[idMaterial];
        
        lblCampo1 = new JLabel("ID: "+ libro.getId());
        lblCampo2 = new JLabel("Título: "+ libro.getTitulo());
        lblCampo3 = new JLabel("Autor: "+ libro.getAutor());
        lblCampo4 = new JLabel("No. Paginas: "+ libro.getPaginas());
        lblCampo5 = new JLabel("Tema: "+ libro.getTema());
        lblCampo6 = new JLabel("Estado: "+ libro.getEstado());
        
        ventana.add(lblCampo1);
        ventana.add(lblCampo2);
        ventana.add(lblCampo3);
        ventana.add(lblCampo4);
        ventana.add(lblCampo5);
        ventana.add(lblCampo6);
    }
    
    private void mostrarRevistas(int idMaterial){
        Revista revista = Sistema.revistas[idMaterial];
        
        lblCampo1 = new JLabel("ID: "+ revista.getId());
        lblCampo2 = new JLabel("Título: "+ revista.getTitulo());
        lblCampo3 = new JLabel("Compañia: "+ revista.getCompañia());
        lblCampo4 = new JLabel("Fecha: "+ revista.getFecha());
        lblCampo5 = new JLabel("Tema: "+ revista.getTema());
        lblCampo6 = new JLabel("Estado: "+ revista.getEstado());
        
        ventana.add(lblCampo1);
        ventana.add(lblCampo2);
        ventana.add(lblCampo3);
        ventana.add(lblCampo4);
        ventana.add(lblCampo5);
        ventana.add(lblCampo6);
    }
    
    private void mostrarTesis(int idMaterial){
        Tesis tesis = Sistema.tesis[idMaterial];
        
        lblCampo1 = new JLabel("ID: "+ tesis.getId());
        lblCampo2 = new JLabel("Título: "+ tesis.getTitulo());
        lblCampo3 = new JLabel("Autor: "+ tesis.getAutor());
        lblCampo4 = new JLabel("Grado: "+ tesis.getGrado());
        lblCampo5 = new JLabel("Tema: "+ tesis.getTema());
        lblCampo6 = new JLabel("Año: "+ tesis.getAño());
        lblCampo6 = new JLabel("Estado: "+ tesis.getEstado());
        
        ventana.add(lblCampo1);
        ventana.add(lblCampo2);
        ventana.add(lblCampo3);
        ventana.add(lblCampo4);
        ventana.add(lblCampo5);
        ventana.add(lblCampo6);
    }
}
