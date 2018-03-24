/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.sistema;

import biblioteca.bean.Libro;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bruno
 */
public class ConsultaMaterial {
    private String[] headers;
    private Object[][] dataLibros; 
    private Object[][] dataRevistas; 
    private Object[][] dataTesis; 
    private JTable contenido = new JTable();
    private JComboBox filtro;
    private JLabel lblFiltro;
    private JLabel lblBusqueda;
    private JTextField txtBusqueda;
    private JButton btnBuscar;
    private JButton cargarContenido;
    private JButton btnLogIn;
    private DefaultTableModel modelo; 
    private JPanel panelAcciones = new JPanel(new FlowLayout());
    private JPanel panelFunciones = new JPanel(new BorderLayout(15,15));
    private JButton btnEditar,btnVer,btnEliminar;
    private boolean opcionTablaSeleccionado = false;
    
    private CargaMasiva cargaMasiva = new CargaMasiva();
    
    public void verMateria(){
        
        JPanel contenidoVerMaterial = new JPanel(new BorderLayout());
        Sistema.ventana.setTitle("Consulta de Material - Biblioteca");
        
        JPanel panelHeader = new JPanel(new BorderLayout(15,15));
        JPanel panelOpciones = new JPanel(new BorderLayout(15,15));
        JPanel panelFiltros = new JPanel();
        JPanel panelBusqueda = new JPanel();
        
        lblFiltro = new JLabel("Filtro por:  ");
        panelFiltros.add(lblFiltro);
        
        filtro = new JComboBox();
        filtro.addItem("Libros");
        filtro.addItem("Revistas");
        filtro.addItem("Tesis");
        filtro.addActionListener(new CambioContenido());
        panelFiltros.add(filtro);
        
        panelOpciones.add(panelFiltros, BorderLayout.EAST);
        
        lblBusqueda = new JLabel("Ingrese el Titulo:  ");
        txtBusqueda = new JTextField(12);
        btnBuscar = new JButton("Buscar");
        panelBusqueda.add(lblBusqueda);
        panelBusqueda.add(txtBusqueda);
        panelBusqueda.add(btnBuscar);
        
        panelOpciones.add(panelBusqueda, BorderLayout.WEST);
        
        panelHeader.add(panelOpciones, BorderLayout.SOUTH);
        
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTitulo = new JLabel("Material de Lectura");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        panelTitulo.add(lblTitulo);
        panelHeader.add(panelTitulo, BorderLayout.CENTER);
        
        panelFunciones.removeAll();
        panelAcciones.removeAll();
        
        //control de usuarios y sesiones
        try {
            //1= usuario normal
            //0= usuario administrador
            if(Sistema.usuarioLogeado.getNivel()==1){
                
            }else{
                //btnEditar = new JButton("Editar");
                //btnEliimirar = new JButton("Eliminar");
                //acciones.add(btnEditar);
                //acciones.add(btnEliimirar);
                
                cargarContenido = new JButton("Carga Masiva");
                
                cargarContenido.addActionListener(new MostrarCargaMasiva());
                
                panelAcciones.add(cargarContenido);
            }
            btnLogIn = new JButton("Log Out");
            btnLogIn.addActionListener(new CerrarSesion());
        } catch (Exception e) {
            btnLogIn = new JButton("Log In");
            
            btnLogIn.addActionListener(new MostrarLogin());
        }
        
        
        btnEliminar = new JButton("Eliminar");
        btnEditar = new JButton("Editar");
        btnVer = new JButton("Ver");     
        
        btnEliminar.setName("Eliminar");
        btnEditar.setName("Editar");
        btnVer.setName("Ver");
        
        btnEliminar.addActionListener(new EliminarEjemplar());
        btnEditar.addActionListener(new EditarEjemplar());
        btnVer.addActionListener(new VerEjemplar());
        
        organizarMaterial(filtro.getSelectedItem().toString());
        
        
        panelFunciones.add(panelAcciones, BorderLayout.CENTER);
        contenidoVerMaterial.add(panelFunciones, BorderLayout.SOUTH);
        
        panelHeader.add(btnLogIn, BorderLayout.EAST);
        
        contenidoVerMaterial.add(panelHeader, BorderLayout.NORTH);
        
        JPanel panelLibros = new JPanel();
        
        //tabla no editable
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        
        //eventos de la tabla
        contenido.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                
                int column = contenido.getColumnModel().getColumnIndexAtX(me.getX());
                int row = me.getY()/contenido.getRowHeight();
                
                if(row < contenido.getRowCount() && row >= 0 && column < contenido.getColumnCount() && column >= 0 && opcionTablaSeleccionado == false){
                    Object object = contenido.getValueAt(row, column);
                    if(object instanceof JButton){
                        ((JButton)object).doClick();
                        JButton btn = (JButton) object;
                        opcionTablaSeleccionado = true;
                        //botonTablaPresionado(btn);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                opcionTablaSeleccionado = false;
            }
        });
        
        JScrollPane scroll = new JScrollPane(contenido);
        panelLibros.add(scroll);
        contenidoVerMaterial.add(panelLibros, BorderLayout.CENTER);
        
        //contenido material
        //funcionalidad libros en la tabla
        
        /*try {
            if(Sistema.usuarioLogeado.getNivel()==0){
                
                //panelFunciones.add(panelAcciones, BorderLayout.CENTER);
            }else{
                //btn prestar
            }
            //panelFunciones.add(panelAcciones, BorderLayout.CENTER);
        } catch (Exception e) {}*/
        
        
        
        Sistema.panel.add(contenidoVerMaterial, "contenidoVerMaterial");
        Sistema.cardLayout.show(Sistema.panel, "contenidoVerMaterial");
        
        Sistema.ventana.add(Sistema.panel);
    }

    private void organizarMaterial(String materia) {
        try {
            switch(materia){
                case "Libros":
                    try {
                        if(Sistema.usuarioLogeado.getNivel()==1){
                            headers = new String[] {"ID", "Titulo", "Autor", "Tipo", "Estado",""};
                            dataLibros = new Object[50][6];
                        }else{
                            headers = new String[] {"ID", "Titulo", "Autor", "Tipo", "Estado","",""};
                            dataLibros = new Object[50][7];
                        }
                    } catch (Exception e) {
                        headers = new String[] {"ID", "Titulo", "Autor", "Tipo", "Estado",""};
                        dataLibros = new Object[50][6];
                    }
                    
                    if (Sistema.contadorLibros>0) {
                        añadirLibros();
                    }
                    
                    modelo.setDataVector(dataLibros, headers);
                    break;
                case "Revistas":
                    try {
                        if(Sistema.usuarioLogeado.getNivel()==1){
                            headers = new String[] {"ID", "Titulo", "Compañia", "Tipo", "Estado",""};
                            dataRevistas = new Object[50][6];
                        }else{
                            headers = new String[] {"ID", "Titulo", "Compañia", "Tipo", "Estado","",""};
                            dataRevistas = new Object[50][7];
                        }
                    } catch (Exception e) {
                        headers = new String[] {"ID", "Titulo", "Compañia", "Tipo", "Estado",""};
                        dataRevistas = new Object[50][6];
                    }
                    
                    if(Sistema.contadorRevistas>0){
                        añadirRevistas();
                    }
                    modelo.setDataVector(dataRevistas, headers);
                    break;
                case "Tesis":
                    try {
                        if(Sistema.usuarioLogeado.getNivel()==1){
                            headers = new String[] {"ID", "Titulo", "Autor", "Tipo", "Estado",""};
                            dataTesis = new Object[50][6];
                        }else{
                            headers = new String[] {"ID", "Titulo", "Autor", "Tipo", "Estado","",""};
                            dataTesis = new Object[50][7];
                        }
                    } catch (Exception e) {
                        headers = new String[] {"ID", "Titulo", "Autor", "Tipo", "Estado",""};
                        dataTesis = new Object[50][6];
                    }
                    if(Sistema.contadorTesis>0){
                        añadirTesis();
                    }
                    modelo.setDataVector(dataTesis, headers);
                    break;
            }
            contenido.setModel(modelo);
            contenido.setDefaultRenderer(Object.class, new TableRender());
        } catch (Exception e) {
            
        }
    }
    
    //estados de los ejemplares
    //0 = disponble
    //1 = ocupado
    //2 = eliminado
    private void añadirLibros(){
        for (int i = 0; i < Sistema.contadorLibros; i++) {
            if (Sistema.libros[i].getEstado()!=2) {
                dataLibros[Sistema.libros[i].getIdTabla()][0] = Sistema.libros[i].getId();
                dataLibros[Sistema.libros[i].getIdTabla()][1] = Sistema.libros[i].getTitulo();
                dataLibros[Sistema.libros[i].getIdTabla()][2] = Sistema.libros[i].getAutor();
                dataLibros[Sistema.libros[i].getIdTabla()][3] = Sistema.libros[i].getTema();
                dataLibros[Sistema.libros[i].getIdTabla()][4] = (Sistema.libros[i].getEstado()==0)?"Disponible":"Ocupado";
                try {
                    if(Sistema.usuarioLogeado.getNivel()==1){
                        dataLibros[Sistema.libros[i].getIdTabla()][5] = btnVer;
                    }else{
                        dataLibros[Sistema.libros[i].getIdTabla()][5] = btnEditar;
                        dataLibros[Sistema.libros[i].getIdTabla()][6] = btnEliminar;
                    }
                } catch (Exception e) {
                    dataLibros[Sistema.libros[i].getIdTabla()][5] = btnVer;
                }
            }
        }
    }
    
    private void añadirRevistas(){
        for (int i = 0; i < Sistema.contadorRevistas; i++) {
            if (Sistema.revistas[i].getEstado()!=2) {
                 dataRevistas[Sistema.revistas[i].getIdTabla()][0] = Sistema.revistas[i].getId();
                dataRevistas[Sistema.revistas[i].getIdTabla()][1] = Sistema.revistas[i].getTitulo();
                dataRevistas[Sistema.revistas[i].getIdTabla()][2] = Sistema.revistas[i].getCompañia();
                dataRevistas[Sistema.revistas[i].getIdTabla()][3] = Sistema.revistas[i].getTema();
                dataRevistas[Sistema.revistas[i].getIdTabla()][4] = (Sistema.revistas[i].getEstado()==0)?"Disponible":"Ocupado";
                try {
                    if(Sistema.usuarioLogeado.getNivel()==1){
                        dataRevistas[Sistema.revistas[i].getIdTabla()][5] = btnVer;
                    }else{
                        dataRevistas[Sistema.revistas[i].getIdTabla()][5] = btnEditar;
                        dataRevistas[Sistema.revistas[i].getIdTabla()][6] = btnEliminar;
                    }
                } catch (Exception e) {
                    dataRevistas[Sistema.revistas[i].getIdTabla()][5] = btnVer;
                }
            }
        }
    }
    
    private void añadirTesis(){
        for (int i = 0; i < Sistema.contadorTesis; i++) {
            if (Sistema.tesis[i].getEstado()!=2) {
                dataTesis[Sistema.tesis[i].getIdTabla()][0] = Sistema.tesis[i].getId();
                dataTesis[Sistema.tesis[i].getIdTabla()][1] = Sistema.tesis[i].getTitulo();
                dataTesis[Sistema.tesis[i].getIdTabla()][2] = Sistema.tesis[i].getAutor();
                dataTesis[Sistema.tesis[i].getIdTabla()][3] = Sistema.tesis[i].getTema();
                dataTesis[Sistema.tesis[i].getIdTabla()][4] = (Sistema.tesis[i].getEstado()==0)?"Disponible":"Ocupado";
                 try {
                    if(Sistema.usuarioLogeado.getNivel()==1){
                        dataTesis[Sistema.tesis[i].getIdTabla()][5] = btnVer;
                    }else{
                        dataTesis[Sistema.tesis[i].getIdTabla()][5] = btnEditar;
                        dataTesis[Sistema.tesis[i].getIdTabla()][6] = btnEliminar;
                    }
                } catch (Exception e) {
                    dataTesis[Sistema.tesis[i].getIdTabla()][5] = btnVer;
                }
            }
        }
    }
    
    private class MostrarCargaMasiva implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            cargaMasiva.mostrarCargaMasiva();
        }
        
    }
    
    private class CambioContenido implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            organizarMaterial(filtro.getSelectedItem().toString());
        }
    }
    
    private class MostrarLogin implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {    
            Login login = new Login();
            login.mostrarLogin();
        }
    
    }
    
    private class CerrarSesion implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Sistema.usuarioLogeado = null;
            btnLogIn.setText("Log In");
            btnLogIn.addActionListener(new MostrarLogin());
            panelAcciones.removeAll();
            panelAcciones.add(btnVer);
            panelFunciones.add(panelAcciones, BorderLayout.CENTER);
            organizarMaterial(filtro.getSelectedItem().toString());
            Sistema.ventana.repaint();
        }
    }
    
    private class VerEjemplar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            VerMaterial verMaterial = new VerMaterial();
            switch(filtro.getSelectedItem().toString()){
                case "Libros":
                    try {
                        for (int i = 0; i < Sistema.libros.length; i++) {
                            if (Sistema.libros[i].getIdTabla()==contenido.getSelectedRow()) {
                                verMaterial.mostrarMaterial(i, filtro.getSelectedItem().toString());
                            }
                        }   
                    } catch (Exception e) {}
                    break;
                case "Revistas":
                    try {
                        for (int i = 0; i < Sistema.revistas.length; i++) {
                            if (Sistema.revistas[i].getIdTabla()==contenido.getSelectedRow()) {
                                verMaterial.mostrarMaterial(i, filtro.getSelectedItem().toString());
                            }
                        }   
                    } catch (Exception e) {}
                    break;
                case "Tesis":
                    try {
                        for (int i = 0; i < Sistema.tesis.length; i++) {
                            if (Sistema.tesis[i].getIdTabla()==contenido.getSelectedRow()) {
                                verMaterial.mostrarMaterial(i, filtro.getSelectedItem().toString());
                            }
                        }   
                    } catch (Exception e) {}
                    break;
            }
        }
    }
    
    private class EliminarEjemplar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                eliminarEjemplarData(filtro.getSelectedItem().toString(), contenido.getSelectedRow());
            } catch (Exception e) {}
        }
    }
    
    private class EditarEjemplar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            //abrir ventana para editar
            EditarMaterial editarMaterial = new EditarMaterial();
            switch(filtro.getSelectedItem().toString()){
                case "Libros":
                    try {
                        for (int i = 0; i < Sistema.libros.length; i++) {
                            if (Sistema.libros[i].getIdTabla()==contenido.getSelectedRow()) {
                                editarMaterial.mostrarEditarMaterial(i, "Libros");
                            }
                        }   
                    } catch (Exception e) {}
                    break;
                case "Revistas":
                    try {
                        for (int i = 0; i < Sistema.revistas.length; i++) {
                            if (Sistema.revistas[i].getIdTabla()==contenido.getSelectedRow()) {
                                editarMaterial.mostrarEditarMaterial(i, "Revistas");
                            }
                        }   
                    } catch (Exception e) {}
                    break;
                case "Tesis":
                    try {
                        for (int i = 0; i < Sistema.tesis.length; i++) {
                            if (Sistema.tesis[i].getIdTabla()==contenido.getSelectedRow()) {
                                editarMaterial.mostrarEditarMaterial(i, "Tesis");
                            }
                        }   
                    } catch (Exception e) {}
                    break;
            }
        }
    }
    
    private class TableRender extends DefaultTableCellRenderer{

        @Override
        public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int row, int column) {
            
            if(o instanceof JButton){
                JButton btn = (JButton)o;
                return btn;
            }
            
            return super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, column); 
        }
    }
    
    private void eliminarEjemplarData(String tipo, int index){
        boolean eliminado = true;
        switch(tipo){
                case "Libros":
                    try {
                        for (int i = 0; i < Sistema.libros.length; i++) {
                            if (eliminado) {
                                if (index==Sistema.libros[i].getIdTabla()) {
                                    eliminado=false;
                                    Sistema.libros[i].setEstado(2);
                                    Sistema.libros[i].setIdTabla(-1);
                                    try {
                                        for (int j = 0; j < Sistema.libros.length ; j++) {
                                            if (j>index ) {
                                                Sistema.libros[j].setIdTabla(Sistema.libros[j].getIdTabla()-1);
                                            }
                                        }
                                    } catch (Exception e) {}
                                }
                            }
                        }
                    } catch (Exception e) {}
                    Sistema.contadorLibrosEliminados++;
                    break;  
                case "Revistas":
                    try {
                        for (int i = 0; i < Sistema.revistas.length; i++) {
                            if (eliminado) {
                                if (index==Sistema.revistas[i].getIdTabla()) {
                                    eliminado=false;
                                    Sistema.revistas[index].setEstado(2);
                                    Sistema.revistas[index].setIdTabla(-1);
                                    try {
                                        for (int j = 0; j < Sistema.revistas.length ; j++) {
                                            if (j>index ) {
                                                Sistema.revistas[j].setIdTabla(Sistema.revistas[j].getIdTabla()-1);
                                            }
                                        }
                                    } catch (Exception e) {}
                                }
                            }
                        }
                    } catch (Exception e) {}
                    Sistema.contadorRevistasEliminadas++;
                    break;
                case "Tesis":
                    try {
                        for (int i = 0; i < Sistema.tesis.length; i++) {
                            if (eliminado) {
                                if (index==Sistema.tesis[i].getIdTabla()) {
                                    eliminado=false;
                                    Sistema.tesis[index].setEstado(2);
                                    Sistema.tesis[index].setIdTabla(-1);
                                    try {
                                        for (int j = 0; j < Sistema.tesis.length ; j++) {
                                            if (j>index ) {
                                                Sistema.tesis[j].setIdTabla(Sistema.tesis[j].getIdTabla()-1);
                                            }
                                        }
                                    } catch (Exception e) {}
                                }
                            }
                        }
                    } catch (Exception e) {}
                    Sistema.contadorTesisEliminadas++;
                    break;
        } 
        organizarMaterial(tipo);
    }
}
