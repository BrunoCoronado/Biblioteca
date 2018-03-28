/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.sistema;

import biblioteca.bean.Usuario;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author bruno
 */
public class Reportes {
    private JPanel panelTitulo, panelContenido, panelHeader, panelReportes;
    private JComboBox reportes;
    private JLabel lblTitulo,lblCampo1,lblCampo2,lblCampo3,lblCampo4,lblCampo5,lblCampo6,lblCampo7,lblCampo8,lblCampo9,lblCampo10,lblCampo11,lblCampo12;
    
    public void mostrarReportes(){
        panelContenido = new JPanel(new BorderLayout(15,15));
        panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelHeader = new JPanel(new BorderLayout(15,15));
        panelReportes = new JPanel(new GridLayout(10,0));
        
        reportes = new JComboBox();
        reportes.addActionListener(new CambiarReporte());
        reportes.addItem("Usuarios con más material prestado");
        reportes.addItem("Autores con más material de lectura");
        reportes.addItem("Revistas más prestadas");
        reportes.addItem("Libros más prestados");
        reportes.addItem("Libros más consultado");
        
        
        agregarReporte();
        panelTitulo.add(lblTitulo);
        panelHeader.add(panelTitulo, BorderLayout.CENTER);
        panelHeader.add(reportes, BorderLayout.EAST);
        
        panelContenido.add(panelHeader, BorderLayout.NORTH);
        panelContenido.add(panelReportes, BorderLayout.CENTER);
        
        Sistema.panel.add(panelContenido, "contenidoReportes");
        Sistema.cardLayout.show(Sistema.panel, "contenidoReportes");
    }

    private void agregarReporte() {
        lblTitulo = new JLabel(reportes.getSelectedItem().toString());
        switch(reportes.getSelectedIndex()){
            case 0:
                reporteUsuarios();
                break;
            case 1:
               reporteAutores();
                break;
            case 2:
               reporteRevistas();
                break;
            case 3:
                reporteLibrosPrestados();
                break;
            case 4:
                reporteLibrosConsultados();
                break;
        }
    }

    private void reporteUsuarios() {
        Usuario[] usr = Sistema.usuarios;
        Usuario temp = new Usuario();
        
        
        try {  
            
            for (int i = 1; i < Sistema.contadorUsuarios; i++) {
                System.out.println(usr[i].getLibrosPrestados());
            }
            //metodo burbuja para ordenar descendentemente
            for (int i = 1; i < Sistema.contadorUsuarios; i++) {
                if(usr[i].getLibrosPrestados()<usr[i+1].getLibrosPrestados()){
                    temp = usr[i];
                    usr[i] = usr[i+1];
                    usr[i+1] = temp;
                }
            }
            
            for (int i = 1; i < Sistema.contadorUsuarios; i++) {
                System.out.println(usr[i].getLibrosPrestados());
            }
            
            //agregar contenido al panel
            
            lblCampo1 = new JLabel("No.   Usuario");
            lblCampo2 = new JLabel("1.   "+usr[0].getUsuario());
            lblCampo3 = new JLabel("2.   "+usr[1].getUsuario());
            lblCampo3 = new JLabel("3.   "+usr[2].getUsuario());
            lblCampo5 = new JLabel("4.   "+usr[3].getUsuario());
            lblCampo6 = new JLabel("5.   "+usr[4].getUsuario());
            
            panelReportes.add(lblCampo1);
            panelReportes.add(lblCampo2);
            panelReportes.add(lblCampo3);
            panelReportes.add(lblCampo4);
            panelReportes.add(lblCampo5);
            panelReportes.add(lblCampo6);
        } catch (Exception e) {
        }
    }

    private void reporteAutores() {
        
    }

    private void reporteRevistas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void reporteLibrosPrestados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void reporteLibrosConsultados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private class CambiarReporte implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            agregarReporte();
        }
    }
}
