package com.romero278.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.romero278.kernel.connection.SQLPlace;

public class ListPlaces extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String flag, option, idPlace;
	String[] dataPlace;
	
	public ListPlaces(String fg, String op) {
		flag = fg;
		option = op;
		
		setTitle("Listado de " + flag.toLowerCase());
		setBounds(0, 0, 1200, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel title = new JLabel("Listado de " + flag.toLowerCase());
		JLabel lSelectPlace1 = new JLabel("Selecciona un " + option.toLowerCase());
		JLabel lSelectPlace2 = new JLabel("para ver su información");
		JLabel lName = new JLabel("Nombre: ");
		JTextArea taList = new JTextArea();
		JTextField tfName = new JTextField(16);
		JScrollPane scroll = new JScrollPane(taList);
		JComboBox<String> cbPlace = new JComboBox<String>();
		JButton btnSelect = new JButton("Seleccionar");
		JButton btnModify = new JButton("Modificar");
		JButton btnDelete = new JButton("Eliminar");
		JButton btnBack = new JButton("Atrás");
		
		tfName.setEditable(false);
		tfName.setBorder(null);
		
		lName.setVisible(false);
		tfName.setVisible(false);
		btnModify.setVisible(false);
		btnDelete.setVisible(false);
		
		taList.setEditable(false);
		taList.setLineWrap(true);
		
		scroll.setPreferredSize(new Dimension(300, 300));
		scroll.setBorder(null);
		
		btnModify.setPreferredSize(new Dimension(150, 30));
		btnDelete.setPreferredSize(new Dimension(150, 30));
		btnBack.setPreferredSize(new Dimension(100, 30));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 100, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, scroll, 200, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, scroll, 180, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lSelectPlace1, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lSelectPlace1, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lSelectPlace2, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lSelectPlace2, 270, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, cbPlace, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, cbPlace, 320, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnSelect, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnSelect, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lName, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, lName, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfName, 820, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, tfName, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, btnModify, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnModify, 450, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, btnDelete, 890, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnDelete, 450, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(scroll);
		container.add(lSelectPlace1);
		container.add(lSelectPlace2);
		container.add(cbPlace);
		container.add(btnSelect);
		container.add(lName);
		container.add(tfName);
		container.add(btnModify);
		container.add(btnDelete);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		taList.setFont(new Font("Arial", Font.BOLD, 20));
		taList.setForeground(new Color (116, 128, 148));
		taList.setBackground(new Color (238, 238, 238));
		
		lSelectPlace1.setFont(new Font("Arial", Font.BOLD, 12));
		lSelectPlace1.setForeground(new Color (116, 128, 148));
		
		lSelectPlace2.setFont(new Font("Arial", Font.BOLD, 12));
		lSelectPlace2.setForeground(new Color (116, 128, 148));
		
		cbPlace.setFont(new Font("Arial", Font.PLAIN, 20));
		cbPlace.setForeground(new Color (116, 128, 148));
		
		btnSelect.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSelect.setForeground(new Color (116, 128, 148));
		
		lName.setFont(new Font("Arial", Font.BOLD, 20));
		lName.setForeground(new Color (116, 128, 148));
		
		tfName.setFont(new Font("Arial", Font.PLAIN, 20));
		tfName.setForeground(new Color (116, 128, 148));
		
		btnModify.setFont(new Font("Arial", Font.BOLD, 20));
		btnModify.setForeground(Color.WHITE);
		btnModify.setBackground(new Color (25, 127, 210));
		
		btnDelete.setFont(new Font("Arial", Font.BOLD, 20));
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(new Color (235, 132, 20));
		
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color (196, 69, 59));
		
		/* --- Logic Part --- */
		
		SQLPlace place = new SQLPlace();
		ArrayList<String> alPlaces = new ArrayList<String>();
		String acumList = "", replacePlaces = "";
		String[] split;
		
		alPlaces = place.listPlaces();
		
		for(int i = 0; i < alPlaces.size(); i++) {
			replacePlaces = alPlaces.get(i).replace('.', '|');
			split = replacePlaces.split(Pattern.quote("|"));
			
			cbPlace.addItem(split[0]);
			acumList += alPlaces.get(i) + "\n";
		}
		
		taList.setText(acumList);
		
		if(alPlaces.isEmpty()) {
			btnSelect.setVisible(false);
		} else {
			btnSelect.setVisible(true);
		}
		
		btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String infoPlace = place.selectPlace(cbPlace.getSelectedItem().toString());
				dataPlace = infoPlace.split(Pattern.quote("|"));
				
				tfName.setText(dataPlace[1]);
				
				lName.setVisible(true);
				tfName.setVisible(true);
				btnModify.setVisible(true);
				btnDelete.setVisible(true);
			}
		});
		
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				ModifyPlace mod = new ModifyPlace(flag, option, dataPlace);
				mod.setVisible(true);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(null, "¿Deseas eliminar este " + option.toLowerCase() + "?", option + " - Eliminar", JOptionPane.YES_NO_OPTION);
				
				if(answer == JOptionPane.YES_OPTION) {
					String request = place.deletePlace(dataPlace[0]);
					
					switch (request) {
						case "success":
							JOptionPane.showMessageDialog(null, option +  " eliminado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
							goBack();
							break;
						case "error delete":
							JOptionPane.showMessageDialog(null, "Ha ocurrido un error, inténtalo de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
							break;
					}
				}
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});
	}
	
	void goBack() {
		setVisible(false);
		
		ConfigOptions conOps = new ConfigOptions(flag, option);
		conOps.setVisible(true);
	}
}
