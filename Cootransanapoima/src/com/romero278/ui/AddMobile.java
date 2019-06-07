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
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.romero278.kernel.connection.SQLMobile;
import com.romero278.kernel.connection.SQLOwner;
import com.romero278.kernel.connection.SQLTypeVehicle;

public class AddMobile extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String flag, option;
	
	public AddMobile(String fg, String op) {
		flag = fg;
		option = op;
		
		setTitle(option + " - Agregar");
		setBounds(0, 0, 1200, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel title = new JLabel("Agregar " + option.toLowerCase());
		JLabel subtitle = new JLabel("Ingresa los datos");
		JLabel lNumber = new JLabel("Número: ");
		JLabel lOwner = new JLabel("Propietario: ");
		JLabel lBrand = new JLabel("Marca: ");
		JLabel lModel = new JLabel("Modelo: ");
		JLabel lLicensePlate = new JLabel("Placa: ");
		JLabel lCapacity = new JLabel("Capacidad: ");
		JLabel lType = new JLabel("Tipo: ");
		JLabel lActive = new JLabel("Activo: ");
		JTextField tfNumber = new JTextField(6);
		JTextField tfBrand = new JTextField(12);
		JTextField tfModel = new JTextField(12);
		JTextField tfLicensePlate = new JTextField(6);
		JTextField tfCapacity = new JTextField(6);
		JComboBox<String> cbOwner = new JComboBox<String>();
		JComboBox<String> cbType = new JComboBox<String>();
		JComboBox<String> cbActive = new JComboBox<String>();
		JButton btnAdd = new JButton("Agregar");
		JButton btnBack = new JButton("Atrás");
		
		btnAdd.setPreferredSize(new Dimension(200, 36));
		btnBack.setPreferredSize(new Dimension(100, 30));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 50, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subtitle, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, subtitle, 130, SpringLayout.NORTH, container);
		
		// ---
		
		springLayout.putConstraint(SpringLayout.WEST, lNumber, 382, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lNumber, 200, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfNumber, 482, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfNumber, 200, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lOwner, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lOwner, 200, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, cbOwner, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, cbOwner, 200, SpringLayout.NORTH, container);
		
		// ---
		
		springLayout.putConstraint(SpringLayout.WEST, lBrand, 320, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lBrand, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfBrand, 400, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfBrand, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lModel, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lModel, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfModel, 690, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfModel, 250, SpringLayout.NORTH, container);
		
		// ---
		
		springLayout.putConstraint(SpringLayout.WEST, lLicensePlate, 412, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lLicensePlate, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfLicensePlate, 482, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfLicensePlate, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lCapacity, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lCapacity, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfCapacity, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfCapacity, 300, SpringLayout.NORTH, container);
		
		// ---
		
		springLayout.putConstraint(SpringLayout.WEST, lType, 390, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lType, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, cbType, 452, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, cbType, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lActive, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lActive, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, cbActive, 680, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, cbActive, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnAdd, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd, 450, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(subtitle);
		container.add(lNumber);
		container.add(tfNumber);
		container.add(lOwner);
		container.add(cbOwner);
		container.add(lBrand);
		container.add(tfBrand);
		container.add(lModel);
		container.add(tfModel);
		container.add(lLicensePlate);
		container.add(tfLicensePlate);
		container.add(lCapacity);
		container.add(tfCapacity);
		container.add(lType);
		container.add(cbType);
		container.add(lActive);
		container.add(cbActive);
		container.add(btnAdd);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		subtitle.setFont(new Font("Arial", Font.BOLD, 28));
		subtitle.setForeground(new Color (116, 128, 148));
		
		lNumber.setFont(new Font("Arial", Font.BOLD, 20));
		lNumber.setForeground(new Color (116, 128, 148));
		
		tfNumber.setFont(new Font("Arial", Font.PLAIN, 18));
		tfNumber.setForeground(new Color (116, 128, 148));
		
		lOwner.setFont(new Font("Arial", Font.BOLD, 20));
		lOwner.setForeground(new Color (116, 128, 148));
		
		cbOwner.setFont(new Font("Arial", Font.PLAIN, 18));
		cbOwner.setForeground(new Color (116, 128, 148));
		
		lBrand.setFont(new Font("Arial", Font.BOLD, 20));
		lBrand.setForeground(new Color (116, 128, 148));
		
		tfBrand.setFont(new Font("Arial", Font.PLAIN, 18));
		tfBrand.setForeground(new Color (116, 128, 148));
		
		lModel.setFont(new Font("Arial", Font.BOLD, 20));
		lModel.setForeground(new Color (116, 128, 148));
		
		tfModel.setFont(new Font("Arial", Font.PLAIN, 18));
		tfModel.setForeground(new Color (116, 128, 148));
		
		lLicensePlate.setFont(new Font("Arial", Font.BOLD, 20));
		lLicensePlate.setForeground(new Color (116, 128, 148));
		
		tfLicensePlate.setFont(new Font("Arial", Font.PLAIN, 18));
		tfLicensePlate.setForeground(new Color (116, 128, 148));
		
		lCapacity.setFont(new Font("Arial", Font.BOLD, 20));
		lCapacity.setForeground(new Color (116, 128, 148));
		
		tfCapacity.setFont(new Font("Arial", Font.PLAIN, 18));
		tfCapacity.setForeground(new Color (116, 128, 148));
		
		lType.setFont(new Font("Arial", Font.BOLD, 20));
		lType.setForeground(new Color (116, 128, 148));
		
		cbType.setFont(new Font("Arial", Font.PLAIN, 18));
		cbType.setForeground(new Color (116, 128, 148));
		
		lActive.setFont(new Font("Arial", Font.BOLD, 20));
		lActive.setForeground(new Color (116, 128, 148));
		
		cbActive.setFont(new Font("Arial", Font.PLAIN, 18));
		cbActive.setForeground(new Color (116, 128, 148));
		
		btnAdd.setFont(new Font("Arial", Font.BOLD, 20));
		btnAdd.setForeground(new Color (116, 128, 148));
		
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color (196, 69, 59));
		
		/* --- Logic part --- */
		 
		SQLOwner ow = new SQLOwner();
		SQLTypeVehicle tve = new SQLTypeVehicle();
		SQLMobile mob = new SQLMobile();
		
		ArrayList<String> alOwners = ow.listOwners();
		ArrayList<String> alTypeVehicle = tve.listTypeVehicles();
		ArrayList<String[]> alMobOw = new ArrayList<String[]>();
		ArrayList<String[]> alMobType = new ArrayList<String[]>();
		
		String aux;
		String[] splitOwner = null;
		
		for(int i = 0; i < alOwners.size(); i++) {
			aux = alOwners.get(i).replace('.', '|');
			splitOwner = aux.split(Pattern.quote("|"));
			alMobOw.add(splitOwner);
			cbOwner.addItem(splitOwner[1]);
		}
		
		String[] splitType = null;
		
		for(int i = 0; i < alTypeVehicle.size(); i++) {
			splitType = alTypeVehicle.get(i).split(Pattern.quote("|"));
			alMobType.add(splitType);
			cbType.addItem(splitType[1]);
		}
		
		cbActive.addItem("Si");
		cbActive.addItem("No");
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tfNumber.getText().isEmpty() || tfBrand.getText().isEmpty() || tfModel.getText().isEmpty() || tfLicensePlate.getText().isEmpty() || tfCapacity.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debes diligenciar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					String request;
					
					String owner = cbOwner.getSelectedItem().toString();
					String idOwner = "";
					String[] arAuxOw = null;
					
					for(int i = 0; i < alMobOw.size(); i++) {
						arAuxOw = alMobOw.get(i); 
						
						if(arAuxOw[1].equals(owner)) {
							idOwner = arAuxOw[0];
						}
					}
					
					String type = cbType.getSelectedItem().toString();
					String idType = "";
					String[] arAuxTy = null;
					
					for(int i = 0; i < alMobType.size(); i++) {
						arAuxTy = alMobType.get(i); 
						
						if(arAuxTy[1].equals(type)) {
							idType = arAuxTy[0];
						}
					}
					
					request = mob.insertMobile(tfNumber.getText(), idOwner, tfBrand.getText(), tfModel.getText(), tfLicensePlate.getText(), tfCapacity.getText(), idType, cbActive.getSelectedItem().toString());
					
					switch (request) {
						case "success":
							JOptionPane.showMessageDialog(null, option +  " agregado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);							
							goBack();
							break;
						case "number":
							JOptionPane.showMessageDialog(null, "El número del móvil ya ha sido registrado previamente", "Error", JOptionPane.ERROR_MESSAGE);
							break;
						case "error insert":
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
