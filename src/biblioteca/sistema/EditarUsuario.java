/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.sistema;

import biblioteca.bean.Usuario;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author bruno
 */
public class EditarUsuario {
    private JFrame ventana;
    private JPanel panelCampos,panelEditarUsuario,panelBotones,panelTitulo;
    private JTextField txtCampo1,txtCampo2,txtCampo3,txtCampo4;
    private JLabel lblTitulo,lblCampo1, lblCampo2,lblCampo3,lblCampo4;
    private JButton btnAceptar,btnCancelar;
   
    private int index;
    
    public void mostrarEditarUsuario(int index){
    
        ventana = new JFrame("Editar Usuario - Biblioteca");
        panelCampos = new JPanel(new GridLayout(6,1,20,20));
        panelEditarUsuario = new JPanel(new BorderLayout(15, 15));
        panelBotones = new JPanel();
        panelTitulo = new JPanel();
        
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
       btnAceptar.addActionListener(new CambiarParametros());
        btnCancelar.addActionListener(new Cancelar());
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);
        
        this.index = index;
        
        Usuario usuarioAEditar = Sistema.usuarios[index];
        lblTitulo = new JLabel("Editar Usuario");
        panelTitulo.add(lblTitulo);
        lblCampo1 =  new JLabel("Username: ");
        panelCampos.add(lblCampo1);
        txtCampo1 = new JTextField(usuarioAEditar.getUsuario());
        panelCampos.add(txtCampo1);
        lblCampo2 = new JLabel("Contraseña: ");
        panelCampos.add(lblCampo2);
        txtCampo2 = new JTextField(usuarioAEditar.getContraseña());
        panelCampos.add(txtCampo2);
        lblCampo3 = new JLabel("Nombre: ");
        panelCampos.add(lblCampo3);
        txtCampo3 = new JTextField(usuarioAEditar.getNombre());
        panelCampos.add(txtCampo3);
        lblCampo4 = new JLabel("Apellido: ");
        panelCampos.add(lblCampo4);
        txtCampo4 = new JTextField(usuarioAEditar.getApellido());
        panelCampos.add(txtCampo4);
        
        panelEditarUsuario.add(panelTitulo, BorderLayout.NORTH);
        panelEditarUsuario.add(panelBotones, BorderLayout.SOUTH);
        panelEditarUsuario.add(panelCampos, BorderLayout.CENTER);
        ventana.add(panelEditarUsuario);
        ventana.pack();
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setVisible(true);
    }
    
    private class CambiarParametros implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Usuario usuarioEditado = new Usuario();
            usuarioEditado = Sistema.usuarios[index];
            usuarioEditado.setUsuario(txtCampo1.getText());
            usuarioEditado.setContraseña(txtCampo2.getText());
            usuarioEditado.setNombre(txtCampo3.getText());
            usuarioEditado.setApellido(txtCampo4.getText());
            Sistema.usuarios[index] = usuarioEditado;
            
            ventana.setVisible(false);
            Sistema.consultaUsuarios.MostrarAdministrarUsuarios();
        }
    }
    
    private class Cancelar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            ventana.setVisible(false);
            Sistema.consultaUsuarios.MostrarAdministrarUsuarios();
        }
    }
}
