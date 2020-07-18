package paquete.buscador;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import paquete.buscador.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.Checkbox;
import java.awt.CheckboxMenuItem;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import java.awt.List;
import java.awt.Panel;
import java.awt.ScrollPane;

public class Runner extends JFrame {

	private JPanel contentPane;
	private JTextField cadenaBusqueda;
	private static JList list = new JList();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					Runner frame = new Runner();
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Runner() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1205, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		cadenaBusqueda = new JTextField();
		cadenaBusqueda.setColumns(10);
		JCheckBox chckbxBuscarTodo = new JCheckBox("Buscar en 'todo'");
		JCheckBox chckbxImagen = new JCheckBox("Imagenes");
		JCheckBox chckbxVideo = new JCheckBox("Videos");
		JCheckBox chckbxTxt = new JCheckBox("Txt");
		JCheckBox chckbxArchivosOffice = new JCheckBox("Archivos Office");
		JButton btnBuscar = new JButton("Buscar");		
		chckbxBuscarTodo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(chckbxBuscarTodo.isSelected()){
					chckbxImagen.setVisible(false);
					chckbxVideo.setVisible(false);
					chckbxTxt.setVisible(false);
					chckbxArchivosOffice.setVisible(false);
				}else{
					chckbxImagen.setVisible(true);
					chckbxVideo.setVisible(true);
					chckbxTxt.setVisible(true);
					chckbxArchivosOffice.setVisible(true);
				}
			}
		});
		chckbxImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxImagen.isSelected()){
					chckbxBuscarTodo.setVisible(false);
				}else if(!chckbxVideo.isSelected() && !chckbxTxt.isSelected() && !chckbxArchivosOffice.isSelected()) {
					chckbxBuscarTodo.setVisible(true);
				}
			}
		});
		chckbxVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxVideo.isSelected()){
					chckbxBuscarTodo.setVisible(false);
				}else if(!chckbxImagen.isSelected() && !chckbxTxt.isSelected() && !chckbxArchivosOffice.isSelected()) {
					chckbxBuscarTodo.setVisible(true);
				}
			}
		});
		chckbxTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxTxt.isSelected()){
					chckbxBuscarTodo.setVisible(false);
				}else if(!chckbxImagen.isSelected() && !chckbxVideo.isSelected() && !chckbxArchivosOffice.isSelected()) {
					chckbxBuscarTodo.setVisible(true);
				}
			}
		});
		chckbxArchivosOffice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxArchivosOffice.isSelected()){
					chckbxBuscarTodo.setVisible(false);
				}else if(!chckbxImagen.isSelected() && !chckbxVideo.isSelected() && !chckbxTxt.isSelected()) {
					chckbxBuscarTodo.setVisible(true);
				}
			}
		});
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				DefaultListModel DLM = new DefaultListModel<>();
				DLM.removeAllElements();
				if(cadenaBusqueda.getText()!="" && !cadenaBusqueda.getText().isEmpty()){
					if(chckbxImagen.isSelected() || chckbxVideo.isSelected() || chckbxTxt.isSelected() || chckbxArchivosOffice.isSelected() || chckbxBuscarTodo.isSelected()){
						String busqueda=cadenaBusqueda.getText();
						ArrayList<String> archivos = Buscador.buscar(busqueda,chckbxImagen.isSelected(),chckbxVideo.isSelected(),chckbxTxt.isSelected(),chckbxArchivosOffice.isSelected(),chckbxBuscarTodo.isSelected());
						if(!archivos.isEmpty()){
						for(int i=0;i<archivos.size();i++){
							System.out.println("------------------------------------");
							System.out.println(archivos.get(i));
							DLM.addElement(archivos.get(i));
						}
							list.setModel(DLM);
						}else{
							JOptionPane.showMessageDialog(null, "No se encontraron resultados para su busqueda");
						}
					}else{
						JOptionPane.showMessageDialog(null, "Debe seleccionar algún checkbox");
					}
				}else{
					System.out.println("Cadena vacía");
					JOptionPane.showMessageDialog(null, "Campo de busqueda vacío!!!");
				}
			}
		});
		
		JButton btnAbrir = new JButton("Abrir Archivo");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{try {
					String valor = (String) list.getSelectedValue();
					Archivo.abrirArchivo(valor);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	
		
		
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(173)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(chckbxBuscarTodo, Alignment.LEADING)
						.addComponent(cadenaBusqueda, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
						.addComponent(chckbxImagen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(chckbxVideo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(chckbxTxt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(chckbxArchivosOffice, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(list, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 886, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(136, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(462)
					.addComponent(btnBuscar, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
					.addGap(437))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(462, Short.MAX_VALUE)
					.addComponent(btnAbrir, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
					.addGap(437))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(53)
					.addComponent(cadenaBusqueda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(chckbxImagen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxVideo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxTxt)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxArchivosOffice)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxBuscarTodo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBuscar)
					.addGap(18)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAbrir)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
