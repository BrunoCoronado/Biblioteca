/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.sistema;

import biblioteca.bean.Libro;
import biblioteca.bean.Usuario;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Login{
    private JTextField nick;
    private JPasswordField contraseña;
    private final JLabel lblUsuario = new JLabel("Usuario");
    private final JLabel lblContraseña = new JLabel("Contraseña");
    private final JLabel labelTitulo = new JLabel("INICIAR SESION");
    private JButton iniciarSesion;
    private JButton registrarse;
    private JButton btnRegresar;
    private Usuario usuario = new Usuario();
    private Registro registro = new Registro();
    //private ConsultaMaterial materia = new ConsultaMaterial();
    
    public void mostrarLogin(){
        nick = new JTextField(20);
        contraseña = new JPasswordField(20);
        iniciarSesion = new JButton("Iniciar Sesion");
        registrarse = new JButton("Registrarse");  
        btnRegresar = new JButton("Regresar");
        
        iniciarSesion.addActionListener(new InicioSesion());
        registrarse.addActionListener(new Registrar());
        btnRegresar.addActionListener(new Regresar());
        
        Sistema.ventana.setTitle("Inicio Sesion - Biblioteca");
        //Sistema.ventana.setSize(300, 300);
       
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        
        JPanel contenidoLogin = new JPanel(new BorderLayout(20,20));
        
        //JPanel panelTitulo = new JPanel();
        
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        JPanel panelHeader = new JPanel(new BorderLayout(20,20));
        JPanel panelTitulo =  new JPanel();
        //gridBagConstraints.gridx=5;
        //gridBagConstraints.gridy=5;
        panelTitulo.add(labelTitulo);
        panelHeader.add(panelTitulo,BorderLayout.CENTER);
        panelHeader.add(btnRegresar, BorderLayout.WEST);
        contenidoLogin.add(panelHeader, BorderLayout.NORTH);
        //contenidoLogin.add(panelTitulo, BorderLayout.NORTH);
        //Sistema.cardLayout.show(panelTitulo, "tituloLogin");
        
        //JPanel formularioLogin = new JPanel();
        //formularioLogin.setLayout(new GridBagLayout());
        
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        
        gridBagConstraints.gridx=3;
        gridBagConstraints.gridy=3;
        panelFormulario.add(lblUsuario, gridBagConstraints);
        
        
        gridBagConstraints.gridx=7;
        gridBagConstraints.gridy=3;
        panelFormulario.add(nick,gridBagConstraints);
        
        gridBagConstraints.gridx=3;
        gridBagConstraints.gridy=6;
        panelFormulario.add(lblContraseña, gridBagConstraints);
        /*nick.setSize(50, 50);
        contraseña.setSize(50, 50);*/
        
        gridBagConstraints.gridx=7;
        gridBagConstraints.gridy=6;
        panelFormulario.add(contraseña, gridBagConstraints);
        
        
        contenidoLogin.add(panelFormulario, BorderLayout.CENTER);
        //contenidoLogin.add(formularioLogin, BorderLayout.CENTER);
        //Sistema.cardLayout.show(formularioLogin, "formularioLogin");
        
        JPanel botonesFormulario = new JPanel();
        botonesFormulario.add(iniciarSesion);
        botonesFormulario.add(registrarse);
        contenidoLogin.add(botonesFormulario, BorderLayout.SOUTH);
        //Sistema.cardLayout.show(botonesFormulario, "botonesFormularioLogin");

        /*container.add(panelTitulo, BorderLayout.NORTH);
        container.add(formularioLogin, BorderLayout.CENTER);
        container.add(botonesFormulario, BorderLayout.SOUTH);*/
        Sistema.panel.add(contenidoLogin, "contenidoLogin");
        //Sistema.ventana.add(Sistema.panel);
        Sistema.cardLayout.show(Sistema.panel, "contenidoLogin");
    }
    
    private class InicioSesion implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(usuario.validarSesion(nick.getText(), String.valueOf(contraseña.getPassword()))){
                JOptionPane.showMessageDialog(null, "Sesion iniciado");
                Sistema.consulta.verMateria();
            }else{
                JOptionPane.showMessageDialog(null, "fallo al iniciar sesion");
                nick.setText("");
                contraseña.setText("");
            }
        }
    }
    
    private class Registrar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            registro.mostrarRegistro();
        }
    }
    
    private class Regresar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Sistema.consulta.verMateria();
        }
    }
}


