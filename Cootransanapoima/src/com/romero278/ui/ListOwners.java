package com.romero278.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.romero278.kernel.connection.SQLOwner;

public class ListOwners extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String flag, option, completeNameOwner, documentOwner, birthdayOwner, addressOwner, cityOwner, phoneOwner, emailOwner;
	
	public ListOwners(String fg, String op) {
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
		JLabel lSelectOwner1 = new JLabel("Selecciona un propietario");
		JLabel lSelectOwner2 = new JLabel("para ver su información");
		JLabel lName = new JLabel("Nombre:");
		JLabel lDocument = new JLabel("Documento:");
		JLabel lBirthdate = new JLabel("Fecha de nacimiento:");
		JLabel lAddress = new JLabel("Dirección:");
		JLabel lCity = new JLabel("Ciudad:");
		JLabel lPhone = new JLabel("Teléfono:");
		JLabel lEmail = new JLabel("Correo:");
		JTextField tfName = new JTextField(23);
		JTextField tfDocument = new JTextField(15);
		JTextField tfBirthdate = new JTextField(10);
		JTextField tfAddress = new JTextField(18);
		JTextField tfCity = new JTextField(15);
		JTextField tfPhone = new JTextField(15);
		JTextField tfEmail = new JTextField(20);
		JTextArea taList = new JTextArea();
		JComboBox<String> cbOwner = new JComboBox<String>();
		JButton btnSelect = new JButton("Seleccionar");
		JButton btnModify = new JButton("Modificar");
		JButton btnDelete = new JButton("Eliminar");
		JButton btnBack = new JButton("Atrás");
		
		tfName.setEditable(false);
		tfDocument.setEditable(false);
		tfBirthdate.setEditable(false);
		tfAddress.setEditable(false);
		tfCity.setEditable(false);
		tfPhone.setEditable(false);
		tfEmail.setEditable(false);
		
		taList.setPreferredSize(new Dimension(350, 280));
		taList.setEditable(false);
		
		lName.setVisible(false);
		lDocument.setVisible(false);
		lBirthdate.setVisible(false);
		lAddress.setVisible(false);
		lCity.setVisible(false);
		lPhone.setVisible(false);
		lEmail.setVisible(false);
		tfName.setVisible(false);
		tfDocument.setVisible(false);
		tfBirthdate.setVisible(false);
		tfAddress.setVisible(false);
		tfCity.setVisible(false);
		tfPhone.setVisible(false);
		tfEmail.setVisible(false);
		btnModify.setVisible(false);
		btnDelete.setVisible(false);
		
		tfName.setBorder(null);
		tfDocument.setBorder(null);
		tfBirthdate.setBorder(null);
		tfAddress.setBorder(null);
		tfCity.setBorder(null);
		tfPhone.setBorder(null);
		tfEmail.setBorder(null);
		
		btnModify.setPreferredSize(new Dimension(150, 30));
		btnDelete.setPreferredSize(new Dimension(150, 30));
		btnBack.setPreferredSize(new Dimension(100, 30));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 100, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, taList, 150, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, taList, 180, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lSelectOwner1, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lSelectOwner1, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lSelectOwner2, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lSelectOwner2, 270, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, cbOwner, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, cbOwner, 320, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnSelect, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnSelect, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lName, 700, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lName, 180, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfName, 790, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfName, 180, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lDocument, 700, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lDocument, 220, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfDocument, 820, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfDocument, 220, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lBirthdate, 700, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lBirthdate, 260, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfBirthdate, 910, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfBirthdate, 260, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lAddress, 700, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lAddress, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfAddress, 800, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfAddress, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lCity, 700, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lCity, 340, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfCity, 780, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfCity, 340, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lPhone, 700, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lPhone, 380, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfPhone, 795, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfPhone, 380, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lEmail, 700, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lEmail, 420, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfEmail, 780, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfEmail, 420, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, btnModify, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnModify, 500, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, btnDelete, 890, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnDelete, 500, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(taList);
		container.add(lSelectOwner1);
		container.add(lSelectOwner2);
		container.add(cbOwner);
		container.add(btnSelect);
		container.add(lName);
		container.add(tfName);
		container.add(lDocument);
		container.add(tfDocument);
		container.add(lBirthdate);
		container.add(tfBirthdate);
		container.add(lAddress);
		container.add(tfAddress);
		container.add(lCity);
		container.add(tfCity);
		container.add(lPhone);
		container.add(tfPhone);
		container.add(lEmail);
		container.add(tfEmail);
		container.add(btnModify);
		container.add(btnDelete);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		taList.setFont(new Font("Arial", Font.BOLD, 16));
		taList.setForeground(new Color (116, 128, 148));
		taList.setBackground(new Color (238, 238, 238));
		
		lSelectOwner1.setFont(new Font("Arial", Font.BOLD, 12));
		lSelectOwner1.setForeground(new Color (116, 128, 148));
		
		lSelectOwner2.setFont(new Font("Arial", Font.BOLD, 12));
		lSelectOwner2.setForeground(new Color (116, 128, 148));
		
		cbOwner.setFont(new Font("Arial", Font.PLAIN, 20));
		cbOwner.setForeground(new Color (116, 128, 148));
		
		btnSelect.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSelect.setForeground(new Color (116, 128, 148));
		
		lName.setFont(new Font("Arial", Font.BOLD, 20));
		lName.setForeground(new Color (116, 128, 148));
		
		tfName.setFont(new Font("Arial", Font.PLAIN, 20));
		tfName.setForeground(new Color (116, 128, 148));
		
		lDocument.setFont(new Font("Arial", Font.BOLD, 20));
		lDocument.setForeground(new Color (116, 128, 148));
		
		tfDocument.setFont(new Font("Arial", Font.PLAIN, 20));
		tfDocument.setForeground(new Color (116, 128, 148));
		
		lBirthdate.setFont(new Font("Arial", Font.BOLD, 20));
		lBirthdate.setForeground(new Color (116, 128, 148));
		
		tfBirthdate.setFont(new Font("Arial", Font.PLAIN, 20));
		tfBirthdate.setForeground(new Color (116, 128, 148));
		
		lAddress.setFont(new Font("Arial", Font.BOLD, 20));
		lAddress.setForeground(new Color (116, 128, 148));
		
		tfAddress.setFont(new Font("Arial", Font.PLAIN, 20));
		tfAddress.setForeground(new Color (116, 128, 148));
		
		lCity.setFont(new Font("Arial", Font.BOLD, 20));
		lCity.setForeground(new Color (116, 128, 148));
		
		tfCity.setFont(new Font("Arial", Font.PLAIN, 20));
		tfCity.setForeground(new Color (116, 128, 148));
		
		lPhone.setFont(new Font("Arial", Font.BOLD, 20));
		lPhone.setForeground(new Color (116, 128, 148));
		
		tfPhone.setFont(new Font("Arial", Font.PLAIN, 20));
		tfPhone.setForeground(new Color (116, 128, 148));
		
		lEmail.setFont(new Font("Arial", Font.BOLD, 20));
		lEmail.setForeground(new Color (116, 128, 148));
		
		tfEmail.setFont(new Font("Arial", Font.PLAIN, 20));
		tfEmail.setForeground(new Color (116, 128, 148));
		
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
		
		String acumList = "";
		SQLOwner ow = new SQLOwner();
		ArrayList<String> alOwners = new ArrayList<String>();		
		alOwners = ow.listOwners();
		
		for(int i = 0; i < alOwners.size(); i++) {
			cbOwner.addItem((i+1) + ". ");
			acumList += alOwners.get(i) + "\n";
		}
		
		taList.setText(acumList);
		
		btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String infoOwner = ow.selectOwner(cbOwner.getSelectedItem().toString());
				String[] parts = infoOwner.split(java.util.regex.Pattern.quote("|"));
				
				completeNameOwner = parts[0];
				documentOwner = parts[1];
				birthdayOwner = parts[2];
				addressOwner = parts[3];
				cityOwner = parts[4];
				phoneOwner = parts[5];
				emailOwner = parts[6];
				
				lName.setVisible(true);
				lDocument.setVisible(true);
				lBirthdate.setVisible(true);
				lAddress.setVisible(true);
				lCity.setVisible(true);
				lPhone.setVisible(true);
				lEmail.setVisible(true);
				tfName.setVisible(true);
				tfDocument.setVisible(true);
				tfBirthdate.setVisible(true);
				tfAddress.setVisible(true);
				tfCity.setVisible(true);
				tfPhone.setVisible(true);
				tfEmail.setVisible(true);
				btnModify.setVisible(true);
				btnDelete.setVisible(true);
				
				tfName.setText(completeNameOwner);
				tfDocument.setText(documentOwner);
				tfBirthdate.setText(birthdayOwner);
				tfAddress.setText(addressOwner);
				tfCity.setText(cityOwner);
				tfPhone.setText(phoneOwner);
				tfEmail.setText(emailOwner);
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
