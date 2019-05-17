package com.romero278.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

public class ConfigMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public ConfigMenu() {
		setTitle("Configuraci�n");
		setBounds(0, 0, 1200, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel title = new JLabel("CONFIGURACI�N");
		JLabel subtitle = new JLabel("Elige una opci�n");
		JLabel lOwners = new JLabel("Propietarios de los m�viles");
		JLabel lMobile = new JLabel("M�viles que pertenecen a la empresa");
		JLabel lPlaces = new JLabel("Lugares a los que se accede por medio de las rutas");
		JLabel lRoutes = new JLabel("Rutas que realizan los m�viles");
		JLabel lTours = new JLabel("El d�a y la hora en el que se realizan las rutas");
		JButton btnOwners = new JButton("Propietarios");
		JButton btnMobile = new JButton("M�viles");
		JButton btnPlaces = new JButton("Lugares");
		JButton btnRoutes = new JButton("Rutas");
		JButton btnTours = new JButton("Recorridos");
		
		btnOwners.setPreferredSize(new Dimension(200, 36));
		btnMobile.setPreferredSize(new Dimension(200, 36));
		btnPlaces.setPreferredSize(new Dimension(200, 36));
		btnRoutes.setPreferredSize(new Dimension(200, 36));
		btnTours.setPreferredSize(new Dimension(200, 36));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 50, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subtitle, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, subtitle, 110, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnOwners, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnOwners, 150, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lOwners, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lOwners, 190, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnMobile, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnMobile, 230, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lMobile, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lMobile, 270, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnPlaces, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnPlaces, 310, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lPlaces, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lPlaces, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnRoutes, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnRoutes, 390, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lRoutes, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lRoutes, 430, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnTours, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnTours, 470, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lTours, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lTours, 510, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(subtitle);
		container.add(btnOwners);
		container.add(lOwners);
		container.add(btnMobile);
		container.add(lMobile);
		container.add(btnPlaces);
		container.add(lPlaces);
		container.add(btnRoutes);
		container.add(lRoutes);
		container.add(btnTours);
		container.add(lTours);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		subtitle.setFont(new Font("Arial", Font.BOLD, 28));
		subtitle.setForeground(new Color (116, 128, 148));
		
		btnOwners.setFont(new Font("Arial", Font.BOLD, 20));
		btnOwners.setForeground(new Color (116, 128, 148));
		
		lOwners.setFont(new Font("Arial", Font.BOLD, 12));
		lOwners.setForeground(new Color (116, 128, 148));
		
		btnMobile.setFont(new Font("Arial", Font.BOLD, 20));
		btnMobile.setForeground(new Color (116, 128, 148));
		
		lMobile.setFont(new Font("Arial", Font.BOLD, 12));
		lMobile.setForeground(new Color (116, 128, 148));
		
		btnPlaces.setFont(new Font("Arial", Font.BOLD, 20));
		btnPlaces.setForeground(new Color (116, 128, 148));
		
		lPlaces.setFont(new Font("Arial", Font.BOLD, 12));
		lPlaces.setForeground(new Color (116, 128, 148));
		
		btnRoutes.setFont(new Font("Arial", Font.BOLD, 20));
		btnRoutes.setForeground(new Color (116, 128, 148));
		
		lRoutes.setFont(new Font("Arial", Font.BOLD, 12));
		lRoutes.setForeground(new Color (116, 128, 148));
		
		btnTours.setFont(new Font("Arial", Font.BOLD, 20));
		btnTours.setForeground(new Color (116, 128, 148));
		
		lTours.setFont(new Font("Arial", Font.BOLD, 12));
		lTours.setForeground(new Color (116, 128, 148));
	}
}
