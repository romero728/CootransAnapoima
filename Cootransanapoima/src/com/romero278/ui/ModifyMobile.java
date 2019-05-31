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

public class ModifyMobile extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String flag, option, idOwner;
	String[] dataMobile;
	
	public ModifyMobile(String fg, String op, String[] data) {
		flag = fg;
		option = op;
		dataMobile = data;
		
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
		JButton btnModify = new JButton("Modificar");
		JButton btnBack = new JButton("Atrás");
		
		btnModify.setPreferredSize(new Dimension(200, 36));
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
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnModify, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnModify, 450, SpringLayout.NORTH, container);
		
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
		container.add(btnModify);
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
		
		btnModify.setFont(new Font("Arial", Font.BOLD, 20));
		btnModify.setForeground(new Color (116, 128, 148));
		
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color (196, 69, 59));
		
		/* --- Logic part --- */
		
		SQLOwner ow = new SQLOwner();
		SQLTypeVehicle tve = new SQLTypeVehicle();
		SQLMobile mob = new SQLMobile();
		
		ArrayList<String> alOwners = ow.listOwners();
		ArrayList<String> alTypeVehicle = tve.selectTypeVehicles();
		ArrayList<String[]> alMobOw = new ArrayList<String[]>();
		
		String aux;
		String[] split = null;
		
		for(int i = 0; i < alOwners.size(); i++) {
			aux = alOwners.get(i).replace('.', '|');
			split = aux.split(Pattern.quote("|"));
			alMobOw.add(split);
			cbOwner.addItem(split[1]);
		}
		
		for(int i = 0; i < alTypeVehicle.size(); i++) {
			cbType.addItem(alTypeVehicle.get(i));
		}
		
		cbActive.addItem("Si");
		cbActive.addItem("No");
		
		tfNumber.setText(dataMobile[1]);
		tfBrand.setText(dataMobile[4]);
		tfModel.setText(dataMobile[5]);
		tfLicensePlate.setText(dataMobile[6]);
		tfCapacity.setText(dataMobile[7]);
		
		cbOwner.setSelectedItem(dataMobile[3]);
		cbType.setSelectedItem(dataMobile[8]);
		cbActive.setSelectedItem(dataMobile[9]);
		
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tfNumber.getText().isEmpty() || tfBrand.getText().isEmpty() || tfModel.getText().isEmpty() || tfLicensePlate.getText().isEmpty() || tfCapacity.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debes diligenciar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					String request;
					String owner = cbOwner.getSelectedItem().toString();
					
					String idOwner = "";
					String[] arAux = null;
					
					for(int i = 0; i < alMobOw.size(); i++) {
						arAux = alMobOw.get(i); 
						
						if(arAux[1].equals(owner)) {
							idOwner = arAux[0];
						}
					}
					
					request = mob.updateMobile(dataMobile[0], tfNumber.getText(), idOwner, tfBrand.getText(), tfModel.getText(), tfLicensePlate.getText(), tfCapacity.getText(), cbType.getSelectedItem().toString(), cbActive.getSelectedItem().toString());
					
					if(request.equals("success")) {
						JOptionPane.showMessageDialog(null, option +  " agregado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);							
						goBack();
					} else {
						JOptionPane.showMessageDialog(null, "Ha ocurrido un error, inténtalo de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
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
		
		ListMobiles list = new ListMobiles(flag, option);
		list.setVisible(true);
	}
}
