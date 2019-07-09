package com.romero278.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.romero278.kernel.connection.SQLOwner;
import com.romero278.kernel.connection.SQLTypeDocument;
import com.toedter.calendar.JDateChooser;

public class AddOwner extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	String flag, option;
	
	@SuppressWarnings("serial")
	public AddOwner(String fg, String op) {
		flag = fg;
		option = op;
		
		setTitle(option + " - Agregar");
		setBounds(0, 0, 1200, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel() {
			 protected void paintComponent(Graphics g) {
		            super.paintComponent(g);
		            Color color1 = new Color(250, 244, 207);
		            Color color2 = new Color(200, 235, 208);
		            Graphics2D g2d = (Graphics2D) g;
		            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		            GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
		            g2d.setPaint(gp);
		            g2d.fillRect(0, 0, getWidth(), getHeight());
		        }
		};
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("logo_anapoima.png")).getScaledInstance(48, 48, java.awt.Image.SCALE_AREA_AVERAGING));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lImageLogo = new JLabel();
		JLabel title = new JLabel("Agregar " + option.toLowerCase());
		JLabel subtitle = new JLabel("Ingresa los datos");
		JLabel lFirstName = new JLabel("Nombre(s):");
		JLabel lLastName = new JLabel("Apellido(s):");
		JLabel lTypeDocument = new JLabel("Tipo de documento:");
		JLabel lDocument = new JLabel("N° de documento:");
		JLabel lBirthdate = new JLabel("Fecha de nacimiento:");
		JLabel lAddress = new JLabel("Dirección:");
		JLabel lCity = new JLabel("Ciudad:");
		JLabel lPhone = new JLabel("Teléfono:");
		JLabel lEmail = new JLabel("Correo electrónico:");
		JTextField tfFirstName = new JTextField(15);
		JTextField tfLastName = new JTextField(15);
		JTextField tfDocument = new JTextField(15);
		JTextField tfAddress = new JTextField(15);
		JTextField tfCity = new JTextField(15);
		JTextField tfPhone = new JTextField(15);
		JTextField tfEmail = new JTextField(15);
		JComboBox<String> cbTypeDocument = new JComboBox<String>();
		JDateChooser dcBirthdate = new JDateChooser("dd/MM/yyyy", "##/##/####", '-');
		JButton btnAdd = new JButton("Agregar", new ImageIcon(getClass().getClassLoader().getResource("add.png")));
		JButton btnBack = new JButton("Atrás", new ImageIcon(getClass().getClassLoader().getResource("back.png")));
		
		ImageIcon logo = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_anapoima.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_AREA_AVERAGING));
		lImageLogo.setIcon(logo);
		
		btnAdd.setPreferredSize(new Dimension(200, 36));
		btnBack.setPreferredSize(new Dimension(100, 30));
		
		Container container = getContentPane();
		SpringLayout springLayout = new SpringLayout();
		container.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, title, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, title, 50, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subtitle, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, subtitle, 130, SpringLayout.NORTH, container);
		
		//---
		
		springLayout.putConstraint(SpringLayout.WEST, lFirstName, 230, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lFirstName, 200, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfFirstName, 350, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfFirstName, 200, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lLastName, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lLastName, 200, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfLastName, 720, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfLastName, 200, SpringLayout.NORTH, container);
		
		//---
		
		springLayout.putConstraint(SpringLayout.WEST, lTypeDocument, 250, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lTypeDocument, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, cbTypeDocument, 470, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, cbTypeDocument, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lDocument, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lDocument, 250, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfDocument, 780, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfDocument, 250, SpringLayout.NORTH, container);
		
		//---
		
		springLayout.putConstraint(SpringLayout.WEST, lBirthdate, 400, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lBirthdate, 300, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, dcBirthdate, 620, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, dcBirthdate, 300, SpringLayout.NORTH, container);
		
		//---
		
		springLayout.putConstraint(SpringLayout.WEST, lAddress, 240, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lAddress, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfAddress, 350, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfAddress, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, lCity, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lCity, 350, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.WEST, tfCity, 700, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfCity, 350, SpringLayout.NORTH, container);
		
		//---
		
		springLayout.putConstraint(SpringLayout.WEST, lPhone, 245, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lPhone, 400, SpringLayout.NORTH, container);
				
		springLayout.putConstraint(SpringLayout.WEST, tfPhone, 350, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfPhone, 400, SpringLayout.NORTH, container);
				
		springLayout.putConstraint(SpringLayout.WEST, lEmail, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lEmail, 400, SpringLayout.NORTH, container);
				
		springLayout.putConstraint(SpringLayout.WEST, tfEmail, 800, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, tfEmail, 400, SpringLayout.NORTH, container);
		
		//---
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnAdd, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd, 450, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lImageLogo, 600, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.NORTH, lImageLogo, 500, SpringLayout.NORTH, container);
		
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnBack, 100, SpringLayout.WEST, container);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, btnBack, 500, SpringLayout.NORTH, container);
		
		container.add(title);
		container.add(subtitle);
		container.add(lFirstName);
		container.add(tfFirstName);
		container.add(lLastName);
		container.add(tfLastName);
		container.add(lTypeDocument);
		container.add(cbTypeDocument);
		container.add(lDocument);
		container.add(tfDocument);
		container.add(lBirthdate);
		container.add(dcBirthdate);
		container.add(lAddress);
		container.add(tfAddress);
		container.add(lCity);
		container.add(tfCity);
		container.add(lPhone);
		container.add(tfPhone);
		container.add(lEmail);
		container.add(tfEmail);
		container.add(btnAdd);
		container.add(lImageLogo);
		container.add(btnBack);
		
		title.setFont(new Font("Arial", Font.BOLD, 40));
		title.setForeground(new Color (116, 128, 148));
		
		subtitle.setFont(new Font("Arial", Font.BOLD, 28));
		subtitle.setForeground(new Color (116, 128, 148));
		
		lFirstName.setFont(new Font("Arial", Font.BOLD, 20));
		lFirstName.setForeground(new Color (116, 128, 148));
		
		tfFirstName.setFont(new Font("Arial", Font.PLAIN, 18));
		tfFirstName.setForeground(new Color (116, 128, 148));
		
		lLastName.setFont(new Font("Arial", Font.BOLD, 20));
		lLastName.setForeground(new Color (116, 128, 148));
		
		tfLastName.setFont(new Font("Arial", Font.PLAIN, 18));
		tfLastName.setForeground(new Color (116, 128, 148));
		
		lTypeDocument.setFont(new Font("Arial", Font.BOLD, 20));
		lTypeDocument.setForeground(new Color (116, 128, 148));
		
		cbTypeDocument.setFont(new Font("Arial", Font.PLAIN, 18));
		cbTypeDocument.setForeground(new Color (116, 128, 148));
		cbTypeDocument.setBackground(new Color(250, 244, 207));
		
		lDocument.setFont(new Font("Arial", Font.BOLD, 20));
		lDocument.setForeground(new Color (116, 128, 148));
		
		tfDocument.setFont(new Font("Arial", Font.PLAIN, 18));
		tfDocument.setForeground(new Color (116, 128, 148));
		
		lBirthdate.setFont(new Font("Arial", Font.BOLD, 20));
		lBirthdate.setForeground(new Color (116, 128, 148));
		
		dcBirthdate.setFont(new Font("Arial", Font.PLAIN, 18));
		dcBirthdate.setForeground(new Color (116, 128, 148));
		
		lAddress.setFont(new Font("Arial", Font.BOLD, 20));
		lAddress.setForeground(new Color (116, 128, 148));
		
		tfAddress.setFont(new Font("Arial", Font.PLAIN, 18));
		tfAddress.setForeground(new Color (116, 128, 148));
		
		lCity.setFont(new Font("Arial", Font.BOLD, 20));
		lCity.setForeground(new Color (116, 128, 148));
		
		tfCity.setFont(new Font("Arial", Font.PLAIN, 18));
		tfCity.setForeground(new Color (116, 128, 148));
		
		lPhone.setFont(new Font("Arial", Font.BOLD, 20));
		lPhone.setForeground(new Color (116, 128, 148));
		
		tfPhone.setFont(new Font("Arial", Font.PLAIN, 18));
		tfPhone.setForeground(new Color (116, 128, 148));
		
		lEmail.setFont(new Font("Arial", Font.BOLD, 20));
		lEmail.setForeground(new Color (116, 128, 148));
		
		tfEmail.setFont(new Font("Arial", Font.PLAIN, 18));
		tfEmail.setForeground(new Color (116, 128, 148));
		
		btnAdd.setFont(new Font("Arial", Font.BOLD, 20));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(new Color(136, 212, 152));
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color (196, 69, 59));
		
		btnAdd.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				btnAdd.setBackground(new Color(136, 212, 152));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnAdd.setBackground(new Color(136, 212, 152));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnAdd.setBackground(new Color(109, 186, 163));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		/* --- Logic part --- */
		
		SQLTypeDocument td = new SQLTypeDocument();
		ArrayList<String> alTypeDocument = new ArrayList<String>();
		alTypeDocument = td.selectTypeDocuments();
		
		for(int i = 0; i < alTypeDocument.size(); i++) {
			cbTypeDocument.addItem(alTypeDocument.get(i));
		}
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tfFirstName.getText().isEmpty() || tfLastName.getText().isEmpty() || tfDocument.getText().isEmpty() || tfAddress.getText().isEmpty() || tfCity.getText().isEmpty() || tfPhone.getText().isEmpty() || tfEmail.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debes diligenciar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					String typeDoc = cbTypeDocument.getSelectedItem().toString();
					String request;
					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");	
					String birthdate = df.format(dcBirthdate.getDate());
					
					SQLOwner ow = new SQLOwner();
					request = ow.insertOwner(tfFirstName.getText(), tfLastName.getText(), typeDoc, tfDocument.getText(), birthdate, tfAddress.getText(), tfCity.getText(), tfPhone.getText(), tfEmail.getText());
					
					switch(request) {
						case "success":
							JOptionPane.showMessageDialog(null, option +  " agregado con éxito", "Mensaje", JOptionPane.INFORMATION_MESSAGE);							
							goBack();							
							break;
						case "error insert":
							JOptionPane.showMessageDialog(null, "Ha ocurrido un error, inténtalo de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
							break;
						case "document":
							JOptionPane.showMessageDialog(null, "El N° de documento ya ha sido agregado previamente", "Error", JOptionPane.ERROR_MESSAGE);
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
