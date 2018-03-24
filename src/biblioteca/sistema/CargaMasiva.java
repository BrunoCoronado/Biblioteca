/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.sistema;

import biblioteca.bean.Libro;
import biblioteca.bean.Revista;
import biblioteca.bean.Tesis;
import biblioteca.bean.Usuario;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author bruno
 */
public class CargaMasiva {
    private JLabel lblTitulo;
    private JTextArea taContenido;
    private JButton cancelar;
    private JButton cargar;
    private JButton btnCerrarSesion;
    
    public CargaMasiva() {
        lblTitulo = new JLabel("Carga Masiva");
        taContenido = new JTextArea();
        cancelar = new JButton("Cancelar");
        cargar = new JButton("Cargar");
        btnCerrarSesion = new JButton("Log Out");
    }
    
    public void mostrarCargaMasiva(){
        JPanel panelCargaMasiva = new JPanel(new BorderLayout());
        
        cargar.addActionListener(new CargarContenido());
        cancelar.addActionListener(new Cancelar());
        JPanel panelBotones = new JPanel();
        panelBotones.add(cargar);
        panelBotones.add(cancelar);
        
        JPanel panelHeader = new JPanel(new BorderLayout(15, 15));
        JPanel panelTitulo = new JPanel();
        panelTitulo.add(lblTitulo);
        panelHeader.add(panelTitulo, BorderLayout.CENTER);
        btnCerrarSesion.addActionListener(new CerrarSesion());
        panelHeader.add(btnCerrarSesion, BorderLayout.EAST);
        
        panelCargaMasiva.add(panelHeader, BorderLayout.NORTH);
        panelCargaMasiva.add(taContenido, BorderLayout.CENTER);
        panelCargaMasiva.add(panelBotones, BorderLayout.SOUTH);
        
        Sistema.panel.add(panelCargaMasiva, "panelCargaMasiva");
        Sistema.cardLayout.show(Sistema.panel, "panelCargaMasiva");
    }
    
    private class CargarContenido implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            separarContenido(taContenido.getText());
            //Sistema.cardLayout.show(Sistema.panel, "contenidoVerMaterial");
            taContenido.setText("");
            Sistema.consulta.verMateria();
        }

        private void separarContenido(String texto) {
            try {
                texto = texto.replace("\n", "");
                String[] contenido = texto.split("\\|");
                for (int i = 0; i < contenido.length; i++) {
                    String[] ejemplar = contenido[i].split(";");
                    
                    switch(ejemplar[0]){
                        case "0":
                            Sistema.libros[Sistema.contadorLibros] = new Libro(ejemplar[2], Integer.parseInt(ejemplar[4]),(Sistema.contadorLibros-Sistema.contadorLibrosEliminados), "LIB-"+(Sistema.contadorLibros+1), ejemplar[1], ejemplar[3], 0);
                            Sistema.contadorLibros++;// = Sistema.contadorLibros++;
                            break;
                        case "1":
                            Sistema.revistas[Sistema.contadorRevistas] = new Revista(ejemplar[2], ejemplar[3],(Sistema.contadorRevistas-Sistema.contadorRevistasEliminadas), "REV-"+(Sistema.contadorRevistas+1), ejemplar[1], ejemplar[4], 0);
                            Sistema.contadorRevistas++;// = Sistema.contadorRevistas++;
                            break;
                        case "2":
                            Sistema.tesis[Sistema.contadorTesis] = new Tesis(ejemplar[2], ejemplar[4], ejemplar[5],(Sistema.contadorTesis-Sistema.contadorTesisEliminadas   ), "TES-"+(Sistema.contadorTesis+1), ejemplar[1], ejemplar[3], 0);
                            Sistema.contadorTesis++;
                            break;
                        case "3":
                            Sistema.usuarios[Sistema.contadorUsuarios] = new Usuario(ejemplar[1], ejemplar[4], ejemplar[2], ejemplar[3], 1);
                            Sistema.contadorUsuarios++;
                            break;
                    }
                    ejemplar = null;
                }
                /*Libro[] libs = Sistema.libros;
                System.out.println(libs[0].getAutor());*/
            } catch (Exception e) {
                System.out.println("error en ejecucion");
                e.printStackTrace();
            }
            
        }
    }
    
    private class Cancelar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            //Sistema.cardLayout.show(Sistema.panel, "contenidoVerMaterial");
            Sistema.consulta.verMateria();
        }  
    }
    
    private class CerrarSesion implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Sistema.usuarioLogeado = null;
            Sistema.consulta.verMateria();
        }
    }
}
