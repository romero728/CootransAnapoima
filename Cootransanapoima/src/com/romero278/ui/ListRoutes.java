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

import com.romero278.kernel.connection.SQLRoute;
import com.romero278.kernel.connection.SQLTypeVehicle;

public class ListRoutes extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String flag, option, idRoute;
	String[] dataRoute;
	
	public ListRoutes(String fg, String op) {
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
		JLabel lSelectRoute1 = new JLabel("Selecciona un " + option.toLowerCase());
		JLabel lSelectRoute2 = new JLabel("para ver su información");
		JLabel lPlaceEnd = new JLabel("Destino: ");
		JLabel lDuration = new JLabel("Duración: ");
		JLabel lActive = new JLabel("Activo: ");
		JLabel lType = new JLabel("Tipo: ");
		JTextField tfPlaceEnd = new JTextField(12);
		JTextField tfDuration = new JTextField(6);
		JTextField tfActive = new JTextField(2);
		JTextArea taList = new JTextArea();
		JTextArea tfType = new JTextArea();
		JScrollPane scroll = new JScrollPane(taList);
		JScrollPane scrollType = new JScrollPane(tfType);
		JComboBox<String> cbRoute = new JComboBox<String>();
		JButton btnSelect = new JButton("Seleccionar");
		JButton btnModify = new JButton("Modificar");
		JButton btnDelete = new JButton("Eliminar");
		JButton btnBack = new JButton("Atrás");
		
		tfPlaceEnd.setEditable(false);
		tfDuration.setEditable(false);
		tfActive.setEditable(false);
		tfType.setEditable(false);
		
		tfPlaceEnd.setBorder(null);
		tfDuration.setBorder(null);
		tfActive.setBorder(null);
		
		taList.setEditable(false);
		taList.setLineWrap(true);
		
		scroll.setPreferredSize(new Dimension(250, 280));
		scroll.setBorder(null);
		
		scrollType.setPreferredSize(new Dimension(200, 60));
		scrollType.setBorder(null);
		
		lPlaceEnd.setVisible(false);
		tfPlaceEnd.setVisible(false);
		lDuration.setVisible(false);
		tfDuration.setVisible(false);
		lActive.setVisible(false);
		tfActive.setVisible(false);
		lType.setVisible(false);
		tfType.setVisible(false);
		btnModify.setVisible(false);
		btnDelete.setVisible(false);
		
		btnModify.setPreferredSize(new Dimension(150, 30));
		btnDelete.setPreferredSize(new Dimension(150, 30));
		btnBack.setPreferredSize(new Dimension(100, 30));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 100, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, scroll, 240, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, scroll, 180, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lSelectRoute1, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lSelectRoute1, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lSelectRoute2, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lSelectRoute2, 270, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, cbRoute, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, cbRoute, 320, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnSelect, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnSelect, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lPlaceEnd, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lPlaceEnd, 180, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfPlaceEnd, 810, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfPlaceEnd, 180, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lDuration, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lDuration, 220, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfDuration, 820, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfDuration, 220, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lActive, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lActive, 260, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfActive, 800, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfActive, 260, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lType, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lType, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfType, 800, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfType, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, btnModify, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnModify, 400, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, btnDelete, 890, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnDelete, 400, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(scroll);
		container.add(lSelectRoute1);
		container.add(lSelectRoute2);
		container.add(cbRoute);
		container.add(btnSelect);
		container.add(lPlaceEnd);
		container.add(tfPlaceEnd);
		container.add(lDuration);
		container.add(tfDuration);
		container.add(lActive);
		container.add(tfActive);
		container.add(lType);
		container.add(tfType);
		container.add(btnModify);
		container.add(btnDelete);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		taList.setFont(new Font("Arial", Font.BOLD, 18));
		taList.setForeground(new Color (116, 128, 148));
		taList.setBackground(new Color (238, 238, 238));
		
		lSelectRoute1.setFont(new Font("Arial", Font.BOLD, 12));
		lSelectRoute1.setForeground(new Color (116, 128, 148));
		
		lSelectRoute2.setFont(new Font("Arial", Font.BOLD, 12));
		lSelectRoute2.setForeground(new Color (116, 128, 148));
		
		cbRoute.setFont(new Font("Arial", Font.PLAIN, 20));
		cbRoute.setForeground(new Color (116, 128, 148));
		
		btnSelect.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSelect.setForeground(new Color (116, 128, 148));
		
		lPlaceEnd.setFont(new Font("Arial", Font.BOLD, 20));
		lPlaceEnd.setForeground(new Color (116, 128, 148));
		
		tfPlaceEnd.setFont(new Font("Arial", Font.PLAIN, 20));
		tfPlaceEnd.setForeground(new Color (116, 128, 148));
		
		lDuration.setFont(new Font("Arial", Font.BOLD, 20));
		lDuration.setForeground(new Color (116, 128, 148));
		
		tfDuration.setFont(new Font("Arial", Font.PLAIN, 20));
		tfDuration.setForeground(new Color (116, 128, 148));
		
		lActive.setFont(new Font("Arial", Font.BOLD, 20));
		lActive.setForeground(new Color (116, 128, 148));
		
		tfActive.setFont(new Font("Arial", Font.PLAIN, 20));
		tfActive.setForeground(new Color (116, 128, 148));
		
		lType.setFont(new Font("Arial", Font.BOLD, 20));
		lType.setForeground(new Color (116, 128, 148));
		
		tfType.setFont(new Font("Arial", Font.PLAIN, 20));
		tfType.setForeground(new Color (116, 128, 148));
		tfType.setBackground(new Color (238, 238, 238));
		
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
		
		SQLRoute route = new SQLRoute();
		SQLTypeVehicle typeVehicle = new SQLTypeVehicle();
		ArrayList<String> alRoutes = new ArrayList<String>();
		String acumList = "", replaceRoute = "";
		String[] split;
		
		alRoutes = route.listRoutes();
		
		for(int i = 0; i < alRoutes.size(); i++) {
			replaceRoute = alRoutes.get(i).replace('.', '|');
			split = replaceRoute.split(Pattern.quote("|"));
			cbRoute.addItem(split[0]);
			acumList += alRoutes.get(i) + "\n";
		}
		
		taList.setText(acumList);
		
		if(alRoutes.isEmpty()) {
			btnSelect.setVisible(false);
		} else {
			btnSelect.setVisible(true);
		}
		
		btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String infoRoute = route.selectRoute(cbRoute.getSelectedItem().toString());
				dataRoute = infoRoute.split(Pattern.quote("|"));
				
				idRoute = dataRoute[0];
				tfPlaceEnd.setText(dataRoute[2]);
				tfDuration.setText(dataRoute[3]);
				tfActive.setText(dataRoute[4]);
				
				String[] types = route.getTypeVehicle(idRoute);
				String acumTypes = "";
				
				if(types.length == 3) {
					tfType.setText("Todos");
				} else {
					for(int i = 0; i < types.length; i++) {
						acumTypes += typeVehicle.getNameTypeVehicle(types[i]) + " ";
					}
					
					tfType.setText(acumTypes);
				}
				
				lPlaceEnd.setVisible(true);
				tfPlaceEnd.setVisible(true);
				lDuration.setVisible(true);
				tfDuration.setVisible(true);
				lActive.setVisible(true);
				tfActive.setVisible(true);
				lType.setVisible(true);
				tfType.setVisible(true);
				btnModify.setVisible(true);
				btnDelete.setVisible(true);
			}
		});
		
		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				ModifyRoute mod = new ModifyRoute(flag, option, dataRoute);
				mod.setVisible(true);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(null, "¿Deseas eliminar este " + option.toLowerCase() + "?", option + " - Eliminar", JOptionPane.YES_NO_OPTION);
				
				if(answer == JOptionPane.YES_OPTION) {
					String request = route.deleteRoute(idRoute);
					
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
