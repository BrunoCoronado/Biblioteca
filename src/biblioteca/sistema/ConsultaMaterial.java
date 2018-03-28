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
    private Object[][] dataLibros,dataRevistas,dataTesis; 
    private JTable contenido = new JTable();
    private JComboBox filtro;
    private JLabel lblFiltro,lblBusqueda;
    private JTextField txtBusqueda;
    private JButton btnBuscar,cargarContenido, administrarUsuarios,btnLogIn,btnEditar,btnVer,btnEliminar,btnPrestar,btnDevolver, btnReportes;
    private DefaultTableModel modelo; 
    private JPanel panelAcciones = new JPanel(new FlowLayout());
    private JPanel panelFunciones = new JPanel(new BorderLayout(15,15));
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
        btnBuscar.addActionListener(new BuscarMaterial());
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
                cargarContenido = new JButton("Carga Masiva");
                administrarUsuarios = new JButton("Administrar Usuarios");
                btnReportes = new JButton("Reportes");
                
                cargarContenido.addActionListener(new MostrarCargaMasiva());
                administrarUsuarios.addActionListener(new MostrarAdministrarUsuarios());
                btnReportes.addActionListener(new MostrarReportes());
                
                panelAcciones.add(cargarContenido);
                panelAcciones.add(administrarUsuarios);
                panelAcciones.add(btnReportes);
            }
            btnLogIn = new JButton("Log Out");
            btnLogIn.addActionListener(new CerrarSesion());
        } catch (Exception e) {
            btnLogIn = new JButton("Log In");
            
            btnLogIn.addActionListener(new MostrarLogin());
        }
        
        btnDevolver = new JButton("Devolver");
        btnPrestar = new JButton("Prestar");
        btnEliminar = new JButton("Eliminar");
        btnEditar = new JButton("Editar");
        btnVer = new JButton("Ver");     
        
        /*btnPrestar.setName("Prestar");
        btnEliminar.setName("Eliminar");
        btnEditar.setName("Editar");
        btnVer.setName("Ver");*/
        
        btnDevolver.addActionListener(new DevolverEjemplar());
        btnPrestar.addActionListener(new PrestarEjemplar());
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
        
        Sistema.panel.add(contenidoVerMaterial, "contenidoVerMaterial");
        Sistema.cardLayout.show(Sistema.panel, "contenidoVerMaterial");
        
        Sistema.ventana.add(Sistema.panel);
    }

    private void añadirHeadersLibro(){
        try {
            if(Sistema.usuarioLogeado.getNivel()==1){
                headers = new String[] {"ID", "Titulo", "Autor", "Tipo", "Estado","",""};
                dataLibros = new Object[50][7];
            }else{
                headers = new String[] {"ID", "Titulo", "Autor", "Tipo", "Estado","",""};
                dataLibros = new Object[50][7];
            }
        } catch (Exception e) {
            headers = new String[] {"ID", "Titulo", "Autor", "Tipo", "Estado",""};
            dataLibros = new Object[50][6];
        }
    }
    
    private void añadirHeadersRevista(){
        try {
            if(Sistema.usuarioLogeado.getNivel()==1){
                headers = new String[] {"ID", "Titulo", "Compañia", "Tipo", "Estado","",""};
                dataRevistas = new Object[50][7];
            }else{
                headers = new String[] {"ID", "Titulo", "Compañia", "Tipo", "Estado","",""};
                dataRevistas = new Object[50][7];
            }
        } catch (Exception e) {
            headers = new String[] {"ID", "Titulo", "Compañia", "Tipo", "Estado",""};
            dataRevistas = new Object[50][6];
        }
    }
    
    private void añadirHeadersTesis(){
        try {
            if(Sistema.usuarioLogeado.getNivel()==1){
                headers = new String[] {"ID", "Titulo", "Autor", "Tipo", "Estado","",""};
                dataTesis = new Object[50][7];
            }else{
                headers = new String[] {"ID", "Titulo", "Autor", "Tipo", "Estado","",""};
                dataTesis = new Object[50][7];
            }
        } catch (Exception e) {
            headers = new String[] {"ID", "Titulo", "Autor", "Tipo", "Estado",""};
            dataTesis = new Object[50][6];
        }
    }
    
    private void organizarMaterial(String materia) {
        try {
            switch(materia){
                case "Libros":
                    añadirHeadersLibro();
                    if (Sistema.contadorLibros>0) {
                        añadirLibros(Sistema.libros);
                    }
                    modelo.setDataVector(dataLibros, headers);
                    break;
                case "Revistas":
                    añadirHeadersRevista();
                    if(Sistema.contadorRevistas>0){
                        añadirRevistas(Sistema.revistas);
                    }
                    modelo.setDataVector(dataRevistas, headers);
                    break;
                case "Tesis":
                    añadirHeadersTesis();
                    if(Sistema.contadorTesis>0){
                        añadirTesis(Sistema.tesis);
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
    private void añadirLibros(Libro[] libros){
        try {
            for (int i = 0; i < libros.length; i++) {
                if (libros[i].getEstado()!=2) {
                    dataLibros[libros[i].getIdTabla()][0] = libros[i].getId();
                    dataLibros[libros[i].getIdTabla()][1] = libros[i].getTitulo();
                    dataLibros[libros[i].getIdTabla()][2] = libros[i].getAutor();
                    dataLibros[libros[i].getIdTabla()][3] = libros[i].getTema();
                    dataLibros[libros[i].getIdTabla()][4] = (libros[i].getEstado()==0)?"Disponible":"Ocupado";
                    try {
                        if(Sistema.usuarioLogeado.getNivel()==1){
                            dataLibros[libros[i].getIdTabla()][5] = btnVer;
                            try {
                                if (libros[i].getEstado()==1) {
                                    if (libros[i].getUsuario().equals(Sistema.usuarioLogeado.getUsuario())){
                                        dataLibros[libros[i].getIdTabla()][6] = btnDevolver;
                                    }
                                }else{
                                    dataLibros[libros[i].getIdTabla()][6] = btnPrestar;
                                }
                            } catch (Exception e) {
                                dataLibros[libros[i].getIdTabla()][6] = btnPrestar;
                            }
                        }else{
                            dataLibros[libros[i].getIdTabla()][5] = btnEditar;
                            dataLibros[libros[i].getIdTabla()][6] = btnEliminar;
                        }
                    } catch (Exception e) {
                        dataLibros[libros[i].getIdTabla()][5] = btnVer;
                    }
                }
            }
        } catch (Exception e) {}
    }

    
    private void añadirRevistas(Revista[] revistas){
        try {
            for (int i = 0; i < revistas.length; i++) {
                if (revistas[i].getEstado()!=2) {
                     dataRevistas[revistas[i].getIdTabla()][0] = revistas[i].getId();
                    dataRevistas[revistas[i].getIdTabla()][1] = revistas[i].getTitulo();
                    dataRevistas[revistas[i].getIdTabla()][2] = revistas[i].getCompañia();
                    dataRevistas[revistas[i].getIdTabla()][3] = revistas[i].getTema();
                    dataRevistas[revistas[i].getIdTabla()][4] = (revistas[i].getEstado()==0)?"Disponible":"Ocupado";
                    try {
                        if(Sistema.usuarioLogeado.getNivel()==1){
                            dataRevistas[revistas[i].getIdTabla()][5] = btnVer;
                            dataRevistas[revistas[i].getIdTabla()][6] = btnPrestar;
                            try {
                                if (revistas[i].getEstado()==1) {
                                    if (revistas[i].getUsuario().equals(Sistema.usuarioLogeado.getUsuario())) {
                                        dataRevistas[revistas[i].getIdTabla()][6] = btnDevolver;
                                    }
                                }else{
                                    dataRevistas[revistas[i].getIdTabla()][6] = btnPrestar;
                                }   
                            } catch (Exception e) {
                                dataRevistas[revistas[i].getIdTabla()][6] = btnPrestar;
                            }

                        }else{
                            dataRevistas[revistas[i].getIdTabla()][5] = btnEditar;
                            dataRevistas[revistas[i].getIdTabla()][6] = btnEliminar;
                        }
                    } catch (Exception e) {
                        dataRevistas[revistas[i].getIdTabla()][5] = btnVer;
                    }
                }
            }
        } catch (Exception e) {}
    }
    
    private void añadirTesis(Tesis[] tesis){
        try {
            for (int i = 0; i < tesis.length; i++) {
                if (tesis[i].getEstado()!=2) {
                    dataTesis[tesis[i].getIdTabla()][0] = tesis[i].getId();
                    dataTesis[tesis[i].getIdTabla()][1] = tesis[i].getTitulo();
                    dataTesis[tesis[i].getIdTabla()][2] = tesis[i].getAutor();
                    dataTesis[tesis[i].getIdTabla()][3] = tesis[i].getTema();
                    dataTesis[tesis[i].getIdTabla()][4] = (tesis[i].getEstado()==0)?"Disponible":"Ocupado";
                     try {
                        if(Sistema.usuarioLogeado.getNivel()==1){
                            dataTesis[tesis[i].getIdTabla()][5] = btnVer;
                            dataTesis[tesis[i].getIdTabla()][6] = btnPrestar;
                            try {
                                if (tesis[i].getUsuario().equals(Sistema.usuarioLogeado.getUsuario()) && tesis[i].getEstado()==1) {
                                    dataTesis[tesis[i].getIdTabla()][6] = btnDevolver;
                                }else{
                                    dataTesis[tesis[i].getIdTabla()][6] = btnPrestar;
                                }
                            } catch (Exception e) {
                                dataTesis[tesis[i].getIdTabla()][6] = btnPrestar;
                            }
                        }else{
                            dataTesis[tesis[i].getIdTabla()][5] = btnEditar;
                            dataTesis[tesis[i].getIdTabla()][6] = btnEliminar;
                        }
                    } catch (Exception e) {
                        dataTesis[tesis[i].getIdTabla()][5] = btnVer;
                    }
                }
            }
        } catch (Exception e) {}
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
                                    Sistema.revistas[i].setEstado(2);
                                    Sistema.revistas[i].setIdTabla(-1);
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
                                    Sistema.tesis[i].setEstado(2);
                                    Sistema.tesis[i].setIdTabla(-1);
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
    
    private class MostrarAdministrarUsuarios implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            //mostrarusuarios 
            Sistema.consultaUsuarios.MostrarAdministrarUsuarios();
        }
    }
    
    private class PrestarEjemplar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                switch(filtro.getSelectedItem().toString()){
                    case "Libros":
                        try {
                            for (int i = 0; i < Sistema.libros.length; i++) {
                                if (contenido.getSelectedRow() == Sistema.libros[i].getIdTabla()) {
                                    //prestar libro
                                    Libro libroPrestado = new Libro();
                                    libroPrestado = Sistema.libros[i];
                                    libroPrestado.setUsuario(Sistema.usuarioLogeado.getUsuario());
                                    libroPrestado.setEstado(1);
                                    Sistema.libros[i]=libroPrestado;
                                }
                            }
                        } catch (Exception e) {}
                        break;
                    case "Revistas":
                        try {
                            for (int i = 0; i < Sistema.revistas.length; i++) {
                                if (contenido.getSelectedRow() == Sistema.revistas[i].getIdTabla()) {
                                    //prestar revista
                                    Revista revistaPrestada = new Revista();
                                    revistaPrestada = Sistema.revistas[i];
                                    revistaPrestada.setUsuario(Sistema.usuarioLogeado.getUsuario());
                                    revistaPrestada.setEstado(1);
                                    Sistema.revistas[i]=revistaPrestada;
                                }
                            }
                        } catch (Exception e) {}
                        break;
                    case "Tesis":
                        try {
                            for (int i = 0; i < Sistema.tesis.length; i++) {
                                if (contenido.getSelectedRow() == Sistema.tesis[i].getIdTabla()) {
                                    //prestar Tesis
                                    Tesis tesisPrestada = new Tesis();
                                    tesisPrestada = Sistema.tesis[i];
                                    tesisPrestada.setUsuario(Sistema.usuarioLogeado.getUsuario());
                                    tesisPrestada.setEstado(1);
                                    Sistema.tesis[i]=tesisPrestada;
                                }
                            }
                        } catch (Exception e) {}
                        break;
                }
                Sistema.usuarioLogeado.setLibrosPrestados(Sistema.usuarioLogeado.getLibrosPrestados()+1);
                organizarMaterial(filtro.getSelectedItem().toString());
            } catch (Exception e) {}
        }
    }
     
    private class DevolverEjemplar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                switch(filtro.getSelectedItem().toString()){
                    case "Libros":
                        try {
                            for (int i = 0; i < Sistema.libros.length; i++) {
                                if (contenido.getSelectedRow() == Sistema.libros[i].getIdTabla()) {
                                    //devolver libro
                                    Libro libroDevuelto = new Libro();
                                    libroDevuelto = Sistema.libros[i];
                                    libroDevuelto.setEstado(0);
                                    libroDevuelto.setUsuario(null);
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
                                    revistaDevuelta.setUsuario(null);
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
                                    tesisDevuelta.setUsuario(null);
                                    Sistema.tesis[i]=tesisDevuelta;
                                }
                            }
                        } catch (Exception e) {}
                        break;
                }
            } catch (Exception e) {}
            Sistema.usuarioLogeado.setLibrosPrestados(Sistema.usuarioLogeado.getLibrosPrestados()-1);
            organizarMaterial(filtro.getSelectedItem().toString());
        }
    }
    
    private class BuscarMaterial implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            switch(filtro.getSelectedItem().toString()){
                case "Libros":
                    try {
                        if (txtBusqueda.getText().equals("")) {
                            añadirLibros(Sistema.libros);
                            break;
                        }else{
                            //añadir libros filtrados
                            Libro[] lb = new Libro[50];
                            Libro tmp = new Libro();

                            try {
                                int contador = 0;
                                for (int j = 0; j < Sistema.libros.length; j++) {
                                    if (txtBusqueda.getText().toLowerCase().equals(Sistema.libros[j].getTitulo().toLowerCase())) {
                                        lb[contador] = Sistema.libros[j];
                                        contador++;
                                    }
                                }
                            } catch (Exception e) {}
                            try {
                                for (int j = 0; j < Sistema.libros.length; j++) {
                                    if (lb[j].getIdTabla()<lb[j+1].getIdTabla()) {
                                        tmp = lb[j];
                                        lb[j] = lb[j+1];
                                        lb[j+1] = tmp;
                                    }
                                }
                            } catch (Exception e) {}
                            añadirHeadersLibro();
                            añadirLibros(lb);
                            modelo.setDataVector(dataLibros, headers);
                        }
                    } catch (Exception e) {}
                    break;
                case "Revistas":
                    try {
                        if (txtBusqueda.getText().equals("")) {
                            añadirRevistas(Sistema.revistas);
                            break;
                        }else{
                            Revista[] rv = new Revista[50];
                            Revista tmp = new Revista();
                            
                            try {
                                int contador = 0;
                                for (int i = 0; i < Sistema.revistas.length; i++) {
                                    if (txtBusqueda.getText().toLowerCase().equals(Sistema.revistas[i].getTitulo().toLowerCase())) {
                                        rv[contador] = Sistema.revistas[i];
                                        contador++;
                                    }
                                }
                            } catch (Exception e) {}
                            try {
                                for (int j = 0; j < Sistema.revistas.length; j++) {
                                    if (rv[j].getIdTabla()<rv[j+1].getIdTabla()) {
                                        tmp = rv[j];
                                        rv[j] = rv[j+1];
                                        rv[j+1] = tmp;
                                    }
                                }
                            } catch (Exception e) {}
                            añadirHeadersRevista();
                            añadirRevistas(rv);
                            modelo.setDataVector(dataRevistas, headers);
                        }
                    } catch (Exception e) {}
                    break;
                case "Tesis":
                    try {
                        if (txtBusqueda.getText().equals("")) {
                            añadirTesis(Sistema.tesis);
                            break;
                        }else{
                            Tesis[] ts = new Tesis[50];
                            Tesis tmp = new Tesis();
                            
                            try {
                                int contador = 0;
                                for (int i = 0; i < Sistema.tesis.length; i++) {
                                    if (txtBusqueda.getText().toLowerCase().equals(Sistema.tesis[i].getTitulo().toLowerCase())) {
                                        ts[contador] = Sistema.tesis[i];
                                    }
                                }
                            } catch (Exception e) {}
                            try {
                                for (int j = 0; j < Sistema.tesis.length; j++) {
                                    if (ts[j].getIdTabla()<ts[j+1].getIdTabla()) {
                                        tmp = ts[j];
                                        ts[j] = ts[j+1];
                                        ts[j+1] = tmp;
                                    }
                                }
                            } catch (Exception e) {}
                            añadirHeadersTesis();
                            añadirTesis(ts);
                            modelo.setDataVector(dataTesis, headers);
                        }
                    } catch (Exception e) {}
                    break;
            }
            contenido.setModel(modelo);
            contenido.setDefaultRenderer(Object.class, new TableRender());
            txtBusqueda.setText("");
            Sistema.ventana.repaint();
            /*modelo.setDataVector(dataRevistas, headers);        
            contenido.setModel(modelo);
            contenido.setDefaultRenderer(Object.class, new TableRender());*/
        }
    }
    
    private class MostrarReportes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Reportes reportes = new Reportes();
            reportes.mostrarReportes();
        }
    }
}
