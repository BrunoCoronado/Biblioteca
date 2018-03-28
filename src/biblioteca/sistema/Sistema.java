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
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author bruno
 */
public class Sistema{
    public static Usuario[] usuarios = new Usuario[11];
    public static Libro[] libros = new Libro[50];
    public static Revista[] revistas = new Revista[50]; 
    public static Tesis[] tesis = new Tesis[50];
    public static CardLayout cardLayout = new CardLayout();
    public static JPanel panel = new JPanel(cardLayout);
    public static JFrame ventana = new JFrame();
    public static Usuario usuarioLogeado;
    
    public static int contadorUsuarios = 1;
    public static int contadorLibros = 0;
    public static int contadorLibrosEliminados = 0;
    public static int contadorRevistas = 0;
    public static int contadorRevistasEliminadas = 0;
    public static int contadorTesis = 0;
    public static int contadorTesisEliminadas = 0;
    
    public static ConsultaMaterial consulta = new ConsultaMaterial();
    public static ConsultaUsuarios consultaUsuarios = new ConsultaUsuarios();
    
    public void inicializar() {
        inicializarUsuarios();
        inicializarVentana();
        consulta.verMateria();
        ventana.setVisible(true);
    }

    private void inicializarUsuarios() {
        Usuario usuario = new Usuario("admin","admin","admin","admin",0);
        usuarios[0] = usuario;
        
        /*Libro libro = new Libro("a", 1, 1, "a", "a", 1);
        libros[0] = libro;*/
    }

    private void inicializarVentana() {
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setTitle("Biblioteca");
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setSize(900, 600);
       // ventana.pack();
    }
    
}