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

import com.romero278.kernel.connection.SQLPlace;
import com.romero278.kernel.connection.SQLRoute;

public class AddRoute extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String flag, option;
	
	public AddRoute(String fg, String op) {
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
		JLabel lPlaceEnd = new JLabel("Destino: ");
		JLabel lDuration = new JLabel("Duración: ");
		JLabel lActive = new JLabel("Activo: ");
		JTextField tfDuration = new JTextField(6);
		JComboBox<String> cbPlaceEnd = new JComboBox<String>();
		JComboBox<String> cbActive = new JComboBox<String>();
		JButton btnAdd = new JButton("Agregar");
		JButton btnBack = new JButton("Atrás");
		
		btnAdd.setPreferredSize(new Dimension(200, 36));
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
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnAdd, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd, 400, SpringLayout.NORTH, container);
		
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
		container.add(btnAdd);
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
		
		btnAdd.setFont(new Font("Arial", Font.BOLD, 20));
		btnAdd.setForeground(new Color (116, 128, 148));
		
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
		
		btnAdd.addActionListener(new ActionListener() {
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
					
					request = route.insertRoute(end, tfDuration.getText(), cbActive.getSelectedItem().toString());
					
					switch(request) {
						case "success":
							JOptionPane.showMessageDialog(null, option +  " agregado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);							
							goBack();
							break;
						case "end":
							JOptionPane.showMessageDialog(null, "El destino ha sido registrado previamente", "Error", JOptionPane.ERROR_MESSAGE);
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
