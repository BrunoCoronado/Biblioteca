/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.sistema;

import biblioteca.bean.Libro;
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
        ventana.setSize(300, 300);
        ventana.setLayout(new GridLayout(1, 10));
        
        switch(tipo){
            case "Libros":
                //agregar contenido libro
                mostrarLibros(idMaterial);
                break;
            case "Revistas":
                //agregar contenido revista
                break;
            case "Tesis":
                //agregar contenido tesis
                break;
        }
        ventana.pack();
        ventana.setVisible(true);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setFocusableWindowState(true);
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
    
    private void mostrarRevistas(int idMaterial){}
    
    private void mostrarTesis(int idMaterial){}
}
