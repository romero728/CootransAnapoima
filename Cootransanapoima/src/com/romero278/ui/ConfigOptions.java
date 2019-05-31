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
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

public class ConfigOptions extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String nameCompany;
	
	public ConfigOptions(String flag, String nameComp) {
		nameCompany = nameComp;
		
		String option = "";
		setTitle(flag + " - Opciones");
		setBounds(0, 0, 1200, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		switch(flag) {
			case "Propietarios":
				option = owner(flag);
				break;
			case "Móviles":
				option = mobile(flag);
				break;
			case "Lugares":
				option = place(flag);
				break;
			case "Rutas":
				option = route(flag);
				break;
			case "Recorridos":
				option = tour(flag);
				break;
		}
		
		JLabel title = new JLabel(flag.toUpperCase());
		JLabel subtitle = new JLabel("Elige una opción");
		JLabel lAdd = new JLabel("Puedes agregar un nuevo " + option.toLowerCase());
		JLabel lList = new JLabel("Puedes ver, modificar o borrar un " + option.toLowerCase());
		JButton btnAdd = new JButton("Agregar " + option.toLowerCase());
		JButton btnList = new JButton("Ver todos los " + flag.toLowerCase());
		JButton btnBack = new JButton("Atrás");
		
		btnAdd.setPreferredSize(new Dimension(300, 36));
		btnList.setPreferredSize(new Dimension(300, 36));
		btnBack.setPreferredSize(new Dimension(100, 30));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 120, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subtitle, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, subtitle, 200, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnList, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnList, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lList, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lList, 290, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnAdd, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd, 340, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lAdd, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lAdd, 380, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(subtitle);
		container.add(btnList);
		container.add(lList);
		container.add(btnAdd);
		container.add(lAdd);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		subtitle.setFont(new Font("Arial", Font.BOLD, 28));
		subtitle.setForeground(new Color (116, 128, 148));
		
		btnList.setFont(new Font("Arial", Font.BOLD, 20));
		btnList.setForeground(new Color (116, 128, 148));
		
		lList.setFont(new Font("Arial", Font.BOLD, 12));
		lList.setForeground(new Color (116, 128, 148));
		
		btnAdd.setFont(new Font("Arial", Font.BOLD, 20));
		btnAdd.setForeground(new Color (116, 128, 148));
		
		lAdd.setFont(new Font("Arial", Font.BOLD, 12));
		lAdd.setForeground(new Color (116, 128, 148));
		
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color (196, 69, 59));
		
		/* --- Logic part --- */
		
		final String fOption = option;
		
		btnList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				switch (fOption) {
					case "Propietario":
						ListOwners listo = new ListOwners(flag, fOption);
						listo.setVisible(true);
						break;
					case "Móvil":
						ListMobiles listmob = new ListMobiles(flag, fOption);
						listmob.setVisible(true);
						break;
					case "Lugar":
						ListPlaces listpl = new ListPlaces(flag, fOption);
						listpl.setVisible(true);
						break;
					case "Ruta":
						ListRoutes listrou = new ListRoutes(flag, fOption);
						listrou.setVisible(true);
						break;
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				switch(fOption) {
					case "Propietario":
						AddOwner addo = new AddOwner(flag, fOption);
						addo.setVisible(true);
						break;
					case "Móvil":
						AddMobile addmo = new AddMobile(flag, fOption);
						addmo.setVisible(true);
						break;
					case "Lugar":
						AddPlace addpl = new AddPlace(flag, fOption);
						addpl.setVisible(true);
						break;
					case "Ruta":
						AddRoute addRou = new AddRoute(flag, fOption);
						addRou.setVisible(true);
						break;
					case "Recorrido":
						break;
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
	
	String owner(String flag) {		
		return "Propietario";
	}
	
	String mobile(String flag) {
		return "Móvil";
	}
	
	String place(String flag) {
		return "Lugar";
	}
	
	String route(String flag) {
		return "Ruta";
	}
	
	String tour(String flag) {
		return "Recorrido";
	}
	
	void goBack() {
		setVisible(false);
		
		ConfigMenu conMenu = new ConfigMenu(nameCompany);
		conMenu.setVisible(true);
	}
}
