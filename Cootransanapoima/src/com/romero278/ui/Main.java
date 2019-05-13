package com.romero278.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setTitle("My test");
		setBounds(0, 0, 1200, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel title = new JLabel("Ingresa los datos para continuar: ");
		JLabel lEmpresa = new JLabel("Empresa: ");
		JLabel lClave = new JLabel("Clave: ");
		JTextField tfEmpresa = new JTextField(30);
		JPasswordField pfClave = new JPasswordField(30);
		JButton btnNext = new JButton("Ingresar");
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.WEST, title, 300, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 120, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lEmpresa, 340, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lEmpresa, 220, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfEmpresa, 450, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfEmpresa, 220, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lClave, 340, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lClave, 260, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, pfClave, 450, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, pfClave, 260, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, btnNext, 550, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnNext, 320, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(lEmpresa);
		container.add(tfEmpresa);
		container.add(lClave);
		container.add(pfClave);
		container.add(btnNext);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		lEmpresa.setFont(new Font("Arial", Font.BOLD, 20));
		lEmpresa.setForeground(new Color (116, 128, 148));
		
		lClave.setFont(new Font("Arial", Font.BOLD, 20));
		lClave.setForeground(new Color (116, 128, 148));
		
		tfEmpresa.setFont(new Font("Arial", Font.PLAIN, 18));
		tfEmpresa.setForeground(new Color (116, 128, 148));
		
		pfClave.setFont(new Font("Arial", Font.PLAIN, 18));
		pfClave.setForeground(new Color (116, 128, 148));
		
		btnNext.setFont(new Font("Arial", Font.BOLD, 18));
		btnNext.setForeground(new Color (100, 100, 100));
	}

}