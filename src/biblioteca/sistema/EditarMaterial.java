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
import java.awt.FlowLayout;
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
public class EditarMaterial {
    private JFrame ventana;
    private JPanel panelCampos;
    private JPanel panelEditarMaterial;
    private JPanel panelBotones;
    private JPanel panelTitulo;
    private JTextField txtCampo1;
    private JTextField txtCampo2;
    private JTextField txtCampo3;
    private JTextField txtCampo4;
    private JTextField txtCampo5;
    private JLabel lblCampo1;
    private JLabel lblCampo2;
    private JLabel lblCampo3;
    private JLabel lblCampo4;
    private JLabel lblCampo5;
    private JLabel lblCampo6;
    private JButton btnAceptar;
    private JButton btnCancelar;
    
    private String tipo;
    private int index;
    
    public void mostrarEditarMaterial(int index, String tipo){
     //buscar el ejemplar dependiendo del tipo, y agregar los componentes a la vanetama.
     
     //agregar campos a la ventana
     //libro 4 campos a editar + id
     //revista 4 campos a editar + id
     //tesis 5 campos a editar + id
     
        ventana = new JFrame("Editar Material - Biblioteca");
        panelCampos = new JPanel(new GridLayout(6,1,20,20));
        panelEditarMaterial = new JPanel(new BorderLayout(15, 15));
        panelBotones = new JPanel();
        panelTitulo = new JPanel();
        
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
        btnAceptar.addActionListener(new CambiarParametros());
        btnCancelar.addActionListener(new Cancelar());
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);
        
        this.tipo = tipo;
        this.index = index;
        //buscar el ejemplar e inicializar componentes segun el tipo de material
        try {
            switch(tipo){
                case "Libros":
                    Libro libroAEditar = Sistema.libros[index];
                    lblCampo6 = new JLabel("Editar Libro: "+libroAEditar.getId());
                    panelTitulo.add(lblCampo6);
                    lblCampo1 =  new JLabel("Titulo: ");
                    panelCampos.add(lblCampo1);
                    txtCampo1 = new JTextField(libroAEditar.getTitulo());
                    panelCampos.add(txtCampo1);
                    lblCampo2 = new JLabel("Autor: ");
                    panelCampos.add(lblCampo2);
                    txtCampo2 = new JTextField(libroAEditar.getAutor());
                    panelCampos.add(txtCampo2);
                    lblCampo3 = new JLabel("Tema: ");
                    panelCampos.add(lblCampo3);
                    txtCampo3 = new JTextField(libroAEditar.getTema());
                    panelCampos.add(txtCampo3);
                    lblCampo4 = new JLabel("No. de Paginas: ");
                    panelCampos.add(lblCampo4);
                    txtCampo4 = new JTextField(Integer.toString(libroAEditar.getPaginas()));
                    panelCampos.add(txtCampo4);
                    break;
                case "Revistas":
                    Revista revistaAEditar = Sistema.revistas[index];
                    lblCampo6 = new JLabel("Editar Revista: "+revistaAEditar.getId());
                    panelTitulo.add(lblCampo6);
                    lblCampo1 =  new JLabel("Titulo: ");
                    panelCampos.add(lblCampo1);
                    txtCampo1 = new JTextField(revistaAEditar.getTitulo());
                    panelCampos.add(txtCampo1);
                    lblCampo2 = new JLabel("Compañia: ");
                    panelCampos.add(lblCampo2);
                    txtCampo2 = new JTextField(revistaAEditar.getCompañia());
                    panelCampos.add(txtCampo2);
                    lblCampo3 = new JLabel("Fecha: ");
                    panelCampos.add(lblCampo3);
                    txtCampo3 = new JTextField(revistaAEditar.getFecha());
                    panelCampos.add(txtCampo3);
                    lblCampo4 = new JLabel("Tema : ");
                    panelCampos.add(lblCampo4);
                    txtCampo4 = new JTextField(revistaAEditar.getTema());
                    panelCampos.add(txtCampo4);
                    break;
                case "Tesis":
                    Tesis tesisAEditar = Sistema.tesis[index];
                    lblCampo6 = new JLabel("Editar Tesis: "+tesisAEditar.getId());
                    panelTitulo.add(lblCampo6);
                    lblCampo1 =  new JLabel("Titulo: ");
                    panelCampos.add(lblCampo1);
                    txtCampo1 = new JTextField(tesisAEditar.getTitulo());
                    panelCampos.add(txtCampo1);
                    lblCampo2 = new JLabel("Autor: ");
                    panelCampos.add(lblCampo2);
                    txtCampo2 = new JTextField(tesisAEditar.getAutor());
                    panelCampos.add(txtCampo2);
                    lblCampo3 = new JLabel("Grado: ");
                    panelCampos.add(lblCampo3);
                    txtCampo3 = new JTextField(tesisAEditar.getGrado());
                    panelCampos.add(txtCampo3);
                    lblCampo4 = new JLabel("Tema: ");
                    panelCampos.add(lblCampo4);
                    txtCampo4 = new JTextField(tesisAEditar.getTema());
                    panelCampos.add(txtCampo4);
                    lblCampo5 = new JLabel("Año: ");
                    panelCampos.add(lblCampo5);
                    txtCampo5 = new JTextField(tesisAEditar.getAño());
                    panelCampos.add(txtCampo5);
                    break;
            }
            panelEditarMaterial.add(panelTitulo, BorderLayout.NORTH);
            panelEditarMaterial.add(panelBotones, BorderLayout.SOUTH);
            panelEditarMaterial.add(panelCampos, BorderLayout.CENTER);
            ventana.add(panelEditarMaterial);
            ventana.pack();
            ventana.setResizable(false);
            ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventana.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private class CambiarParametros implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            switch(tipo){
                case "Libros":
                    Libro libroEditado = new Libro();
                    libroEditado = Sistema.libros[index];
                    libroEditado.setTitulo(txtCampo1.getText());
                    libroEditado.setAutor(txtCampo2.getText());
                    libroEditado.setTema(txtCampo3.getText());
                    libroEditado.setPaginas(Integer.parseInt(txtCampo4.getText()));
                    Sistema.libros[index] = libroEditado;
                    break;
                case "Revistas":
                    Revista revistaEditada = new Revista();
                    revistaEditada = Sistema.revistas[index];
                    revistaEditada.setTitulo(txtCampo1.getText());
                    revistaEditada.setCompañia(txtCampo2.getText());
                    revistaEditada.setFecha(txtCampo3.getText());
                    revistaEditada.setTema(txtCampo4.getText());
                    Sistema.revistas[index]=revistaEditada;
                    break;
                case "Tesis":
                    Tesis tesisEditada = new Tesis();
                    tesisEditada = Sistema.tesis[index];
                    tesisEditada.setTitulo(txtCampo1.getText());
                    tesisEditada.setAutor(txtCampo2.getText());
                    tesisEditada.setGrado(txtCampo3.getText());
                    tesisEditada.setTema(txtCampo4.getText());
                    tesisEditada.setAño(txtCampo5.getText());
                    Sistema.tesis[index]=tesisEditada;
                    break;
            }
            ventana.setVisible(false);
            Sistema.consulta.verMateria();
        }
    }
    
    private class Cancelar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            ventana.setVisible(false);
            Sistema.consulta.verMateria();
        }
    }
}
