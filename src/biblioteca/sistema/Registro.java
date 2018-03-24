/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.sistema;

import biblioteca.bean.Usuario;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author bruno
 */
public class Registro {
    private JTextField nombre;
    private JTextField apellido;
    private JTextField usuario;
    private JPasswordField contraseña;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblUsuario;
    private JLabel lblContraseña;
    private JButton registrarse = new JButton("Registrarse");
    private JButton cancelar = new JButton("Cancelar");

    public Registro() {
        nombre = new JTextField(20);
        apellido = new JTextField(20);
        usuario =  new JTextField(20);
        contraseña = new JPasswordField(20);
        lblNombre = new JLabel("Nombre");
        lblApellido =  new JLabel("Apellido");
        lblUsuario = new JLabel("Usuario");
        lblContraseña = new JLabel("Contraseña");
        
        cancelar.addActionListener(new Cancelar());
        registrarse.addActionListener(new Registrarse());
    }
    
    
    
    public void mostrarRegistro(){
        JPanel contenidoRegistro = new JPanel(new BorderLayout());
        
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        
        Sistema.ventana.setTitle("Registro - Biblioteca");
        //Sistema.ventana.setSize(600, 600);
        
        JPanel panelTitulo = new JPanel();
        JLabel labelTitulo = new JLabel("REGISTRO");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        panelTitulo.add(labelTitulo);
        contenidoRegistro.add(panelTitulo, BorderLayout.NORTH);
        //Sistema.cardLayout.show(panelTitulo, "tituloLogin");
        
        JPanel formularioRegistro = new JPanel();
        formularioRegistro.setLayout(new GridBagLayout());
        /*nick.setSize(50, 50);
        contraseña.setSize(50, 50);*/
        /*formularioLogin.add(nick);
        formularioLogin.add(contraseña);
        contenidoLogin.add(formularioLogin, BorderLayout.CENTER);
        //Sistema.cardLayout.show(formularioLogin, "formularioLogin");*/
        gridBagConstraints.gridx=6;
        gridBagConstraints.gridy=0;
        formularioRegistro.add(nombre, gridBagConstraints);
        gridBagConstraints.gridx=3;
        gridBagConstraints.gridy=0;
        formularioRegistro.add(lblNombre, gridBagConstraints);
        gridBagConstraints.gridx=6;
        gridBagConstraints.gridy=1;
        formularioRegistro.add(apellido, gridBagConstraints);
        gridBagConstraints.gridx=3;
        gridBagConstraints.gridy=1;
        formularioRegistro.add(lblApellido, gridBagConstraints);
        gridBagConstraints.gridx=6;
        gridBagConstraints.gridy=2;
        formularioRegistro.add(usuario, gridBagConstraints);
        gridBagConstraints.gridx=3;
        gridBagConstraints.gridy=2;
        formularioRegistro.add(lblUsuario, gridBagConstraints);
        gridBagConstraints.gridx=6;
        gridBagConstraints.gridy=3;
        formularioRegistro.add(contraseña, gridBagConstraints);
        gridBagConstraints.gridx=3;
        gridBagConstraints.gridy=3;
        formularioRegistro.add(lblContraseña, gridBagConstraints);
        contenidoRegistro.add(formularioRegistro, BorderLayout.CENTER);
        
        JPanel botonesFormulario = new JPanel();
        botonesFormulario.add(registrarse);
        botonesFormulario.add(cancelar);
        contenidoRegistro.add(botonesFormulario, BorderLayout.SOUTH);
        
        Sistema.panel.add(contenidoRegistro, "contenidoRegistro");
        Sistema.cardLayout.show(Sistema.panel, "contenidoRegistro");
        /*Sistema.ventana.removeAll();
        Sistema.ventana.add(contenidoRegistro, "contenidoRegistro");
        Sistema.ventana.revalidate();
        Sistema.ventana.repaint();*/
    }
    
    class Registrarse implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                for (int i = 0; i < 10; i++) {
                    if(Sistema.usuarios[i].getNombre().equals(usuario.getText())){
                        JOptionPane.showMessageDialog(null, "Usuario no registrado! Nombre de usuario no disponible.");
                        reiniciarTexto();
                        break;
                    }
                }
            } catch (Exception e) {
                if(validarCampos()){
                    agregarUsuarioRegistrado();
                }else{
                    JOptionPane.showMessageDialog(null, "Usuario no registrado! Llenar todos los campos");
                }
                reiniciarTexto();
            }
                
                
            
           /* for (int i = 0; i < Sistema.usuarios.length; i++) {
                try {
                System.out.println(Sistema.usuarios[i].getUsuario()+ " "+Sistema.usuarios[i].getContraseña());
                } catch (Exception e) {
                    break;
                }
            }*/
        }
        
        public void agregarUsuarioRegistrado(){
                Sistema.usuarios[Sistema.contadorUsuarios] = new Usuario(usuario.getText(), String.valueOf(contraseña.getPassword()), nombre.getText(), apellido.getText(), 1);
                Sistema.contadorUsuarios++;// = Sistema.contadorUsuarios + 1;
                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
                Sistema.cardLayout.show(Sistema.panel, "contenidoLogin");
        }

        private void reiniciarTexto() {
            nombre.setText("");
            apellido.setText("");
            usuario.setText("");
            contraseña.setText("");
        }

        private boolean validarCampos() {
            if(nombre.getText().equals("")||apellido.getText().equals("")||usuario.getText().equals("")||String.valueOf(contraseña.getPassword()).equals("")){
                return false;
            }
            return true;
        }
    }
    
    class Cancelar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Sistema.cardLayout.show(Sistema.panel, "contenidoLogin");
        }
    }
}
