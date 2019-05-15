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

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public MainMenu(String nameCompany) {
		setTitle("Menú principal");
		setBounds(0, 0, 1200, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel title = new JLabel(nameCompany.toUpperCase());
		JLabel subtitle = new JLabel("Elige una opción");
		JLabel lGenerate = new JLabel("Puedes generar el plan de rodamiento semanal");
		JLabel lEdit = new JLabel("Puedes configurar las rutas, los móviles, entre otros");
		JButton btnGenerate = new JButton("Generar plan");
		JButton btnEdit = new JButton("Configuración");
		
		btnGenerate.setPreferredSize(new Dimension(200, 36));
		btnEdit.setPreferredSize(new Dimension(200, 36));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 100, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subtitle, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, subtitle, 200, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnGenerate, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnGenerate, 280, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lGenerate, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lGenerate, 320, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnEdit, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnEdit, 370, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lEdit, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lEdit, 410, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(subtitle);
		container.add(lGenerate);
		container.add(lEdit);
		container.add(btnGenerate);
		container.add(btnEdit);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		subtitle.setFont(new Font("Arial", Font.BOLD, 28));
		subtitle.setForeground(new Color (116, 128, 148));
		
		btnGenerate.setFont(new Font("Arial", Font.BOLD, 20));
		btnGenerate.setForeground(new Color (116, 128, 148));
		
		lGenerate.setFont(new Font("Arial", Font.BOLD, 12));
		lGenerate.setForeground(new Color (116, 128, 148));
		
		btnEdit.setFont(new Font("Arial", Font.BOLD, 20));
		btnEdit.setForeground(new Color (116, 128, 148));
		
		lEdit.setFont(new Font("Arial", Font.BOLD, 12));
		lEdit.setForeground(new Color (116, 128, 148));
	}

}
