/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.sistema;

import biblioteca.bean.Libro;
import biblioteca.bean.Revista;
import biblioteca.bean.Tesis;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bruno
 */
public class Prestamos {
    private String [] headers;
    private Object[][] data;
    private JScrollPane scroll;
    private JTable contenido = new JTable();
    private JLabel lblTitulo;
    private JButton btnRegresar, btnCerrarSesion, btnDevolver;
    private DefaultTableModel modelo;
    private JPanel panelContenido, panelTitulo, panelMaterial, panelAcciones, panelHeader;
    private boolean opcionTablaSeleccionado = false;
    
    public void mostrarPrestamos(){
        
        panelContenido = new JPanel(new BorderLayout(10,10));
        panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelHeader = new JPanel(new BorderLayout(15, 15));
        
        Sistema.ventana.setTitle("Prestamos - Biblioteca");
        lblTitulo = new JLabel("Mis Prestamos");
        
        btnRegresar = new JButton("Regresar");
        btnCerrarSesion = new JButton("Cerrar Sesion");
        btnDevolver = new JButton("Devolver");
        
        btnRegresar.addActionListener(new Regresar());
        //btnDevolver.addActionListener(new Devolver());
        btnCerrarSesion.addActionListener(new LogOut());
        
        panelHeader.add(btnRegresar, BorderLayout.WEST);
        panelHeader.add(btnCerrarSesion, BorderLayout.EAST);
        panelHeader.add(lblTitulo, BorderLayout.CENTER);
        
        headers = new String[] {"ID", "Titulo", "Autor", "Tipo", "Accion"};
        
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        }; 
        cargarPrestamos();
        contenido.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                int column = contenido.getColumnModel().getColumnIndexAtX(me.getX());
                int row = me.getY()/contenido.getRowHeight();
                
                if(row < contenido.getRowCount() && row >= 0 && column < contenido.getColumnCount() && column >= 0 && opcionTablaSeleccionado == false){
                    Object object = contenido.getValueAt(row, column);
                    if (object instanceof JButton) {
                        ((JButton)object).doClick();
                        JButton btn = (JButton) object;
                        opcionTablaSeleccionado = true;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                opcionTablaSeleccionado = false;
            }
        });
        
        panelContenido.add(panelHeader, BorderLayout.NORTH);
        
        scroll = new JScrollPane(contenido);
        panelContenido.add(scroll, BorderLayout.CENTER);
        
        Sistema.panel.add(panelContenido, "contenidoPrestamos");
        Sistema.cardLayout.show(Sistema.panel, "contenidoPrestamos");
    }

    private void cargarPrestamos() {
        data = new Object[100][5];
        
        int contadorData = 0;
        try {
            for (int i = 0; i < Sistema.libros.length; i++) {
                if (Sistema.libros[i].getUsuario().equals(Sistema.usuarioLogeado.getUsuario()) && Sistema.libros[i].getEstado()!=2) {
                    data[contadorData][0] = Sistema.libros[i].getId();
                    data[contadorData][1] = Sistema.libros[i].getTitulo();
                    data[contadorData][2] = Sistema.libros[i].getAutor();
                    data[contadorData][3] = Sistema.libros[i].getTema();
                    data[contadorData][4] = btnDevolver;
                    contadorData++;
                }
            }
        } catch (Exception e) {}
        try {
            for (int i = 0; i < Sistema.revistas.length; i++) {
                if (Sistema.revistas[i].getUsuario().equals(Sistema.usuarioLogeado.getUsuario()) && Sistema.revistas[i].getEstado()!=2) {
                    data[contadorData][0] = Sistema.revistas[i].getId();
                    data[contadorData][1] = Sistema.revistas[i].getTitulo();
                    data[contadorData][2] = Sistema.revistas[i].getCompañia();
                    data[contadorData][3] = Sistema.revistas[i].getTema();
                    data[contadorData][4] = btnDevolver;
                }
            }
        } catch (Exception e) {}
        try {
            for (int i = 0; i < Sistema.tesis.length; i++) {
                if (Sistema.tesis[i].getUsuario().equals(Sistema.usuarioLogeado.getUsuario()) && Sistema.tesis[i].getEstado()!=2) {
                    data[contadorData][0] = Sistema.tesis[i].getId();
                    data[contadorData][1] = Sistema.tesis[i].getTitulo();
                    data[contadorData][2] = Sistema.tesis[i].getAutor();
                    data[contadorData][3] = Sistema.tesis[i].getTema();
                    data[contadorData][4] = btnDevolver;
                    contadorData++;
                }
            }
        } catch (Exception e) {}
        modelo.setDataVector(data, headers);
        contenido.setModel(modelo);
        contenido.setDefaultRenderer(Object.class, new TableRender());
    }
    
    private class TableRender extends DefaultTableCellRenderer{

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
            if(o instanceof JButton){
                JButton btn = (JButton)o;
                return btn;
            }
            return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    private class DevolverEjemplar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            boolean devuelto = true;
            try {
                /*switch(filtro.getSelectedItem().toString()){
                    case "Libros":
                        try {
                            for (int i = 0; i < Sistema.libros.length; i++) {
                                if (contenido.getSelectedRow() == Sistema.libros[i].getIdTabla()) {
                                    //devolver libro
                                    Libro libroDevuelto = new Libro();
                                    libroDevuelto = Sistema.libros[i];
                                    libroDevuelto.setEstado(0);
                                    libroDevuelto.setUsuario("");
                                    Sistema.libros[i]=libroDevuelto;
                                }
                            }
                        } catch (Exception e) {}
                        break;
                    case "Revistas":
                        try {
                            for (int i = 0; i < Sistema.revistas.length; i++) {
                                if (contenido.getSelectedRow() == Sistema.revistas[i].getIdTabla()) {
                                    //devolver revistas
                                    Revista revistaDevuelta = new Revista();
                                    revistaDevuelta = Sistema.revistas[i];
                                    revistaDevuelta.setEstado(0);
                                    revistaDevuelta.setUsuario("");
                                    Sistema.revistas[1]=revistaDevuelta;
                                }
                            }   
                        } catch (Exception e) {}
                        break;
                    case "Tesis":
                        try {
                            for (int i = 0; i < Sistema.tesis.length; i++) {
                                if (contenido.getSelectedRow() == Sistema.tesis[i].getIdTabla()) {
                                    //devolver Tesis
                                    Tesis tesisDevuelta = new Tesis();
                                    tesisDevuelta = Sistema.tesis[i];
                                    tesisDevuelta.setEstado(0);
                                    tesisDevuelta.setUsuario("");
                                    Sistema.tesis[i]=tesisDevuelta;
                                }
                            }
                        } catch (Exception e) {}
                        break;
                }*/
                
                /*if (devuelto) {
                    for (int i = 0; i < Sistema.libros.length; i++) {
                        if (Sistema.libros[i].getId().equals(modelo.)) {
                            
                        }
                    }
                }*/
                
            } catch (Exception e) {}
            Sistema.usuarioLogeado.setLibrosPrestados(Sistema.usuarioLogeado.getLibrosPrestados()-1);
        }
    }
    
    private class Regresar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Sistema.consulta.verMateria();
        }
    }
    
    private class LogOut implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Sistema.usuarioLogeado = null;
            Sistema.consulta.verMateria();
        }
    }
}
