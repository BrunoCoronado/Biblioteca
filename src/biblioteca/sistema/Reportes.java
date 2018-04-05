/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.sistema;

import biblioteca.bean.Autor;
import biblioteca.bean.Libro;
import biblioteca.bean.Revista;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bruno
 */
public class Reportes {
    private JPanel panelTitulo, panelContenido, panelHeader, panelReportes;
    private JComboBox reportes;
    private JButton btnRegresar, btnCerrarSesion;
    private JLabel lblTitulo;
    private JTable contenido = new JTable();
    private DefaultTableModel modelo;
    private String[] headers;
    private Object[][] data;
    
    public void mostrarReportes(){
        panelContenido = new JPanel(new BorderLayout(15,15));
        panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelHeader = new JPanel(new BorderLayout(15,15));
        panelReportes = new JPanel(new BorderLayout(10,10));
        
        btnRegresar = new JButton("Regresar");
        btnCerrarSesion = new JButton("Cerrar Sesion");
        
        btnRegresar.addActionListener(new Regresar());
        btnCerrarSesion.addActionListener(new CerrarSesion());
        
        reportes = new JComboBox();
        reportes.addActionListener(new CambiarReporte());
        reportes.addItem("Usuarios con más material prestado");
        reportes.addItem("Autores con más material de lectura");
        reportes.addItem("Revistas más prestadas");
        reportes.addItem("Libros más prestados");
        reportes.addItem("Libros más consultado");
        
        agregarReporte();
        
        JScrollPane scroll = new JScrollPane(contenido);
        panelReportes.add(scroll, BorderLayout.CENTER);
        
        panelTitulo.add(lblTitulo);
        panelHeader.add(panelTitulo, BorderLayout.CENTER);
        panelHeader.add(btnRegresar, BorderLayout.WEST);
        panelHeader.add(btnCerrarSesion, BorderLayout.EAST);
        panelHeader.add(reportes, BorderLayout.SOUTH);
        
        panelContenido.add(panelHeader, BorderLayout.NORTH);
        panelContenido.add(panelReportes, BorderLayout.CENTER);
        
        Sistema.panel.add(panelContenido, "contenidoReportes");
        Sistema.cardLayout.show(Sistema.panel, "contenidoReportes");
    }

    private void agregarReporte() {
        lblTitulo = new JLabel(reportes.getSelectedItem().toString());
        añadirHeaders();
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
        
        
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        
        modelo.setDataVector(data, headers);
        contenido.setModel(modelo);
    }
    
    private void añadirHeaders(){
        switch(reportes.getSelectedIndex()){
            case 0:
                headers = new String[] {"No.", "Usuario", "Libros Prestados"};
                break;
            case 1:
               headers = new String[] {"No.", "Autor", "No. Libros"};
                break;
            case 2:
               headers = new String[] {"No.", "Revista", "No. Prestamos"};
                break;
            case 3:
                headers = new String[] {"No.", "Libro", "No. Prestamos"};
                break;
            case 4:
                headers = new String[] {"No.", "Libro", "No. Consultas"};
                break;
        }
        data = new Object[5][3];
    }
    private void reporteUsuarios() {
        Usuario[] usr = Sistema.usuarios;
        Usuario temp = new Usuario();
        
        /*for (int i = 1; i < Sistema.contadorUsuarios; i++) {
            System.out.println(usr[i].getLibrosPrestados()+"\n");
        }*/
        try {
            //metodo burbuja para ordenar descendentemente
            for (int i = 1; i < Sistema.contadorUsuarios; i++) {
                if(usr[i].getLibrosPrestados()<usr[i+1].getLibrosPrestados()){
                    temp = usr[i];
                    usr[i] = usr[i+1];
                    usr[i+1] = temp;
                }
            }
        } catch (Exception e) {}
        
        for (int i = 1; i < Sistema.contadorUsuarios; i++) {
            System.out.println(usr[i].getLibrosPrestados()+"\n");
        }

        try {    
            for (int i = 1; i < 5; i++) {
                data[i-1][0] = i;
                data[i-1][1] = usr[i].getUsuario();
                data[i-1][2] = usr[i].getLibrosPrestados();
            }
        } catch (Exception e) {}

    }

    private void reporteAutores() {
        Autor[] au = Sistema.autor;
        Autor tmp = new Autor();
        
        try {
            for (int i = 0; i < Sistema.contadorAutor; i++) {
                if(au[i].getConadorLibros()<au[i+1].getConadorLibros()){
                    tmp = au[i];
                    au[i] = au[i+1];
                    au[i+1] = tmp;
                }
            }
        } catch (Exception e) {}
        
        try {
            for (int i = 0; i < 4; i++) {
                data[i][0] = i+1;
                data[i][1] = au[i].getNombre();
                data[i][2] = au[i].getConadorLibros();
            }
        } catch (Exception e) {}
    }

    private void reporteRevistas() {
        Revista[] rv = Sistema.revistas;
        Revista tmp = new Revista();
        
        try {
            for (int i = 0; i < Sistema.contadorRevistas; i++) {
                if(rv[i].getContadorPrestamo()<rv[i+1].getContadorPrestamo()){
                    tmp = rv[i];
                    rv[i] = rv[i+1];
                    rv[i+1] = tmp;
                }
            }
        } catch (Exception e) {}
        
        try {
            for (int i = 0; i < 4; i++) {
                data[i][0] = i+1;
                data[i][1] = rv[i].getTitulo();
                data[i][2] = rv[i].getContadorPrestamo();
            }
        } catch (Exception e) {}
    }

    private void reporteLibrosPrestados() {
        Libro[] lb = Sistema.libros;
        Libro tmp = new Libro();
        
        try {
            
            for (int i = 0; i < Sistema.contadorLibros; i++) {
                if(lb[i].getContadorPrestado()<lb[i+1].getContadorPrestado()){
                    tmp = lb[i];
                    lb[i] = lb[i+1];
                    lb[i+1] = tmp;
                }
            }
        } catch (Exception e) {}
        
        try {
            for (int i = 0; i < 4; i++) {
                data[i][0] = i+1;
                data[i][1] = lb[i].getTitulo();
                data[i][2] = lb[i].getContadorPrestado();
            }
        } catch (Exception e) {}
    }

    private void reporteLibrosConsultados() {
        Libro[] lb = Sistema.libros;
        Libro tmp = new Libro();
        
        try {
            //metodo burbuja para ordenar 
            for (int i = 1; i < Sistema.contadorLibros; i++) {
                if(lb[i].getContadorVisto()<lb[i+1].getContadorVisto()){
                    tmp = lb[i];
                    lb[i] = lb[i+1];
                    lb[i+1] = tmp;
                }
            }
        } catch (Exception e) {}
        
        try {
            for (int i = 0; i < 4; i++) {
                data[i][0] = i+1;
                data[i][1] = lb[i].getTitulo();
                data[i][2] = lb[i].getContadorVisto();
            }
        } catch (Exception e) {}
    }
    
    private class CambiarReporte implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            agregarReporte();
        }
    }
    
    private class CerrarSesion implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Sistema.usuarioLogeado = null;
            Sistema.consulta.verMateria();
        }
   }
   
   private class Regresar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Sistema.consulta.verMateria();
        }
   }
}
