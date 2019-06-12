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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.romero278.kernel.connection.SQLPlace;
import com.romero278.kernel.connection.SQLRoute;

public class ModifyRoute extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String flag, option, idRoute;
	String[] dataRoute;
	
	public ModifyRoute(String fg, String op, String[] data) {
		flag = fg;
		option = op;
		dataRoute = data;
		
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
		JLabel lPlaceEnd = new JLabel("Destino: ");
		JLabel lDuration = new JLabel("Duración: ");
		JLabel lActive = new JLabel("Activo: ");
		JLabel lType = new JLabel("Tipo de vehículo: ");
		JTextField tfDuration = new JTextField(6);
		JComboBox<String> cbPlaceEnd = new JComboBox<String>();
		JComboBox<String> cbActive = new JComboBox<String>();
		JCheckBox chTypeMicro = new JCheckBox("Microbus");
		JCheckBox chTypeCamp = new JCheckBox("Campero / Camioneta");
		JCheckBox chTypeWag = new JCheckBox("Wagon");
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
		
		springLayout.putConstraint(SpringLayout.WEST, lPlaceEnd, 480, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lPlaceEnd, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, cbPlaceEnd, 580, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, cbPlaceEnd, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lDuration, 400, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lDuration, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfDuration, 500, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfDuration, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lActive, 650, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lActive, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, cbActive, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, cbActive, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lType, 260, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lType, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, chTypeMicro, 440, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, chTypeMicro, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, chTypeCamp, 550, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, chTypeCamp, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, chTypeWag, 760, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, chTypeWag, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnModify, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnModify, 400, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(subtitle);
		container.add(lPlaceEnd);
		container.add(cbPlaceEnd);
		container.add(lDuration);
		container.add(tfDuration);
		container.add(lActive);
		container.add(cbActive);
		container.add(lType);
		container.add(chTypeMicro);
		container.add(chTypeCamp);
		container.add(chTypeWag);
		container.add(btnModify);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		subtitle.setFont(new Font("Arial", Font.BOLD, 28));
		subtitle.setForeground(new Color (116, 128, 148));
		
		lPlaceEnd.setFont(new Font("Arial", Font.BOLD, 20));
		lPlaceEnd.setForeground(new Color (116, 128, 148));
		
		cbPlaceEnd.setFont(new Font("Arial", Font.PLAIN, 18));
		cbPlaceEnd.setForeground(new Color (116, 128, 148));
		
		lDuration.setFont(new Font("Arial", Font.BOLD, 20));
		lDuration.setForeground(new Color (116, 128, 148));
		
		tfDuration.setFont(new Font("Arial", Font.PLAIN, 18));
		tfDuration.setForeground(new Color (116, 128, 148));
		
		lActive.setFont(new Font("Arial", Font.BOLD, 20));
		lActive.setForeground(new Color (116, 128, 148));
		
		cbActive.setFont(new Font("Arial", Font.PLAIN, 18));
		cbActive.setForeground(new Color (116, 128, 148));
		
		lType.setFont(new Font("Arial", Font.BOLD, 20));
		lType.setForeground(new Color (116, 128, 148));
		
		chTypeMicro.setFont(new Font("Arial", Font.PLAIN, 18));
		chTypeMicro.setForeground(new Color (116, 128, 148));
		
		chTypeCamp.setFont(new Font("Arial", Font.PLAIN, 18));
		chTypeCamp.setForeground(new Color (116, 128, 148));
		
		chTypeWag.setFont(new Font("Arial", Font.PLAIN, 18));
		chTypeWag.setForeground(new Color (116, 128, 148));
		
		btnModify.setFont(new Font("Arial", Font.BOLD, 20));
		btnModify.setForeground(new Color (116, 128, 148));
		
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color (196, 69, 59));
		
		/* --- Logic part --- */
		
		SQLRoute route = new SQLRoute();
		SQLPlace place = new SQLPlace();
		ArrayList<String> alPlaces = place.listPlaces();
		String replacePlace = "";
		String[] splitPlace;
		
		for(int i = 0; i < alPlaces.size(); i++) {
			replacePlace = alPlaces.get(i).replace('.', '|');
			splitPlace = replacePlace.split(Pattern.quote("|"));
			cbPlaceEnd.addItem(splitPlace[1]);
		}
		
		cbActive.addItem("Si");
		cbActive.addItem("No");
		
		idRoute = dataRoute[0];
		tfDuration.setText(dataRoute[3]);
		cbPlaceEnd.setSelectedItem(dataRoute[2]);
		cbActive.setSelectedItem(dataRoute[4]);
		
		String[] types = route.getTypeVehicle(idRoute);
		
		for(int i = 0; i < types.length; i++) {
			switch (types[i]) {
				case "1":
					chTypeMicro.setSelected(true);
					break;
				case "2":
					chTypeCamp.setSelected(true);
					break;
				case "3":
					chTypeWag.setSelected(true);
					break;
				default:
					break;
			}
		}
		
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tfDuration.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debes diligenciar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					String request, end = "", rp;
					String[] spl;
					
					for(int i = 0; i < alPlaces.size(); i++) {
						rp = alPlaces.get(i).replace('.', '|');
						spl = rp.split(Pattern.quote("|"));
						
						if(spl[1].equals(cbPlaceEnd.getSelectedItem())) {
							end = spl[0];
						}
					}
					
					String[] types = new String[3];
					
					if(chTypeMicro.isSelected()) {
						types[0] = "1";
					} else {
						types[0] = "0";
					}
					
					if(chTypeCamp.isSelected()) {
						types[1] = "1";
					} else {
						types[1] = "0";
					}
					
					if(chTypeWag.isSelected()) {
						types[2] = "1";
					} else {
						types[2] = "0";
					}
					
					request = route.updateRoute(idRoute, end, tfDuration.getText(), cbActive.getSelectedItem().toString(), types);
					
					switch(request) {
						case "success":
							JOptionPane.showMessageDialog(null, option +  " modificado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);							
							goBack();
							break;
						case "end":
							JOptionPane.showMessageDialog(null, "El destino ha sido registrado previamente", "Error", JOptionPane.ERROR_MESSAGE);
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
		
		ListRoutes list = new ListRoutes(flag, option);
		list.setVisible(true);
	}
}
