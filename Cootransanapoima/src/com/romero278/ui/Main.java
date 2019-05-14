package com.romero278.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;
import com.romero278.kernel.connection.ConnectionBD;

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
		ConnectionBD conBD = new ConnectionBD();
		Connection connection = conBD.connection();
		
		/*String sql = "INSERT INTO lugares (nombre_lugar) VALUES ('Tolima')";
		try {
			
			System.out.println(sql);
			PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
			prep.executeUpdate();
			prep.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
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
		
		btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tfEmpresa.getText().isEmpty() || pfClave.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debes diligenciar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					String sql = "SELECT count(*) FROM empresas WHERE nombre_empresa = '"+tfEmpresa.getText()+"' and password_empresa = '"+pfClave.getText()+"'";
					try {
						PreparedStatement prep = (PreparedStatement) connection.prepareStatement(sql);
						ResultSet res = (ResultSet) prep.executeQuery();
						
						while(res.next()) {
							if(res.getString("count(*)").equals("0")) {								
								JOptionPane.showMessageDialog(null, "Nombre de la empresa o contraseña errónea", "Error", JOptionPane.ERROR_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "Bienvenido al gestor de rodamientos", "OK", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
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