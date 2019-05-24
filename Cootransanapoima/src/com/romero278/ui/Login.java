package com.romero278.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.romero278.kernel.connection.SQLLogin;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {		
		setTitle("Ingreso");
		setBounds(0, 0, 1200, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel title = new JLabel("Ingresa los datos para continuar ");
		JLabel lCompany = new JLabel("Empresa: ");
		JLabel lPassword = new JLabel("Clave: ");
		JTextField tfCompany = new JTextField(20);
		JPasswordField pfPassword = new JPasswordField(20);
		JButton btnNext = new JButton("Ingresar");
		
		btnNext.setPreferredSize(new Dimension(200, 36));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 120, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lCompany, 420, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lCompany, 220, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfCompany, 520, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfCompany, 220, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lPassword, 420, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lPassword, 260, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, pfPassword, 520, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, pfPassword, 260, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnNext, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnNext, 320, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(lCompany);
		container.add(tfCompany);
		container.add(lPassword);
		container.add(pfPassword);
		container.add(btnNext);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		lCompany.setFont(new Font("Arial", Font.BOLD, 20));
		lCompany.setForeground(new Color (116, 128, 148));
		
		lPassword.setFont(new Font("Arial", Font.BOLD, 20));
		lPassword.setForeground(new Color (116, 128, 148));
		
		tfCompany.setFont(new Font("Arial", Font.PLAIN, 18));
		tfCompany.setForeground(new Color (116, 128, 148));
		
		pfPassword.setFont(new Font("Arial", Font.PLAIN, 18));
		pfPassword.setForeground(new Color (116, 128, 148));
		
		btnNext.setFont(new Font("Arial", Font.BOLD, 18));
		btnNext.setForeground(new Color (100, 100, 100));
		
		/* --- Logic Part --- */
		
		btnNext.addActionListener(new ActionListener() {			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tfCompany.getText().isEmpty() || pfPassword.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debes diligenciar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					SQLLogin log = new SQLLogin();
					boolean request = log.login(tfCompany.getText(), pfPassword.getText());
					
					if(request) {
						setVisible(false);
						
						MainMenu mainMenu = new MainMenu(tfCompany.getText());
						mainMenu.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Nombre de la empresa o contraseña errónea", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}				
			}
		});
	}
}