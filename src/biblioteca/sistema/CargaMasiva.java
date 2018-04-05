/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.sistema;

import biblioteca.bean.Autor;
import biblioteca.bean.Libro;
import biblioteca.bean.Revista;
import biblioteca.bean.Tesis;
import biblioteca.bean.Usuario;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author bruno
 */
public class CargaMasiva {
    private JLabel lblTitulo;
    private JTextArea taContenido;
    private JButton cancelar;
    private JButton cargar;
    private JButton btnCerrarSesion;
    private boolean limiteLibros,limiteRevistas,limiteTesis, limiteUsuarios, nombreDeUsuarioNoDisponible;
    private String mensaje = "Limite Alcanzado de: ";
    
    public CargaMasiva() {
        lblTitulo = new JLabel("Carga Masiva");
        taContenido = new JTextArea();
        cancelar = new JButton("Cancelar");
        cargar = new JButton("Cargar");
        btnCerrarSesion = new JButton("Log Out");
    }
    
    public void mostrarCargaMasiva(){
        JPanel panelCargaMasiva = new JPanel(new BorderLayout());
        
        cargar.addActionListener(new CargarContenido());
        cancelar.addActionListener(new Cancelar());
        JPanel panelBotones = new JPanel();
        panelBotones.add(cargar);
        panelBotones.add(cancelar);
        
        JPanel panelHeader = new JPanel(new BorderLayout(15, 15));
        JPanel panelTitulo = new JPanel();
        panelTitulo.add(lblTitulo);
        panelHeader.add(panelTitulo, BorderLayout.CENTER);
        btnCerrarSesion.addActionListener(new CerrarSesion());
        panelHeader.add(btnCerrarSesion, BorderLayout.EAST);
        
        JScrollPane scrollPane = new JScrollPane(taContenido);
        panelCargaMasiva.add(panelHeader, BorderLayout.NORTH);
        panelCargaMasiva.add(scrollPane, BorderLayout.CENTER);
        panelCargaMasiva.add(panelBotones, BorderLayout.SOUTH);
        
        Sistema.panel.add(panelCargaMasiva, "panelCargaMasiva");
        Sistema.cardLayout.show(Sistema.panel, "panelCargaMasiva");
    }
    
    private class CargarContenido implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            separarContenido(taContenido.getText());
            //Sistema.cardLayout.show(Sistema.panel, "contenidoVerMaterial");
            taContenido.setText("");
            Sistema.consulta.verMateria();
        }

        private void separarContenido(String texto) {
            try {
                String[] contenido = texto.split("\n");
                for (int i = 0; i < contenido.length; i++) {
                    String[] ejemplar = contenido[i].split("\\|");
                    switch(ejemplar[0]){
                        case "0":
                            if (Sistema.contadorLibros<=49) {
                                Sistema.libros[Sistema.contadorLibros] = new Libro("",0,0,ejemplar[2], Integer.parseInt(ejemplar[4]),(Sistema.contadorLibros-Sistema.contadorLibrosEliminados), "LIB-"+(Sistema.contadorLibros+1), ejemplar[1], ejemplar[3], 0);
                                Sistema.contadorLibros++;
                                try {
                                    for (int j = 0; j < Sistema.autor.length; j++) {
                                        if (Sistema.autor[j].getNombre().equals(ejemplar[2])) {
                                            Sistema.autor[j].setConadorLibros(Sistema.autor[j].getConadorLibros()+1);
                                            break;
                                        }
                                    }
                                } catch (Exception e) {
                                    Sistema.autor[Sistema.contadorAutor] = new Autor(ejemplar[2],1);
                                    Sistema.contadorAutor++;
                                }
                            }else{
                                if (limiteLibros==false) {
                                    errorLimiteAlmacenamieto(0);
                                    limiteLibros = true;
                                }
                            }
                            break;
                        case "1":
                            if (Sistema.contadorRevistas<=49) {
                                Sistema.revistas[Sistema.contadorRevistas] = new Revista("",0,ejemplar[2], ejemplar[3],(Sistema.contadorRevistas-Sistema.contadorRevistasEliminadas), "REV-"+(Sistema.contadorRevistas+1), ejemplar[1], ejemplar[4], 0);
                                Sistema.contadorRevistas++;
                                 try {
                                    for (int j = 0; j < Sistema.autor.length; j++) {
                                        if (Sistema.autor[j].getNombre().equals(ejemplar[2])) {
                                            Sistema.autor[j].setConadorLibros(Sistema.autor[j].getConadorLibros()+1);
                                            break;
                                        }
                                    }
                                } catch (Exception e) {
                                    Sistema.autor[Sistema.contadorAutor] = new Autor(ejemplar[2],1);
                                    Sistema.contadorAutor++;
                                }
                            }else{
                                if (limiteRevistas==false) {
                                    errorLimiteAlmacenamieto(1);
                                    limiteRevistas = true;
                                }
                            }
                            break;
                        case "2":
                            if (Sistema.contadorTesis<=49) {
                                Sistema.tesis[Sistema.contadorTesis] = new Tesis("",ejemplar[2], ejemplar[4], ejemplar[5],(Sistema.contadorTesis-Sistema.contadorTesisEliminadas   ), "TES-"+(Sistema.contadorTesis+1), ejemplar[1], ejemplar[3], 0);
                                Sistema.contadorTesis++;  
                                 try {
                                    for (int j = 0; j < Sistema.autor.length; j++) {
                                        if (Sistema.autor[j].getNombre().equals(ejemplar[2])) {
                                            Sistema.autor[j].setConadorLibros(Sistema.autor[j].getConadorLibros()+1);
                                            break;
                                        }
                                    }
                                } catch (Exception e) {
                                    Sistema.autor[Sistema.contadorAutor] = new Autor(ejemplar[2],1);
                                    Sistema.contadorAutor++;
                                }
                            } else {
                                if (limiteTesis==false) {
                                    errorLimiteAlmacenamieto(2);
                                    limiteTesis = true;
                                }
                            }
                            break;
                        case "3":
                            if (Sistema.contadorUsuarios<=10) {
                                try {
                                    for (int j = 0; j < 10; j++) {
                                        if (Sistema.usuarios[j].getUsuario().equals(ejemplar[1])) {
                                            nombreDeUsuarioNoDisponible=true;
                                            break;
                                        }
                                    }
                                } catch (Exception e) {
                                    Sistema.usuarios[Sistema.contadorUsuarios] = new Usuario(Sistema.contadorUsuarios-1,0,0,ejemplar[1], ejemplar[4], ejemplar[2], ejemplar[3], 1);
                                    Sistema.contadorUsuarios++;
                                }
                                
                            } else {
                                if (limiteUsuarios==false) {
                                    errorLimiteAlmacenamieto(3);
                                    limiteUsuarios = true;
                                }
                            }
                            break;
                    }
                    ejemplar = null;
                }
                if (limiteLibros==true||limiteRevistas==true||limiteTesis==true||limiteUsuarios==true) {
                    errorLimiteAlmacenamieto(4);
                }
                if(nombreDeUsuarioNoDisponible==true){
                    errorNombreDeUsuario();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en el ingreso de datos. Datos almacenados hasta antes del error", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
        private void errorLimiteAlmacenamieto(int tipo){
            switch(tipo){
                case 0:
                    mensaje = mensaje.concat(" Libros, ");
                    break;
                case 1:
                    mensaje = mensaje.concat(" Revistas, ");
                    break;
                case 2:
                    mensaje = mensaje.concat(" Tesis, ");
                    break;
                case 3:
                    mensaje = mensaje.concat(" Usuarios, ");
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, mensaje+" Extras no se Agregan.", "Error", JOptionPane.ERROR_MESSAGE);
                    limiteLibros=false;
                    limiteRevistas=false;
                    limiteTesis=false;
                    limiteUsuarios=false;
                    break;
            }
        }
        
        private void errorNombreDeUsuario(){
            JOptionPane.showMessageDialog(null, "Nombres de usuario repetidos no disponibles. Solo los validos se registran.", "Error", JOptionPane.ERROR_MESSAGE);
            nombreDeUsuarioNoDisponible=false;
        }
    }
    
    private class Cancelar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Sistema.consulta.verMateria();
        }  
    }
    
    private class CerrarSesion implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Sistema.usuarioLogeado = null;
            Sistema.consulta.verMateria();
        }
    }
}
