package com.romero278.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.romero278.kernel.connection.SQLPlace;

public class ModifyPlace extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String flag, option, idOwner;
	String[] dataPlace;
	
	public ModifyPlace(String fg, String op, String[] data) {
		flag = fg;
		option = op;
		dataPlace = data;
		
		setTitle(option + " - Modificar");
		setBounds(0, 0, 1200, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel title = new JLabel("Modificar " + option.toLowerCase());
		JLabel subtitle = new JLabel("Ingresa los datos");
		JLabel lName = new JLabel("Nombre: ");
		JTextField tfName = new JTextField(16);
		JButton btnModify = new JButton("Modificar");
		JButton btnBack = new JButton("Atrás");
		
		btnModify.setPreferredSize(new Dimension(200, 36));
		btnBack.setPreferredSize(new Dimension(100, 30));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 100, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subtitle, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, subtitle, 180, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lName, 450, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lName, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfName, 550, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfName, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnModify, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnModify, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(subtitle);
		container.add(lName);
		container.add(tfName);
		container.add(btnModify);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		subtitle.setFont(new Font("Arial", Font.BOLD, 28));
		subtitle.setForeground(new Color (116, 128, 148));
		
		lName.setFont(new Font("Arial", Font.BOLD, 20));
		lName.setForeground(new Color (116, 128, 148));
		
		tfName.setFont(new Font("Arial", Font.PLAIN, 18));
		tfName.setForeground(new Color (116, 128, 148));
		
		btnModify.setFont(new Font("Arial", Font.BOLD, 20));
		btnModify.setForeground(new Color (116, 128, 148));
		
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color (196, 69, 59));
		
		/* --- Logic part --- */
		
		SQLPlace place = new SQLPlace();
		
		tfName.setText(dataPlace[1]);
		
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tfName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debes diligenciar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					String request = place.updatePlace(dataPlace[0], tfName.getText());
					
					switch(request) {
						case "success":
							JOptionPane.showMessageDialog(null, option +  " modificado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);							
							goBack();
							break;
						case "name":
							JOptionPane.showMessageDialog(null, "El nombre del lugar ya ha sido registrado previamente", "Error", JOptionPane.ERROR_MESSAGE);
							break;
						case "error update":
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
		
		ListPlaces list = new ListPlaces(flag, option);
		list.setVisible(true);
	}
}
