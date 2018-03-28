/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.sistema;

import biblioteca.bean.Usuario;
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
public class ConsultaUsuarios {
    private String[] headers;
    private Object[][] dataUsuarios;
    private JScrollPane scroll;
    private JTable contenido = new JTable();
    private JPanel panelContenido, panelTitulo, panelHeader;
    private JButton btnEditar, btnEliminar, btnCerrarSesion, btnRegresar;
    private JLabel lblTitulo;
    private DefaultTableModel modelo;
    private boolean opcionTablaSeleccionado = false;
        
    public void MostrarAdministrarUsuarios(){
        Sistema.ventana.setTitle("Administrar Usuarios - Biblioteca[ADMINISTRADOR]");
        btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new EditarUsuarioSeleccionado());
        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new EliminarUsuario());
        btnRegresar = new JButton("Regresar");
        btnRegresar.addActionListener(new Regresar());
        btnCerrarSesion = new JButton("Cerrar Sesion");
        btnCerrarSesion.addActionListener(new CerrarSesion());
        headers = new String[] {"Username","Nombre","Apellido","Libros prestados","",""};
        
        panelHeader = new JPanel(new BorderLayout(15,15));
        panelContenido = new JPanel(new BorderLayout(15, 15));
        panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        lblTitulo = new JLabel("Administracion De Usuarios");
        panelTitulo.add(lblTitulo);
        
        panelHeader.add(btnRegresar, BorderLayout.WEST);
        panelHeader.add(panelTitulo, BorderLayout.CENTER);
        panelHeader.add(btnCerrarSesion, BorderLayout.EAST);
        panelContenido.add(panelHeader, BorderLayout.NORTH);
        
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        añadirUsuarios();
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
        
        scroll = new JScrollPane(contenido);
        panelContenido.add(scroll, BorderLayout.CENTER);
        
        Sistema.panel.add(panelContenido, "contenidoVerUsuarios");
        Sistema.cardLayout.show(Sistema.panel, "contenidoVerUsuarios");
    }

    private void añadirUsuarios() {
        dataUsuarios = new Object[10][6];
        try {
            for (int i = 1; i < Sistema.contadorUsuarios; i++) {
                if (Sistema.usuarios[i].getEstado()==0) {
                    int index = Sistema.usuarios[i].getIdTabla();
                    Usuario usuario = Sistema.usuarios[i];
                    dataUsuarios[index][0] = usuario.getUsuario();
                    dataUsuarios[index][1] = usuario.getNombre();
                    dataUsuarios[index][2] = usuario.getApellido();
                    dataUsuarios[index][3] = usuario.getLibrosPrestados();
                    dataUsuarios[index][4] = btnEditar;
                    dataUsuarios[index][5] = btnEliminar;
                }   
            }
            modelo.setDataVector(dataUsuarios, headers);
            contenido.setModel(modelo);
            contenido.setDefaultRenderer(Object.class, new TableRender());
        } catch (Exception e) {}
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
    
    private class EditarUsuarioSeleccionado implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            EditarUsuario editarUsuario = new EditarUsuario();
            try {
                for (int i = 0; i < Sistema.usuarios.length; i++) {
                    if (Sistema.usuarios[i].getIdTabla()==contenido.getSelectedRow()) {
                        editarUsuario.mostrarEditarUsuario(i);
                    }
                }
            } catch (Exception e) {}
        }
         
    }
    
    private class EliminarUsuario implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                eliminarUsuarioData(contenido.getSelectedRow());
            } catch (Exception e) {}
        }
    }
    //usuarios === 1 eliminado/0 activo
    private void eliminarUsuarioData(int index){
        boolean eliminado = true;
        try {
            for (int i = 1; i < Sistema.usuarios.length; i++) {
                if (eliminado) {
                    if (Sistema.usuarios[i].getIdTabla()==index) {
                        eliminado = false;
                        Sistema.usuarios[i].setEstado(1);
                        Sistema.usuarios[i].setIdTabla(-1);
                        try {
                            for (int j = i+1; j < Sistema.usuarios.length; j++) {
                                    Sistema.usuarios[j].setIdTabla(Sistema.usuarios[j].getIdTabla()-1);
                            }
                        } catch (Exception e) {}
                    }
                }
            }
        } catch (Exception e) {}
        añadirUsuarios();
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
